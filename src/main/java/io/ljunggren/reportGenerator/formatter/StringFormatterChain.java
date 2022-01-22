package io.ljunggren.reportGenerator.formatter;

import java.lang.annotation.Annotation;

import org.apache.commons.text.WordUtils;

import io.ljunggren.reportGenerator.Item;
import io.ljunggren.reportGenerator.annotation.StringFormatter;

public class StringFormatterChain extends FormatterChain{

	public Item format(Annotation annotation, Item item) {
		if (annotation.annotationType() == StringFormatter.class && item.getValue() instanceof String) {
			StringFormatter formatter = (StringFormatter) annotation;
			String value = format(item.getValue().toString(), formatter.format());
			item.setValue(value);
		}
		return nextChain.format(annotation, item);
	}
	
	private String format(String value, StringFormatter.Format format) {
		switch (format) {
		case UPPERCASE: return value.toUpperCase();
		case LOWERCASE: return value.toLowerCase();
		case CAPITALIZE: return WordUtils.capitalize(value);
		case CAPITALIZE_FULLY: return WordUtils.capitalizeFully(value);
		default: return value;
		}
	}

}
