package com.ljunggren.reportGenerator.formatter;

import java.lang.annotation.Annotation;

import com.ljunggren.reportGenerator.Item;
import com.ljunggren.reportGenerator.annotation.BooleanFormatter;

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
