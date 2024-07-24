package io.ljunggren.report.generator.formatter;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import io.ljunggren.report.generator.annotation.NullFormatter;
import io.ljunggren.report.generator.annotation.Reportable;
import io.ljunggren.report.generator.csv.CSVGenerator;
import lombok.AllArgsConstructor;

public class NullFormatterChainTest {

	@AllArgsConstructor
	private class BooleanPojo {
		@Reportable(headerName = "Validation", column= "A")
		@NullFormatter(replacementText = "No Data")
		private Boolean valid;
	}

	@Test
	public void formatBooleanTest() {
		BooleanPojo pojo = new BooleanPojo(null);
		CSVGenerator generator = new CSVGenerator(Arrays.asList(new BooleanPojo[] {pojo}), ',');
		String csv = generator.generate();
		String expected = new StringBuilder()
				.append("Validation\r\n")
				.append("No Data\r\n")
				.toString();
		assertEquals(expected, csv);
	}
	
	@AllArgsConstructor
	private class IntegerPojo {
		@Reportable(headerName = "Balance", column= "A")
		@NullFormatter(replacementText = "No Data")
		private Integer balance;
	}

	@Test
	public void formatIntTest() {
		IntegerPojo pojo = new IntegerPojo(null);
		CSVGenerator generator = new CSVGenerator(Arrays.asList(new IntegerPojo[] {pojo}), ',');
		String csv = generator.generate();
		String expected = new StringBuilder()
				.append("Balance\r\n")
				.append("No Data\r\n")
				.toString();
		assertEquals(expected, csv);
	}

	@AllArgsConstructor
	private class StringPojo {
		@Reportable(headerName = "Name", column= "A")
		@NullFormatter(replacementText = "No Data")
		private String name;
	}

	@Test
	public void formatStringTest() {
		StringPojo pojo = new StringPojo(null);
		CSVGenerator generator = new CSVGenerator(Arrays.asList(new StringPojo[] {pojo}), ',');
		String csv = generator.generate();
		String expected = new StringBuilder()
				.append("Name\r\n")
				.append("No Data\r\n")
				.toString();
		assertEquals(expected, csv);
	}
	
    public class MethodPojo {
        @Reportable(headerName = "Validation", column= "A")
        @NullFormatter(replacementText = "No Data")
        public Boolean isValid() {
            return null;
        }
    }

    @Test
    public void methodTest() {
        MethodPojo pojo = new MethodPojo();
        CSVGenerator generator = new CSVGenerator(Arrays.asList(new MethodPojo[] {pojo}), ',');
        String csv = generator.generate();
        String expected = new StringBuilder()
                .append("Validation\r\n")
                .append("No Data\r\n")
                .toString();
        assertEquals(expected, csv);
    }
    
}
