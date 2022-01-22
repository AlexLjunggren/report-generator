package io.ljunggren.reportGenerator;

import java.lang.annotation.Annotation;
import java.lang.reflect.AccessibleObject;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Item {

	private Object value;
	private AccessibleObject reportable;
	
    public Annotation[] getAnnotations() {
        return reportable == null ? new Annotation[] {} : reportable.getAnnotations();
    }
}
