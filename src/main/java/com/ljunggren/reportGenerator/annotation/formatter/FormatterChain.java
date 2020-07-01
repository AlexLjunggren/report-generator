package com.ljunggren.reportGenerator.annotation.formatter;

import com.ljunggren.reportGenerator.Item;

public abstract class FormatterChain {

	protected FormatterChain nextChain;
	
	public FormatterChain nextChain(FormatterChain nextChain) {
		this.nextChain = nextChain;
		return this;
	}
	
	public abstract String format(Item item);
	
}
