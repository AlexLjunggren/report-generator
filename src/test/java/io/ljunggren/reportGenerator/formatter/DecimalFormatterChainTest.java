package io.ljunggren.reportGenerator.formatter;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import io.ljunggren.reportGenerator.annotation.DecimalFormatter;
import io.ljunggren.reportGenerator.annotation.Reportable;
import io.ljunggren.reportGenerator.csv.CSVGenerator;
import lombok.AllArgsConstructor;

public class DecimalFormatterChainTest {

	@AllArgsConstructor
	private class IntFormatPojo {
		@Reportable(headerName = "Balance", column= "A")
		@DecimalFormatter(format = "#.00")
		private int balance;
	}

	@Test
	public void formatIntTest() {
		IntFormatPojo pojo = new IntFormatPojo(250);
		CSVGenerator generator = new CSVGenerator(Arrays.asList(new IntFormatPojo[] {pojo}), ',');
		String csv = generator.generate();
		String expected = new StringBuilder()
				.append("Balance\r\n")
				.append("250.00\r\n")
				.toString();
		assertEquals(expected, csv);
	}

	@AllArgsConstructor
	private class DoubleFormatPojo {
		@Reportable(headerName = "Balance", column= "A")
		@DecimalFormatter(format = "#.00")
		private double balance;
	}

	@Test
	public void formatDoubleTest() {
		DoubleFormatPojo pojo = new DoubleFormatPojo(250.123);
		CSVGenerator generator = new CSVGenerator(Arrays.asList(new DoubleFormatPojo[] {pojo}), ',');
		String csv = generator.generate();
		String expected = new StringBuilder()
				.append("Balance\r\n")
				.append("250.12\r\n")
				.toString();
		assertEquals(expected, csv);
	}

	@AllArgsConstructor
	private class FloatFormatPojo {
		@Reportable(headerName = "Balance", column= "A")
		@DecimalFormatter(format = "#.00")
		private float balance;
	}

	@Test
	public void formatFloatTest() {
		FloatFormatPojo pojo = new FloatFormatPojo(250.123f);
		CSVGenerator generator = new CSVGenerator(Arrays.asList(new FloatFormatPojo[] {pojo}), ',');
		String csv = generator.generate();
		String expected = new StringBuilder()
				.append("Balance\r\n")
				.append("250.12\r\n")
				.toString();
		assertEquals(expected, csv);
	}

	@AllArgsConstructor
	private class FloatObjectFormatPojo {
		@Reportable(headerName = "Balance", column= "A")
		@DecimalFormatter(format = "#.00")
		private Float balance;
	}

	@Test
	public void formatFloatObjectTest() {
		FloatObjectFormatPojo pojo = new FloatObjectFormatPojo(null);
		CSVGenerator generator = new CSVGenerator(Arrays.asList(new FloatObjectFormatPojo[] {pojo}), ',');
		String csv = generator.generate();
		String expected = new StringBuilder()
				.append("Balance\r\n")
				.append("\r\n")
				.toString();
		assertEquals(expected, csv);
	}

	@AllArgsConstructor
	private class LongFormatPojo {
		@Reportable(headerName = "Balance", column= "A")
		@DecimalFormatter(format = "#.00")
		private long balance;
	}

	@Test
	public void formatLongObjectTest() {
		LongFormatPojo pojo = new LongFormatPojo(250L);
		CSVGenerator generator = new CSVGenerator(Arrays.asList(new LongFormatPojo[] {pojo}), ',');
		String csv = generator.generate();
		String expected = new StringBuilder()
				.append("Balance\r\n")
				.append("250.00\r\n")
				.toString();
		assertEquals(expected, csv);
	}
	
    public class MethodPojo {
        @Reportable(headerName = "Balance", column= "A")
        @DecimalFormatter(format = "#.00")
        public int getBalance() {
            return 250;
        }
    }

    @Test
    public void methodTest() {
        MethodPojo pojo = new MethodPojo();
        CSVGenerator generator = new CSVGenerator(Arrays.asList(new MethodPojo[] {pojo}), ',');
        String csv = generator.generate();
        String expected = new StringBuilder()
                .append("Balance\r\n")
                .append("250.00\r\n")
                .toString();
        assertEquals(expected, csv);
    }

	

}
