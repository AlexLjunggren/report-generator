package com.ljunggren.reportGenerator;

import java.lang.reflect.Field;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class Item {

	private Object value;
	private Field field;

}
