package com.ljunggren.reportGenerator.annotation.formatter;

import java.text.DecimalFormat;

import com.ljunggren.reportGenerator.Item;
import com.ljunggren.reportGenerator.annotation.DecimalFormatter;

public class DecimalFormatterChain extends FormatterChain {

	@Override
	public String format(Item item) {
		DecimalFormatter formatter = item.getField().getAnnotation(DecimalFormatter.class);
		if (formatter != null && isNumberInstance(item.getValue())) {
			DecimalFormat decimalFormat = new DecimalFormat(formatter.format());
			return decimalFormat.format(item.getValue());
		}
		return nextChain.format(item);
	}
	
	private boolean isNumberInstance(Object value) {
		return value instanceof Integer ||
				value instanceof Double ||
				value instanceof Float ||
				value instanceof Long;
	}

}
