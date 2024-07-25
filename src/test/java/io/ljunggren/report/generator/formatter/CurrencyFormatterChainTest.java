package io.ljunggren.report.generator.formatter;

import static org.junit.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.Test;

import io.ljunggren.report.generator.annotation.CurrencyFormatter;
import io.ljunggren.report.generator.annotation.CurrencyFormatter.Currency;
import io.ljunggren.report.generator.annotation.Reportable;
import io.ljunggren.report.generator.csv.CSVGenerator;
import lombok.AllArgsConstructor;

public class CurrencyFormatterChainTest {

	@AllArgsConstructor
	private class USDFormatIntPojo {
		@Reportable(headerName = "Balance", column= "A")
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
		@Reportable(headerName = "Balance", column= "A")
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
		@Reportable(headerName = "Balance", column= "A")
		@CurrencyFormatter(format = Currency.USD)
		private float balance;
	}

	@Test
	public void USDFormatFloatTest() {
		USDFormatFloatPojo pojo = new USDFormatFloatPojo(250.253f);
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
		@Reportable(headerName = "Balance", column= "A")
		@CurrencyFormatter(format = Currency.USD)
		private long balance;
	}

	@Test
	public void USDFormatLongTest() {
		USDFormatLongPojo pojo = new USDFormatLongPojo(250L);
		CSVGenerator generator = new CSVGenerator(Arrays.asList(new USDFormatLongPojo[] {pojo}), ',');
		String csv = generator.generate();
		String expected = new StringBuilder()
				.append("Balance\r\n")
				.append("$250.00\r\n")
				.toString();
		assertEquals(expected, csv);
	}

    @AllArgsConstructor
    private class USDFormatBigDecimalPojo {
        @Reportable(headerName = "Balance", column= "A")
        @CurrencyFormatter(format = Currency.USD)
        private BigDecimal balance;
    }

    @Test
    public void USDFormatBigDecimalTest() {
        USDFormatBigDecimalPojo pojo = new USDFormatBigDecimalPojo(new BigDecimal(250.253));
        CSVGenerator generator = new CSVGenerator(Arrays.asList(new USDFormatBigDecimalPojo[] {pojo}), ',');
        String csv = generator.generate();
        String expected = new StringBuilder()
                .append("Balance\r\n")
                .append("$250.25\r\n")
                .toString();
        assertEquals(expected, csv);
    }

    @AllArgsConstructor
    private class USDFormatStringPojo {
        @Reportable(headerName = "Balance", column= "A")
        @CurrencyFormatter(format = Currency.USD)
        private String balance;
    }
    
    @Test
    public void USDFormatStringTest() {
        USDFormatStringPojo pojo = new USDFormatStringPojo("250.253");
        CSVGenerator generator = new CSVGenerator(Arrays.asList(new USDFormatStringPojo[] {pojo}), ',');
        String csv = generator.generate();
        String expected = new StringBuilder()
                .append("Balance\r\n")
                .append("250.253\r\n")
                .toString();
        assertEquals(expected, csv);
    }

    public class MethodPojo {
        @Reportable(headerName = "Balance", column= "A")
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
