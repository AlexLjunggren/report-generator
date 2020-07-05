package com.ljunggren.reportGenerator.formatter;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import com.ljunggren.reportGenerator.annotation.BooleanFormatter;
import com.ljunggren.reportGenerator.annotation.Reportable;
import com.ljunggren.reportGenerator.csv.CSVGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class BooleanFormatterChainTest {

	@Getter
	@AllArgsConstructor
	private class BooleanPojo {
		@Reportable(headerName = "Validation", order = 0)
		@BooleanFormatter(falseText = "Not Valid", trueText = "Valid")
		private Boolean valid;
	}

	@Test
	public void formatFalseTest() {
		BooleanPojo pojo = new BooleanPojo(false);
		CSVGenerator generator = new CSVGenerator(Arrays.asList(new BooleanPojo[] {pojo}), ',');
		String csv = generator.generate();
		String expected = new StringBuilder()
				.append("Validation\r\n")
				.append("Not Valid\r\n")
				.toString();
		assertEquals(expected, csv);
	}
	
	@Test
	public void formatTrueTest() {
		BooleanPojo pojo = new BooleanPojo(true);
		CSVGenerator generator = new CSVGenerator(Arrays.asList(new BooleanPojo[] {pojo}), ',');
		String csv = generator.generate();
		String expected = new StringBuilder()
				.append("Validation\r\n")
				.append("Valid\r\n")
				.toString();
		assertEquals(expected, csv);
	}
	
	@Test
	public void formatNullTest() {
		BooleanPojo pojo = new BooleanPojo(null);
		CSVGenerator generator = new CSVGenerator(Arrays.asList(new BooleanPojo[] {pojo}), ',');
		String csv = generator.generate();
		String expected = new StringBuilder()
				.append("Validation\r\n")
				.append("\r\n")
				.toString();
		assertEquals(expected, csv);
	}
	
}
