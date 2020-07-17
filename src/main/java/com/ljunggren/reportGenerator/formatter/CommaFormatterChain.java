package com.ljunggren.reportGenerator.formatter;

import java.lang.annotation.Annotation;
import java.text.NumberFormat;

import com.ljunggren.reportGenerator.Item;
import com.ljunggren.reportGenerator.annotation.CommaFormatter;

public class CommaFormatterChain extends FormatterChain {

	@Override
	public Item format(Annotation annotation, Item item) {
		if (annotation.annotationType() == CommaFormatter.class && isNumberInstance(item.getValue())) {
			String value = format(item.getValue());
			item.setValue(value);
		}
		return nextChain.format(annotation, item);
	}

	private boolean isNumberInstance(Object value) {
		return value instanceof Integer ||
				value instanceof Double ||
				value instanceof Long;
	}
	
	private String format(Object value) {
		return NumberFormat.getNumberInstance().format(value);
	}
	
}
