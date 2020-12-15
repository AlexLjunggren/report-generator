package com.ljunggren.reportGenerator.formatter;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.junit.Test;

import com.ljunggren.reportGenerator.annotation.DateFormatter;
import com.ljunggren.reportGenerator.annotation.Reportable;
import com.ljunggren.reportGenerator.csv.CSVGenerator;

import lombok.AllArgsConstructor;

public class DateFormatterChainTest {
	
	@AllArgsConstructor
	private class DateFormatPojo {
		@Reportable(headerName = "Date", order = 0)
		@DateFormatter(format = "yyyy-MM-dd")
		private Date date;
	}

	private Date createDate() {
		try {
			return new SimpleDateFormat("yyyy/MM/dd").parse("2020/06/28");
		} catch (ParseException e) {
			return null;
		}
	}

	@Test
	public void formatTest() {
		DateFormatPojo pojo = new DateFormatPojo(createDate());
		CSVGenerator generator = new CSVGenerator(Arrays.asList(new DateFormatPojo[] {pojo}), ',');
		String csv = generator.generate();
		String expected = new StringBuilder()
				.append("Date\r\n")
				.append("2020-06-28\r\n")
				.toString();
		assertEquals(expected, csv);
	}

	@Test
	public void formatNullTest() {
		DateFormatPojo pojo = new DateFormatPojo(null);
		CSVGenerator generator = new CSVGenerator(Arrays.asList(new DateFormatPojo[] {pojo}), ',');
		String csv = generator.generate();
		String expected = new StringBuilder()
				.append("Date\r\n")
				.append("\r\n")
				.toString();
		assertEquals(expected, csv);
	}

	@AllArgsConstructor
	private class BadDateFormatPojo {
		@Reportable(headerName = "Date", order = 0)
		@DateFormatter(format = "nonsense")
		private Date date;
	}

	@Test(expected = IllegalArgumentException.class)
	public void badFormatTest() {
		BadDateFormatPojo pojo = new BadDateFormatPojo(createDate());
		CSVGenerator generator = new CSVGenerator(Arrays.asList(new BadDateFormatPojo[] {pojo}), ',');
		generator.generate();
	}

    public class MethodPojo {
        @Reportable(headerName = "Date", order = 0)
        @DateFormatter(format = "yyyy-MM-dd")
        public Date getDate() throws ParseException {
            return new SimpleDateFormat("yyyy/MM/dd").parse("2020/06/28");
        };
    }

    @Test
    public void methodTest() {
        MethodPojo pojo = new MethodPojo();
        CSVGenerator generator = new CSVGenerator(Arrays.asList(new MethodPojo[] {pojo}), ',');
        String csv = generator.generate();
        String expected = new StringBuilder()
                .append("Date\r\n")
                .append("2020-06-28\r\n")
                .toString();
        assertEquals(expected, csv);
    }

}
