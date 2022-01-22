package io.ljunggren.reportGenerator.formatter;

import java.lang.annotation.Annotation;

import io.ljunggren.reportGenerator.Item;

public class FormatterCatchAll extends FormatterChain {
	
	public Item format(Annotation annotation, Item item) {
		return item;
	}

}
