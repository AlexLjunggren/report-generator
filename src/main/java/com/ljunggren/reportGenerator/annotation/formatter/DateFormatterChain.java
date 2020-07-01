package com.ljunggren.reportGenerator.annotation.formatter;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.ljunggren.reportGenerator.Item;
import com.ljunggren.reportGenerator.annotation.DateFormatter;

public class DateFormatterChain extends FormatterChain{

	public String format(Item item) {
		DateFormatter formatter = item.getField().getAnnotation(DateFormatter.class);
		if (formatter != null && item.getValue() instanceof Date) {
			SimpleDateFormat format = new SimpleDateFormat(formatter.format());
			return format.format(item.getValue());
		}
		return nextChain.format(item);
	}
	
}
