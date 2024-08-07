package io.ljunggren.report.generator;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

import io.ljunggren.report.generator.annotation.AutoSize;
import io.ljunggren.report.generator.annotation.Reportable;
import io.ljunggren.report.generator.formatter.BooleanFormatterChain;
import io.ljunggren.report.generator.formatter.CommaFormatterChain;
import io.ljunggren.report.generator.formatter.CurrencyFormatterChain;
import io.ljunggren.report.generator.formatter.DateFormatterChain;
import io.ljunggren.report.generator.formatter.DecimalFormatterChain;
import io.ljunggren.report.generator.formatter.FormatterCatchAll;
import io.ljunggren.report.generator.formatter.NullFormatterChain;
import io.ljunggren.report.generator.formatter.StringFormatterChain;
import io.ljunggren.report.generator.formatter.TrimFormatterChain;
import io.ljunggren.report.generator.utils.GeneratorUtils;
import lombok.Getter;

@Getter
public abstract class Generator {
	
	private List<?> data;
	private List<AccessibleObject> reportables;
	private List<String> headers;
	private List<Record> records;
	private List<Integer> autoSizedColumns;
	
	public abstract Object generate();
	
	protected Generator(List<?> data) {
		this.data = data;
		this.reportables = order(findAnnotatedFields(data), findAnnotatedMethods(data));
		this.headers = generateHeaders(reportables);
		this.records = generateRecords(reportables, data);
		this.autoSizedColumns = getAutoSizedColumns(reportables);
	}
	
	private List<Field> findAnnotatedFields(List<?> data) {
		if (data == null || data.isEmpty()) {
	        return new ArrayList<Field>();
		}
		Class<?> clazz = data.get(0).getClass();
		return FieldUtils.getFieldsListWithAnnotation(clazz, Reportable.class);
	}
	
    private List<Method> findAnnotatedMethods(List<?> data) {
        if (data == null || data.isEmpty()) {
            return new ArrayList<Method>();
        }
        Class<?> clazz = data.get(0).getClass();
        return MethodUtils.getMethodsListWithAnnotation(clazz, Reportable.class);
    }

	private List<AccessibleObject> order(List<Field> fields, List<Method> methods) {
	    List<AccessibleObject> reportables = Stream.concat(fields.stream(), methods.stream()).collect(Collectors.toList());
        Collections.sort(reportables, new Comparator<AccessibleObject>() {
            public int compare(AccessibleObject reportable1, AccessibleObject reportable2) {
                int method1Order = GeneratorUtils.columnToInt(getOrderFromReportable(reportable1));
                int method2Order = GeneratorUtils.columnToInt(getOrderFromReportable(reportable2));
                return method1Order - method2Order;
           }
        });
        return reportables;
	}
	
    private String getOrderFromReportable(AccessibleObject reportable) {
        return reportable.getAnnotation(Reportable.class).column();
    }
    
	private List<String> generateHeaders(List<AccessibleObject> reportables) {
		List<String> headers = new ArrayList<String>();
		reportables.forEach(reportable -> headers.add(getHeaderFromReportable(reportable)));
		return headers;
	}
	
    private String getHeaderFromReportable(AccessibleObject reportable) {
        return reportable.getAnnotation(Reportable.class).headerName();
    }
    
    private List<Integer> getAutoSizedColumns(List<AccessibleObject> reportables) {
        return reportables.stream()
                .filter(reportable -> isAutoSized(reportable))
                .map(reportable -> GeneratorUtils.columnToInt(getOrderFromReportable(reportable)))
                .collect(Collectors.toList());
    }
    
    private boolean isAutoSized(AccessibleObject reportable) {
        AutoSize autoSize = reportable.getAnnotation(AutoSize.class);
        return autoSize != null;
    }

	private List<Record> generateRecords(List<AccessibleObject> reportables, List<?> data) {
		List<Record> records = new ArrayList<Record>();
		if (data != null && !data.isEmpty()) {
			List<Item> items;
			for (Object object: data) {
				items = new ArrayList<Item>();
				for (AccessibleObject reportable: reportables) {
					try {
						items.add(getValueFromReportable(reportable, object));
					} catch (Exception e) {
						items.add(new Item("Unknown Value", null));
					}
				}
				records.add(new Record(items));
			}
		}
		return records;
	}
	
    public Item getValueFromReportable(AccessibleObject reportable, Object object) throws Exception {
        if (reportable instanceof Field) {
            Field field = (Field) reportable;
            Object value = FieldUtils.readField(field, object, true);
            return new Item(value, field);
        }
        if (reportable instanceof Method) {
            Method method = (Method) reportable;
            Object value = MethodUtils.invokeMethod(object, method.getName());
            return new Item(value, method);
        }
        throw new Exception("Unknown Instance type");
    }
    
	protected String getValueFromItem(Item item) {
		Annotation[] annotations = item.getAnnotations();
		Arrays.asList(annotations).forEach(annotation -> processChain(annotation, item));
		return item.getValue() == null ? null : item.getValue().toString();
	}
	
	private Item processChain(Annotation annotation, Item item) {
		return new DateFormatterChain().nextChain(
				new StringFormatterChain().nextChain(
				new DecimalFormatterChain().nextChain(
				new CurrencyFormatterChain().nextChain(
				new BooleanFormatterChain().nextChain(
				new CommaFormatterChain().nextChain(
				new TrimFormatterChain().nextChain(
				new NullFormatterChain().nextChain(
				new FormatterCatchAll()
						)))))))).format(annotation, item);
	}
	
}
