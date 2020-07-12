package com.ljunggren.reportGenerator.formatter;

import org.apache.commons.text.WordUtils;

import com.ljunggren.reportGenerator.Item;
import com.ljunggren.reportGenerator.annotation.StringFormatter;

public class StringFormatterChain extends FormatterChain{

	public String format(Item item) {
		StringFormatter formatter = item.getField().getAnnotation(StringFormatter.class);
		if (formatter != null && item.getValue() instanceof String) {
			String value = format(item.getValue().toString(), formatter.format());
			item.setValue(value);
		}
		return nextChain.format(item);
	}
	
	private String format(String value, StringFormatter.Format format) {
		switch (format) {
		case UPPERCASE: return value.toUpperCase();
		case LOWERCASE: return value.toLowerCase();
		case CAPITALIZE: return WordUtils.capitalize(value);
		case CAPITALIZE_FULLY: return WordUtils.capitalizeFully(value);
		default: return value;
		}
	}

}
