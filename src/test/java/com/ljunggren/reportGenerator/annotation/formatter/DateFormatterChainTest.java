package com.ljunggren.reportGenerator.annotation.formatter;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;

import org.junit.Test;

import com.ljunggren.reportGenerator.CSVGenerator;
import com.ljunggren.reportGenerator.annotation.DateFormatter;
import com.ljunggren.reportGenerator.annotation.Reportable;

import lombok.AllArgsConstructor;
import lombok.Getter;

public class DateFormatterChainTest {
	
	@Getter
	@AllArgsConstructor
	private class DateFormatPojo {
		@Reportable(headerName = "Date", order = 0)
		@DateFormatter(format = "yyyy-MM-dd")
		private Date date;
	}

	@Getter
	@AllArgsConstructor
	private class BadDateFormatPojo {
		@Reportable(headerName = "Date", order = 0)
		@DateFormatter(format = "nonsense")
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

	@Test(expected = IllegalArgumentException.class)
	public void badFormatTest() {
		BadDateFormatPojo pojo = new BadDateFormatPojo(createDate());
		CSVGenerator generator = new CSVGenerator(Arrays.asList(new BadDateFormatPojo[] {pojo}), ',');
		generator.generate();
	}

}
