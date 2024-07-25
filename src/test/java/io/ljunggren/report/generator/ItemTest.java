package io.ljunggren.report.generator;

import static org.junit.Assert.assertEquals;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.commons.lang3.reflect.FieldUtils;
import org.apache.commons.lang3.reflect.MethodUtils;
import org.junit.Test;

import io.ljunggren.report.generator.annotation.Reportable;
import io.ljunggren.report.generator.annotation.StringFormatter;
import io.ljunggren.report.generator.annotation.StringFormatter.Format;

public class ItemTest {
    
    public class TestPojo {
        @Reportable(headerName = "Name", column = "A")
        @StringFormatter(format = Format.UPPERCASE)
        private String name;
        
        @Reportable(headerName = "Application", column = "B")
        public String getApplication() {
            return "Report Generator";
        }
    }
    
    @Test
    public void getAnnotationsForFieldTest() {
        Field[] fields = FieldUtils.getFieldsWithAnnotation(TestPojo.class, Reportable.class);
        Item item = new Item("Alex", fields[0]);
        Annotation[] annotations = item.getAnnotations();
        assertEquals(2, annotations.length);
    }
    
    @Test
    public void getAnnotationsForMethodTest() {
        Method[] methods = MethodUtils.getMethodsWithAnnotation(TestPojo.class, Reportable.class);
        Item item = new Item("Alex", methods[0]);
        Annotation[] annotations = item.getAnnotations();
        assertEquals(1, annotations.length);
    }
    
    @Test
    public void getAnnotationsNullTest() {
        Item item = new Item(null, null);
        Annotation[] annotations = item.getAnnotations();
        assertEquals(0, annotations.length);
    }

}
