package io.ljunggren.reportGenerator.formatter;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import io.ljunggren.reportGenerator.annotation.CommaFormatter;
import io.ljunggren.reportGenerator.annotation.Reportable;
import io.ljunggren.reportGenerator.csv.CSVGenerator;
import lombok.AllArgsConstructor;

public class CommaFormatterChainTest {

	@AllArgsConstructor
	private class FormatIntPojo {
		@Reportable(headerName = "Balance", column= "A")
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

	@AllArgsConstructor
	private class FormatDoublePojo {
		@Reportable(headerName = "Balance", column= "A")
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

	@AllArgsConstructor
	private class FormatLongPojo {
		@Reportable(headerName = "Balance", column= "A")
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

	@AllArgsConstructor
	private class FormatNulltPojo {
		@Reportable(headerName = "Balance", column= "A")
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
	
    public class MethodPojo {
        @Reportable(headerName = "Balance", column= "A")
        @CommaFormatter
        public int getBalance() {
            return 2500000;
        }
    }

	@Test
	public void methodTest() {
        MethodPojo pojo = new MethodPojo();
        CSVGenerator generator = new CSVGenerator(Arrays.asList(new MethodPojo[] {pojo}), ',');
        String csv = generator.generate();
        String expected = new StringBuilder()
                .append("Balance\r\n")
                .append("\"2,500,000\"\r\n")
                .toString();
        assertEquals(expected, csv);
	}

}
