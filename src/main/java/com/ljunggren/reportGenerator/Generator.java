package com.ljunggren.reportGenerator;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;

import com.ljunggren.reportGenerator.annotation.Reportable;
import com.ljunggren.reportGenerator.annotation.ReportableMethod;
import com.ljunggren.reportGenerator.formatter.BooleanFormatterChain;
import com.ljunggren.reportGenerator.formatter.CommaFormatterChain;
import com.ljunggren.reportGenerator.formatter.CurrencyFormatterChain;
import com.ljunggren.reportGenerator.formatter.DateFormatterChain;
import com.ljunggren.reportGenerator.formatter.DecimalFormatterChain;
import com.ljunggren.reportGenerator.formatter.FormatterCatchAll;
import com.ljunggren.reportGenerator.formatter.NullFormatterChain;
import com.ljunggren.reportGenerator.formatter.StringFormatterChain;
import com.ljunggren.reportGenerator.formatter.TrimFormatterChain;

import lombok.Getter;

@Getter
public abstract class Generator {
	
	private List<?> data;
	private List<Field> fields;
	private List<Method> methods;
	private List<String> headers;
	private List<Record> records;
	
	public abstract Object generate();
	
	protected Generator(List<?> data) {
		this.data = data;
		this.fields = orderFields(findAnnotatedFields(data));
		this.methods = orderMethods(findAnnotatedMethods(data));
		this.headers = generateHeaders(fields);
		this.records = generateRecords(fields, data);
	}
	
	private List<Field> findAnnotatedFields(List<?> data) {
		if (data == null || data.isEmpty()) {
	        return new ArrayList<Field>();
		}
		Class<?> clazz = data.get(0).getClass();
		Field[] fields = FieldUtils.getFieldsWithAnnotation(clazz, Reportable.class);
		return Arrays.asList(fields);
	}
	
	private List<Field> orderFields(List<Field> fields) {
		Collections.sort(fields, new Comparator<Field>() {
			public int compare(Field field1, Field field2) {
				int field1Order = field1.getAnnotation(Reportable.class).order();
				int field2Order = field2.getAnnotation(Reportable.class).order();
				return field1Order - field2Order;
			}
		});
		return fields;
	}
	
	private List<Method> orderMethods(List<Method> methods) {
	    Collections.sort(methods, new Comparator<Method>() {
            public int compare(Method method1, Method method2) {
                int method1Order = method1.getAnnotation(ReportableMethod.class).order();
                int method2Order = method2.getAnnotation(ReportableMethod.class).order();
                return method1Order - method2Order;
           }
	    });
	    return methods;
	}
	
	private List<Method> findAnnotatedMethods(List<?> data) {
	    if (data == null || data.isEmpty()) {
	        return new ArrayList<Method>();
	    }
        Class<?> clazz = data.get(0).getClass();
        Method[] methods = MethodUtils.getMethodsWithAnnotation(clazz, ReportableMethod.class);
        return Arrays.asList(methods);
	}

	private  List<String> generateHeaders(List<Field> fields) {
		List<String> headers = new ArrayList<String>();
		fields.forEach(field -> headers.add(field.getAnnotation(Reportable.class).headerName()));
		return headers;
	}

	private List<Record> generateRecords(List<Field> fields, List<?> data) {
		List<Record> records = new ArrayList<Record>();
		if (data != null && !data.isEmpty()) {
			List<Item> items;
			for (Object object: data) {
				items = new ArrayList<Item>();
				for (Field field: fields) {
					try {
						Object value = FieldUtils.readField(field, object, true);
						items.add(new Item(value, field));
					} catch (IllegalAccessException e) {
						items.add(new Item("Unknown Value", null));
					}
				}
				records.add(new Record(items));
			}
		}
		return records;
	}
	
	protected String getValueFromItem(Item item) {
		Annotation[] annotations = item.getField().getAnnotations();
		Arrays.asList(annotations).forEach(annotation -> processChain(annotation, item));
		return item.getValue() != null ? item.getValue().toString() : null;
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
