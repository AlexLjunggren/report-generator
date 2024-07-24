package io.ljunggren.report.generator.formatter;

import java.lang.annotation.Annotation;

import io.ljunggren.report.generator.Item;

public class FormatterCatchAll extends FormatterChain {
	
	public Item format(Annotation annotation, Item item) {
		return item;
	}

}
