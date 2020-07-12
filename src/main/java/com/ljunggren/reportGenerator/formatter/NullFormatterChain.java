package com.ljunggren.reportGenerator.formatter;

import com.ljunggren.reportGenerator.Item;
import com.ljunggren.reportGenerator.annotation.NullFormatter;

public class NullFormatterChain extends FormatterChain {

	@Override
	public String format(Item item) {
		NullFormatter formatter = item.getField().getAnnotation(NullFormatter.class);
		if (formatter != null && item.getValue() == null) {
			item.setValue(formatter.replacementText());
		}
		return nextChain.format(item);
	}

}
