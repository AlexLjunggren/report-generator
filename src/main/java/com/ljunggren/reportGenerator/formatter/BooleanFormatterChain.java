package com.ljunggren.reportGenerator.formatter;

import com.ljunggren.reportGenerator.Item;
import com.ljunggren.reportGenerator.annotation.BooleanFormatter;

public class BooleanFormatterChain extends FormatterChain {

	@Override
	public String format(Item item) {
		BooleanFormatter formatter = item.getField().getAnnotation(BooleanFormatter.class);
		if (formatter != null && item.getValue() instanceof Boolean) {
			boolean isTrue = (boolean) item.getValue();
			return isTrue ? formatter.trueText() : formatter.falseText();
		}
		return nextChain.format(item);
	}
	
}
