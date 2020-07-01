package com.ljunggren.reportGenerator;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.apache.commons.lang3.reflect.FieldUtils;

import com.ljunggren.reportGenerator.annotation.Reportable;
import com.ljunggren.reportGenerator.annotation.formatter.DateFormatterChain;
import com.ljunggren.reportGenerator.annotation.formatter.FormatterCatchAll;
import com.ljunggren.reportGenerator.annotation.formatter.StringFormatterChain;

import lombok.Getter;

@Getter
public abstract class GeneratorBase {
	
	private List<?> data;
	private List<Field> fields;
	private List<String> headers;
	private List<Record> records;
	
	public abstract Object generate();
	
	protected GeneratorBase(List<?> data) {
		this.data = data;
		this.fields = orderFields(findAnnotatedFields(data));
		this.headers = generateHeaders(fields);
		this.records = generateRecords(fields, data);
	}
	
	private List<Field> findAnnotatedFields(List<?> data) {
		if (data != null && !data.isEmpty()) {
			Class<?> clazz = data.get(0).getClass();
			Field[] fields = FieldUtils.getFieldsWithAnnotation(clazz, Reportable.class);
			return Arrays.asList(fields);
		}
		return new ArrayList<Field>();
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

	private  List<String> generateHeaders(List<Field> fields) {
		List<String> headers = new ArrayList<String>();
		for (Field field: fields) {
			headers.add(field.getAnnotation(Reportable.class).headerName());
		}
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
		return new DateFormatterChain().nextChain(
				new StringFormatterChain().nextChain(
				new FormatterCatchAll()
				)).format(item);
	}
	

	
}
