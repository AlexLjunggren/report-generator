package com.ljunggren.reportGenerator.formatter;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import com.ljunggren.reportGenerator.annotation.CommaFormatter;
import com.ljunggren.reportGenerator.annotation.Reportable;
import com.ljunggren.reportGenerator.csv.CSVGenerator;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class CommaFormatterChainTest {

	@Getter
	@AllArgsConstructor
	private class FormatIntPojo {
		@Reportable(headerName = "Balance", order = 0)
		@CommaFormatter
		private int balance;
	}

	@Test
	public void formatIntTest() {
		FormatIntPojo pojo = new FormatIntPojo(2500000);
		CSVGenerator generator = new CSVGenerator(Arrays.asList(new FormatIntPojo[] {pojo}), ',');
		String csv = generator.generate();
		String expected = new StringBuilder()
				.append("Balance\r\n")
				.append("\"2,500,000\"\r\n")
				.toString();
		assertEquals(expected, csv);
	}

	@Getter
	@AllArgsConstructor
	private class FormatDoublePojo {
		@Reportable(headerName = "Balance", order = 0)
		@CommaFormatter
		private double balance;
	}

	@Test
	public void formatDoubleTest() {
		FormatDoublePojo pojo = new FormatDoublePojo(2500000.12);
		CSVGenerator generator = new CSVGenerator(Arrays.asList(new FormatDoublePojo[] {pojo}), ',');
		String csv = generator.generate();
		String expected = new StringBuilder()
				.append("Balance\r\n")
				.append("\"2,500,000.12\"\r\n")
				.toString();
		assertEquals(expected, csv);
	}

	@Getter
	@AllArgsConstructor
	private class FormatLongPojo {
		@Reportable(headerName = "Balance", order = 0)
		@CommaFormatter
		private long balance;
	}

	@Test
	public void formatLongTest() {
		FormatLongPojo pojo = new FormatLongPojo(2500000);
		CSVGenerator generator = new CSVGenerator(Arrays.asList(new FormatLongPojo[] {pojo}), ',');
		String csv = generator.generate();
		String expected = new StringBuilder()
				.append("Balance\r\n")
				.append("\"2,500,000\"\r\n")
				.toString();
		assertEquals(expected, csv);
	}

	@Getter
	@AllArgsConstructor
	private class FormatNulltPojo {
		@Reportable(headerName = "Balance", order = 0)
		@CommaFormatter
		private Integer balance;
	}

	@Test
	public void formatNullTest() {
		FormatNulltPojo pojo = new FormatNulltPojo(null);
		CSVGenerator generator = new CSVGenerator(Arrays.asList(new FormatNulltPojo[] {pojo}), ',');
		String csv = generator.generate();
		String expected = new StringBuilder()
				.append("Balance\r\n")
				.append("\r\n")
				.toString();
		assertEquals(expected, csv);
	}

}
