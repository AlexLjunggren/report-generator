package com.ljunggren.reportGenerator.formatter;

import java.lang.annotation.Annotation;

import com.ljunggren.reportGenerator.Item;

public abstract class FormatterChain {

	protected FormatterChain nextChain;
	
	public FormatterChain nextChain(FormatterChain nextChain) {
		this.nextChain = nextChain;
		return this;
	}
	
	public abstract Item format(Annotation annotation, Item item);
	
}
