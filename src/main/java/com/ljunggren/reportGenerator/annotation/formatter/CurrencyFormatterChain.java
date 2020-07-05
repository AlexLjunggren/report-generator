package com.ljunggren.reportGenerator.annotation.formatter;

import java.text.DecimalFormat;

import com.ljunggren.reportGenerator.Item;
import com.ljunggren.reportGenerator.annotation.CurrencyFormatter;

public class CurrencyFormatterChain extends FormatterChain {

	@Override
	public String format(Item item) {
		CurrencyFormatter formatter = item.getField().getAnnotation(CurrencyFormatter.class);
		if (formatter != null && isNumberInstance(item.getValue())) {
			return format(item.getValue(), formatter.format());
		}
		return nextChain.format(item);
	}

	private boolean isNumberInstance(Object value) {
		return value instanceof Integer ||
				value instanceof Double ||
				value instanceof Float ||
				value instanceof Long;
	}
	
	private String format(Object value, CurrencyFormatter.Format format) {
		switch  (format) {
		case USD: return String.format("$%s", new DecimalFormat("#.00").format(value));
		default: return value.toString();
		}
	}

}