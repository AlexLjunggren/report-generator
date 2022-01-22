package io.ljunggren.reportGenerator.formatter;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import io.ljunggren.reportGenerator.annotation.Reportable;
import io.ljunggren.reportGenerator.csv.CSVGenerator;
import lombok.AllArgsConstructor;

public class FormatterCatchAllTest {

	@AllArgsConstructor
	private class Pojo {
		@Reportable(headerName = "Name", column= "A")
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
	
	@Test
	public void formatCatchAllNullTest() {
		Pojo pojo = new Pojo(null);
		CSVGenerator generator = new CSVGenerator(Arrays.asList(new Pojo[] {pojo}), ',');
		String csv = generator.generate();
		String expected = new StringBuilder()
				.append("Name\r\n")
				.append("\r\n")
				.toString();
		assertEquals(expected, csv);
	}
	
}
