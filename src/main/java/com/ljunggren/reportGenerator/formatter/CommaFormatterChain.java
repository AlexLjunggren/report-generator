package com.ljunggren.reportGenerator.formatter;

import java.text.NumberFormat;

import com.ljunggren.reportGenerator.Item;
import com.ljunggren.reportGenerator.annotation.CommaFormatter;

public class CommaFormatterChain extends FormatterChain {

	@Override
	public String format(Item item) {
		CommaFormatter formatter = item.getField().getAnnotation(CommaFormatter.class);
		if (formatter != null && isNumberInstance(item.getValue())) {
			String value = format(item.getValue());
			item.setValue(value);
		}
		return nextChain.format(item);
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
