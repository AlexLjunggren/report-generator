package io.ljunggren.report.generator.formatter;

import java.lang.annotation.Annotation;

import io.ljunggren.report.generator.Item;
import io.ljunggren.report.generator.annotation.BooleanFormatter;

public class BooleanFormatterChain extends FormatterChain {

	@Override
	public Item format(Annotation annotation, Item item) {
		if (annotation.annotationType() == BooleanFormatter.class && item.getValue() instanceof Boolean) {
			BooleanFormatter formatter = (BooleanFormatter) annotation;
			boolean isTrue = (boolean) item.getValue();
			String value = isTrue ? formatter.trueText() : formatter.falseText();
			item.setValue(value);
		}
		return nextChain.format(annotation, item);
	}
	
}
