package io.ljunggren.report.generator.formatter;

import java.lang.annotation.Annotation;
import java.math.BigDecimal;
import java.text.DecimalFormat;

import io.ljunggren.report.generator.Item;
import io.ljunggren.report.generator.annotation.CurrencyFormatter;

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
				value instanceof Long ||
				value instanceof BigDecimal;
	}
	
	private String format(Object value, CurrencyFormatter.Currency format) {
		switch  (format) {
		case USD: return String.format("$%s", new DecimalFormat("#.00").format(value));
		default: return value.toString();
		}
	}

}
