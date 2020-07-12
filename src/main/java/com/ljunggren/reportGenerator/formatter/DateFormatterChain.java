package com.ljunggren.reportGenerator.formatter;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.ljunggren.reportGenerator.Item;
import com.ljunggren.reportGenerator.annotation.DateFormatter;

public class DateFormatterChain extends FormatterChain {

	public String format(Item item) {
		DateFormatter formatter = item.getField().getAnnotation(DateFormatter.class);
		if (formatter != null && item.getValue() instanceof Date) {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat(formatter.format());
			String value = simpleDateFormat.format(item.getValue());
			item.setValue(value);
		}
		return nextChain.format(item);
	}
	
}
