package io.ljunggren.report.generator.formatter;

import java.lang.annotation.Annotation;

import io.ljunggren.report.generator.Item;

public abstract class FormatterChain {

	protected FormatterChain nextChain;
	
	public FormatterChain nextChain(FormatterChain nextChain) {
		this.nextChain = nextChain;
		return this;
	}
	
	public abstract Item format(Annotation annotation, Item item);
	
}
