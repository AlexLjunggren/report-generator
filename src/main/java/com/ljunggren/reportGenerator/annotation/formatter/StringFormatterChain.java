package com.ljunggren.reportGenerator.annotation.formatter;

import com.ljunggren.reportGenerator.Item;
import com.ljunggren.reportGenerator.annotation.StringFormatter;

public class StringFormatterChain extends FormatterChain{

	public String format(Item item) {
		StringFormatter formatter = item.getField().getAnnotation(StringFormatter.class);
		if (formatter != null && item.getValue() instanceof String) {
			return format((String) item.getValue(), formatter.format());
		}
		return nextChain.format(item);
	}
	
	private String format(String value, StringFormatter.Format format) {
		switch (format) {
		case UPPERCASE: return value.toUpperCase();
		case LOWERCASE: return value.toLowerCase();
		default: return value;
		}
	}

}
