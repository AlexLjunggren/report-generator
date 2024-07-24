package io.ljunggren.report.generator.formatter;

import java.lang.annotation.Annotation;
import java.text.SimpleDateFormat;
import java.util.Date;

import io.ljunggren.report.generator.Item;
import io.ljunggren.report.generator.annotation.DateFormatter;

public class DateFormatterChain extends FormatterChain {

	public Item format(Annotation annotation, Item item) {
		if (annotation.annotationType() == DateFormatter.class && item.getValue() instanceof Date) {
			DateFormatter formatter = (DateFormatter) annotation;
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatter.format());
			String value = simpleDateFormat.format(item.getValue());
			item.setValue(value);
		}
		return nextChain.format(annotation, item);
	}
	
}
