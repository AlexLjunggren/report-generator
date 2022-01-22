package io.ljunggren.reportGenerator.formatter;

import java.lang.annotation.Annotation;

import io.ljunggren.reportGenerator.Item;
import io.ljunggren.reportGenerator.annotation.TrimFormatter;

public class TrimFormatterChain extends FormatterChain {

	@Override
	public Item format(Annotation annotation, Item item) {
		if (annotation.annotationType() == TrimFormatter.class && item.getValue() instanceof String) {
			String value = item.getValue().toString().trim();
			item.setValue(value);
		}
		return nextChain.format(annotation, item);
	}

}
