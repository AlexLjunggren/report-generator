package com.ljunggren.reportGenerator.formatter.integration;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import com.ljunggren.reportGenerator.annotation.NullFormatter;
import com.ljunggren.reportGenerator.annotation.Reportable;
import com.ljunggren.reportGenerator.annotation.StringFormatter;
import com.ljunggren.reportGenerator.annotation.StringFormatter.Format;
import com.ljunggren.reportGenerator.annotation.TrimFormatter;
import com.ljunggren.reportGenerator.csv.CSVGenerator;

import lombok.AllArgsConstructor;

public class FormatIntegrationTest {

	@AllArgsConstructor
	private class UpperTrimPojo {
		@Reportable(headerName = "Name", order = 0)
		@StringFormatter(format = Format.UPPERCASE)
		@TrimFormatter
		private String name;
	}

	@Test
	public void UpperTrimTest() {
		UpperTrimPojo pojo = new UpperTrimPojo("  alex  ");
		CSVGenerator generator = new CSVGenerator(Arrays.asList(new UpperTrimPojo[] {pojo}), ',');
		String csv = generator.generate();
		String expected = new StringBuilder()
				.append("Name\r\n")
				.append("ALEX\r\n")
				.toString();
		assertEquals(expected, csv);
	}
	
	@AllArgsConstructor
	private class TrimUpperPojo {
		@Reportable(headerName = "Name", order = 0)
		@TrimFormatter
		@StringFormatter(format = Format.UPPERCASE)
		private String name;
	}

	@Test
	public void TrimUpperTest() {
		TrimUpperPojo pojo = new TrimUpperPojo("  alex  ");
		CSVGenerator generator = new CSVGenerator(Arrays.asList(new TrimUpperPojo[] {pojo}), ',');
		String csv = generator.generate();
		String expected = new StringBuilder()
				.append("Name\r\n")
				.append("ALEX\r\n")
				.toString();
		assertEquals(expected, csv);
	}
	
	@AllArgsConstructor
	private class NullTrimCapitalizeFullPojo {
		@Reportable(headerName = "Name", order = 0)
		@NullFormatter(replacementText = " no data ")
		@StringFormatter(format = Format.CAPITALIZE_FULLY)
		@TrimFormatter
		private String name;
	}

	@Test
	public void NullTrimCapitalizeFullTest() {
		NullTrimCapitalizeFullPojo pojo = new NullTrimCapitalizeFullPojo(null);
		CSVGenerator generator = new CSVGenerator(Arrays.asList(new NullTrimCapitalizeFullPojo[] {pojo}), ',');
		String csv = generator.generate();
		String expected = new StringBuilder()
				.append("Name\r\n")
				.append("No Data\r\n")
				.toString();
		assertEquals(expected, csv);
	}
	
}
