package com.ljunggren.reportGenerator.formatter;

import java.lang.annotation.Annotation;

import com.ljunggren.reportGenerator.Item;
import com.ljunggren.reportGenerator.annotation.TrimFormatter;

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
