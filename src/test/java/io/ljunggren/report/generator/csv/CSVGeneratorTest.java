package io.ljunggren.report.generator.csv;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import io.ljunggren.report.generator.Record;
import io.ljunggren.report.generator.TestPojo;

public class CSVGeneratorTest {
	
	private Date date = createDate();
	private List<TestPojo> data = Arrays.asList(new TestPojo[] {
			TestPojo.builder().name("Alex").year(2020).city("Indianapolis").group("Marketing").touchDate(date).build(),
			TestPojo.builder().name("Chris").year(2021).city("Salt Lake City").group("Sales").touchDate(date).build(),
	});
	
	private Date createDate() {
		try {
			return new SimpleDateFormat("yyyy-MM-dd").parse("2020-06-28");
		} catch (ParseException e) {
			return null;
		}
	}

	@Test
	public void generatorConstructorTest() {
		CSVGenerator generator = new CSVGenerator(data, ',');
		List<String> expectedHeaders = Arrays.asList(new String[] {
				"Year", "Name", "Group", "Application", "Touch Date"
		});
		assertEquals(data, generator.getData());
		assertEquals(expectedHeaders, generator.getHeaders());
		assertEquals(2, generator.getRecords().size());
		
		Record record1 = generator.getRecords().get(0);
		assertEquals(2020, record1.getItems().get(0).getValue());
		assertEquals("Alex", record1.getItems().get(1).getValue());
		assertEquals("Marketing", record1.getItems().get(2).getValue());
        assertEquals("Report Generator", record1.getItems().get(3).getValue());
		assertEquals(date, record1.getItems().get(4).getValue());
		
		Record record2 = generator.getRecords().get(1);
		assertEquals(2021, record2.getItems().get(0).getValue());
		assertEquals("Chris", record2.getItems().get(1).getValue());
		assertEquals("Sales", record2.getItems().get(2).getValue());
        assertEquals("Report Generator", record1.getItems().get(3).getValue());
		assertEquals(date, record2.getItems().get(4).getValue());
	}
	
	@Test
	public void generateTest() {
		CSVGenerator generator = new CSVGenerator(data, ',');
		String csv = generator.generate();
		String expected = new StringBuilder()
				.append("Year,Name,Group,Application,Touch Date\r\n")
				.append("2020,ALEX,Marketing,Report Generator,2020-06-28\r\n")
				.append("2021,CHRIS,Sales,Report Generator,2020-06-28\r\n")
				.toString();
		assertEquals(expected, csv);
	}
	
	@Test
	public void generateWithPipeDelimiterTest() {
		CSVGenerator generator = new CSVGenerator(data, '|');
		String csv = generator.generate();
		String expected = new StringBuilder()
				.append("Year|Name|Group|Application|Touch Date\r\n")
				.append("2020|ALEX|Marketing|Report Generator|2020-06-28\r\n")
				.append("2021|CHRIS|Sales|Report Generator|2020-06-28\r\n")
				.toString();
		assertEquals(expected, csv);
	}
	
	@Test
	public void generateNullTest() {
		CSVGenerator generator = new CSVGenerator(null, ',');
		String csv = generator.generate();
		assertEquals("\r\n", csv);
	}

}
