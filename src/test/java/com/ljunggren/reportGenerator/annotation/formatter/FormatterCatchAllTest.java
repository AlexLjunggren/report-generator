package com.ljunggren.reportGenerator.annotation.formatter;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import com.ljunggren.reportGenerator.CSVGenerator;
import com.ljunggren.reportGenerator.annotation.Reportable;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class FormatterCatchAllTest {

	@Getter
	@AllArgsConstructor
	private class Pojo {
		@Reportable(headerName = "Name", order = 0)
		private String name;
	}

	@Test
	public void formatCatchAllTest() {
		Pojo pojo = new Pojo("Alex");
		CSVGenerator generator = new CSVGenerator(Arrays.asList(new Pojo[] {pojo}), ',');
		String csv = generator.generate();
		String expected = new StringBuilder()
				.append("Name\r\n")
				.append("Alex\r\n")
				.toString();
		assertEquals(expected, csv);
	}
	
}
