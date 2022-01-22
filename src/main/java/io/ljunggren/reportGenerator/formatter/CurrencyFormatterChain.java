package io.ljunggren.reportGenerator.formatter;

import java.lang.annotation.Annotation;
import java.text.DecimalFormat;

import io.ljunggren.reportGenerator.Item;
import io.ljunggren.reportGenerator.annotation.CurrencyFormatter;

public class CurrencyFormatterChain extends FormatterChain {
	
	@Override
	public Item format(Annotation annotation, Item item) {
		if (annotation.annotationType() == CurrencyFormatter.class && isNumberInstance(item.getValue())) {
			CurrencyFormatter formatter = (CurrencyFormatter) annotation;
			String value = format(item.getValue(), formatter.format());
			item.setValue(value);
		}
		return nextChain.format(annotation, item);
	}

	private boolean isNumberInstance(Object value) {
		return value instanceof Integer ||
				value instanceof Double ||
				value instanceof Float ||
				value instanceof Long;
	}
	
	private String format(Object value, CurrencyFormatter.Currency format) {
		switch  (format) {
		case USD: return String.format("$%s", new DecimalFormat("#.00").format(value));
		default: return value.toString();
		}
	}

}
