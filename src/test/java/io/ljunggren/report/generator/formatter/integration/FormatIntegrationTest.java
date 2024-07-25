package io.ljunggren.report.generator.formatter.integration;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import io.ljunggren.report.generator.annotation.NullFormatter;
import io.ljunggren.report.generator.annotation.Reportable;
import io.ljunggren.report.generator.annotation.StringFormatter;
import io.ljunggren.report.generator.annotation.StringFormatter.Format;
import io.ljunggren.report.generator.annotation.TrimFormatter;
import io.ljunggren.report.generator.csv.CSVGenerator;
import lombok.AllArgsConstructor;

public class FormatIntegrationTest {

	@AllArgsConstructor
	private class UpperTrimPojo {
		@Reportable(headerName = "Name", column= "A")
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
		@Reportable(headerName = "Name", column= "A")
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
		@Reportable(headerName = "Name", column= "A")
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
