package io.ljunggren.reportGenerator.formatter;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.Test;

import io.ljunggren.reportGenerator.annotation.Reportable;
import io.ljunggren.reportGenerator.annotation.StringFormatter;
import io.ljunggren.reportGenerator.annotation.StringFormatter.Format;
import io.ljunggren.reportGenerator.csv.CSVGenerator;
import lombok.AllArgsConstructor;

public class StringFormatterChainTest {
	
	@AllArgsConstructor
	private class UppercasePojo {
		@Reportable(headerName = "Name", column= "A")
		@StringFormatter(format = Format.UPPERCASE)
		private String name;
	}

	@Test
	public void formatUppercaseTest() {
		UppercasePojo pojo = new UppercasePojo("Alex");
		CSVGenerator generator = new CSVGenerator(Arrays.asList(new UppercasePojo[] {pojo}), ',');
		String csv = generator.generate();
		String expected = new StringBuilder()
				.append("Name\r\n")
				.append("ALEX\r\n")
				.toString();
		assertEquals(expected, csv);
	}
	
	@AllArgsConstructor
	private class LowercasePojo {
		@Reportable(headerName = "Name", column= "A")
		@StringFormatter(format = Format.LOWERCASE)
		private String name;
	}

	@Test
	public void formatLowercaseTest() {
		LowercasePojo pojo = new LowercasePojo("Alex");
		CSVGenerator generator = new CSVGenerator(Arrays.asList(new LowercasePojo[] {pojo}), ',');
		String csv = generator.generate();
		String expected = new StringBuilder()
				.append("Name\r\n")
				.append("alex\r\n")
				.toString();
		assertEquals(expected, csv);
	}
	
	@Test
	public void formatLowercaseNullTest() {
		LowercasePojo pojo = new LowercasePojo(null);
		CSVGenerator generator = new CSVGenerator(Arrays.asList(new LowercasePojo[] {pojo}), ',');
		String csv = generator.generate();
		String expected = new StringBuilder()
				.append("Name\r\n")
				.append("\r\n")
				.toString();
		assertEquals(expected, csv);
	}
	
	@AllArgsConstructor
	private class CapitalizePojo {
		@Reportable(headerName = "Name", column= "A")
		@StringFormatter(format = Format.CAPITALIZE)
		private String name;
	}

	@Test
	public void formatCapitalizeTest() {
		CapitalizePojo pojo = new CapitalizePojo("alex ljunggren");
		CSVGenerator generator = new CSVGenerator(Arrays.asList(new CapitalizePojo[] {pojo}), ',');
		String csv = generator.generate();
		String expected = new StringBuilder()
				.append("Name\r\n")
				.append("Alex Ljunggren\r\n")
				.toString();
		assertEquals(expected, csv);
	}
	
	@AllArgsConstructor
	private class CapitalizeFullyPojo {
		@Reportable(headerName = "Name", column= "A")
		@StringFormatter(format = Format.CAPITALIZE_FULLY)
		private String name;
	}

	@Test
	public void formatCapitalizeFullyTest() {
		CapitalizeFullyPojo pojo = new CapitalizeFullyPojo("aLEX lJUNGGREN");
		CSVGenerator generator = new CSVGenerator(Arrays.asList(new CapitalizeFullyPojo[] {pojo}), ',');
		String csv = generator.generate();
		String expected = new StringBuilder()
				.append("Name\r\n")
				.append("Alex Ljunggren\r\n")
				.toString();
		assertEquals(expected, csv);
	}
	
    public class MethodPojo {
        @Reportable(headerName = "Name", column= "A")
        @StringFormatter(format = Format.UPPERCASE)
        public String getName() {
            return "Alex";
        }
    }

    @Test
    public void methodTest() {
        MethodPojo pojo = new MethodPojo();
        CSVGenerator generator = new CSVGenerator(Arrays.asList(new MethodPojo[] {pojo}), ',');
        String csv = generator.generate();
        String expected = new StringBuilder()
                .append("Name\r\n")
                .append("ALEX\r\n")
                .toString();
        assertEquals(expected, csv);
    }
    
}
