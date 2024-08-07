package io.ljunggren.report.generator.formatter;

import java.lang.annotation.Annotation;
import java.text.DecimalFormat;

import io.ljunggren.report.generator.Item;
import io.ljunggren.report.generator.annotation.DecimalFormatter;

public class DecimalFormatterChain extends FormatterChain {

	@Override
	public Item format(Annotation annotation, Item item) {
		if (annotation.annotationType() == DecimalFormatter.class && isNumberInstance(item.getValue())) {
			DecimalFormatter formatter = (DecimalFormatter) annotation;
			DecimalFormat decimalFormat = new DecimalFormat(formatter.format());
			String value = decimalFormat.format(item.getValue());
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

}
