package com.ljunggren.reportGenerator;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Item {

	private Object value;
	private Object reportable;
	
    public Annotation[] getAnnotations() {
        return reportable instanceof AccessibleObject ? 
                ((AccessibleObject) reportable).getAnnotations() : 
                    new Annotation[] {};
    }

}
