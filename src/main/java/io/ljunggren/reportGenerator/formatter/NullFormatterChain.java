package io.ljunggren.reportGenerator.formatter;

import java.lang.annotation.Annotation;

import io.ljunggren.reportGenerator.Item;
import io.ljunggren.reportGenerator.annotation.NullFormatter;

public class NullFormatterChain extends FormatterChain {

	@Override
	public Item format(Annotation annotation, Item item) {
		if (annotation.annotationType() == NullFormatter.class && item.getValue() == null) {
			NullFormatter formatter = (NullFormatter) annotation;
			item.setValue(formatter.replacementText());
		}
		return nextChain.format(annotation, item);
	}

}
