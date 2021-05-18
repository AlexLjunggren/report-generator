package com.ljunggren.reportGenerator.formatter;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import com.ljunggren.reportGenerator.annotation.Reportable;
import com.ljunggren.reportGenerator.annotation.TrimFormatter;
import com.ljunggren.reportGenerator.csv.CSVGenerator;

import lombok.AllArgsConstructor;

public class TrimFormatterChainTest {

	@AllArgsConstructor
	private class TrimPojo {
		@Reportable(headerName = "Name", column= "A")
		@TrimFormatter
		private String name;
	}

	@Test
	public void formatTest() {
		TrimPojo pojo = new TrimPojo("  Alex  ");
		CSVGenerator generator = new CSVGenerator(Arrays.asList(new TrimPojo[] {pojo}), ',');
		String csv = generator.generate();
		String expected = new StringBuilder()
				.append("Name\r\n")
				.append("Alex\r\n")
				.toString();
		assertEquals(expected, csv);
	}
	
	@Test
	public void formatNullTest() {
		TrimPojo pojo = new TrimPojo(null);
		CSVGenerator generator = new CSVGenerator(Arrays.asList(new TrimPojo[] {pojo}), ',');
		String csv = generator.generate();
		String expected = new StringBuilder()
				.append("Name\r\n")
				.append("\r\n")
				.toString();
		assertEquals(expected, csv);
	}
	
    public class MethodPojo {
        @Reportable(headerName = "Name", column= "A")
        @TrimFormatter
        public String getName() {
            return "  Alex  ";
        }
    }

    @Test
    public void mathodTest() {
        MethodPojo pojo = new MethodPojo();
        CSVGenerator generator = new CSVGenerator(Arrays.asList(new MethodPojo[] {pojo}), ',');
        String csv = generator.generate();
        String expected = new StringBuilder()
                .append("Name\r\n")
                .append("Alex\r\n")
                .toString();
        assertEquals(expected, csv);
    }
    
}
