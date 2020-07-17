package com.ljunggren.reportGenerator.formatter;

import java.lang.annotation.Annotation;

import com.ljunggren.reportGenerator.Item;

public class FormatterCatchAll extends FormatterChain {
	
	public Item format(Annotation annotation, Item item) {
		return item;
	}

}
