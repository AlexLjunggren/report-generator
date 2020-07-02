package com.ljunggren.reportGenerator.annotation.formatter;

import com.ljunggren.reportGenerator.Item;

public class FormatterCatchAll extends FormatterChain {
	
	public String format(Item item) {
		return item.getValue() != null ? item.getValue().toString() : null;
	}

}
