package com.ljunggren.reportGenerator.formatter;

import com.ljunggren.reportGenerator.Item;
import com.ljunggren.reportGenerator.annotation.TrimFormatter;

public class TrimFormatterChain extends FormatterChain {

	@Override
	public String format(Item item) {
		TrimFormatter formatter = item.getField().getAnnotation(TrimFormatter.class);
		if (formatter != null && item.getValue() instanceof String) {
			String value = item.getValue().toString().trim();
			item.setValue(value);
		}
		return nextChain.format(item);
	}

}
