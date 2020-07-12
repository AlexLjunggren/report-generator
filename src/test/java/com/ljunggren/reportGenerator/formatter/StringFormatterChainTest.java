package com.ljunggren.reportGenerator.formatter;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import com.ljunggren.reportGenerator.annotation.Reportable;
import com.ljunggren.reportGenerator.annotation.StringFormatter;
import com.ljunggren.reportGenerator.annotation.StringFormatter.Format;
import com.ljunggren.reportGenerator.csv.CSVGenerator;

import lombok.AllArgsConstructor;

public class StringFormatterChainTest {
	
	@AllArgsConstructor
	private class UppercasePojo {
		@Reportable(headerName = "Name", order = 0)
		@StringFormatter(format = Format.UPPERCASE)
		private String name;
	}

	@Test
	public void formatUppercaseTest() {
		UppercasePojo pojo = new UppercasePojo("Alex");
		CSVGenerator generator = new CSVGenerator(Arrays.asList(new UppercasePojo[] {pojo}), ',');
		String csv = generator.generate();
		String expected = new StringBuilder()
				.append("Name\r\n")
				.append("ALEX\r\n")
				.toString();
		assertEquals(expected, csv);
	}
	
	@AllArgsConstructor
	private class LowercasePojo {
		@Reportable(headerName = "Name", order = 0)
		@StringFormatter(format = Format.LOWERCASE)
		private String name;
	}

	@Test
	public void formatLowercaseTest() {
		LowercasePojo pojo = new LowercasePojo("Alex");
		CSVGenerator generator = new CSVGenerator(Arrays.asList(new LowercasePojo[] {pojo}), ',');
		String csv = generator.generate();
		String expected = new StringBuilder()
				.append("Name\r\n")
				.append("alex\r\n")
				.toString();
		assertEquals(expected, csv);
	}
	
	@Test
	public void formatLowercaseNullTest() {
		LowercasePojo pojo = new LowercasePojo(null);
		CSVGenerator generator = new CSVGenerator(Arrays.asList(new LowercasePojo[] {pojo}), ',');
		String csv = generator.generate();
		String expected = new StringBuilder()
				.append("Name\r\n")
				.append("\r\n")
				.toString();
		assertEquals(expected, csv);
	}
	
	@AllArgsConstructor
	private class CapitalizePojo {
		@Reportable(headerName = "Name", order = 0)
		@StringFormatter(format = Format.CAPITALIZE)
		private String name;
	}

	@Test
	public void formatCapitalizeTest() {
		CapitalizePojo pojo = new CapitalizePojo("alex ljunggren");
		CSVGenerator generator = new CSVGenerator(Arrays.asList(new CapitalizePojo[] {pojo}), ',');
		String csv = generator.generate();
		String expected = new StringBuilder()
				.append("Name\r\n")
				.append("Alex Ljunggren\r\n")
				.toString();
		assertEquals(expected, csv);
	}
	
	@AllArgsConstructor
	private class CapitalizeFullyPojo {
		@Reportable(headerName = "Name", order = 0)
		@StringFormatter(format = Format.CAPITALIZE_FULLY)
		private String name;
	}

	@Test
	public void formatCapitalizeFullyTest() {
		CapitalizeFullyPojo pojo = new CapitalizeFullyPojo("aLEX lJUNGGREN");
		CSVGenerator generator = new CSVGenerator(Arrays.asList(new CapitalizeFullyPojo[] {pojo}), ',');
		String csv = generator.generate();
		String expected = new StringBuilder()
				.append("Name\r\n")
				.append("Alex Ljunggren\r\n")
				.toString();
		assertEquals(expected, csv);
	}
	
}
