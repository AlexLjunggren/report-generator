package com.ljunggren.reportGenerator.formatter;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import com.ljunggren.reportGenerator.annotation.CurrencyFormatter;
import com.ljunggren.reportGenerator.annotation.CurrencyFormatter.Currency;
import com.ljunggren.reportGenerator.annotation.Reportable;
import com.ljunggren.reportGenerator.csv.CSVGenerator;

import lombok.AllArgsConstructor;

public class CurrencyFormatterChainTest {

	@AllArgsConstructor
	private class USDFormatIntPojo {
		@Reportable(headerName = "Balance", order = 0)
		@CurrencyFormatter(format = Currency.USD)
		private int balance;
	}

	@Test
	public void formatUSDIntTest() {
		USDFormatIntPojo pojo = new USDFormatIntPojo(250);
		CSVGenerator generator = new CSVGenerator(Arrays.asList(new USDFormatIntPojo[] {pojo}), ',');
		String csv = generator.generate();
		String expected = new StringBuilder()
				.append("Balance\r\n")
				.append("$250.00\r\n")
				.toString();
		assertEquals(expected, csv);
	}

	@AllArgsConstructor
	private class USDFormatDoublePojo {
		@Reportable(headerName = "Balance", order = 0)
		@CurrencyFormatter(format = Currency.USD)
		private double balance;
	}

	@Test
	public void formatUSDDoubleTest() {
		USDFormatDoublePojo pojo = new USDFormatDoublePojo(250.253);
		CSVGenerator generator = new CSVGenerator(Arrays.asList(new USDFormatDoublePojo[] {pojo}), ',');
		String csv = generator.generate();
		String expected = new StringBuilder()
				.append("Balance\r\n")
				.append("$250.25\r\n")
				.toString();
		assertEquals(expected, csv);
	}

	@AllArgsConstructor
	private class USDFormatFloatPojo {
		@Reportable(headerName = "Balance", order = 0)
		@CurrencyFormatter(format = Currency.USD)
		private double balance;
	}

	@Test
	public void USDFormatFloatPojo() {
		USDFormatFloatPojo pojo = new USDFormatFloatPojo(250.253);
		CSVGenerator generator = new CSVGenerator(Arrays.asList(new USDFormatFloatPojo[] {pojo}), ',');
		String csv = generator.generate();
		String expected = new StringBuilder()
				.append("Balance\r\n")
				.append("$250.25\r\n")
				.toString();
		assertEquals(expected, csv);
	}

	@AllArgsConstructor
	private class USDFormatLongPojo {
		@Reportable(headerName = "Balance", order = 0)
		@CurrencyFormatter(format = Currency.USD)
		private long balance;
	}

	@Test
	public void USDFormatLongPojo() {
		USDFormatLongPojo pojo = new USDFormatLongPojo(250L);
		CSVGenerator generator = new CSVGenerator(Arrays.asList(new USDFormatLongPojo[] {pojo}), ',');
		String csv = generator.generate();
		String expected = new StringBuilder()
				.append("Balance\r\n")
				.append("$250.00\r\n")
				.toString();
		assertEquals(expected, csv);
	}

    public class MethodPojo {
        @Reportable(headerName = "Balance", order = 0)
        @CurrencyFormatter(format = Currency.USD)
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
                .append("$250.00\r\n")
                .toString();
        assertEquals(expected, csv);
    }

}
