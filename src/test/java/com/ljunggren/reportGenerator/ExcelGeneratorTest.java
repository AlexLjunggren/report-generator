package com.ljunggren.reportGenerator;

import static org.junit.Assert.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.Test;

public class ExcelGeneratorTest {

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
		ExcelGenerator generator = new ExcelGenerator(data);
		List<String> expectedHeaders = Arrays.asList(new String[] {
				"Year", "Name", "Group", "Touch Date"
		});
		assertEquals(data, generator.getData());
		assertEquals(4, generator.getFields().size());
		assertEquals(expectedHeaders, generator.getHeaders());
		assertEquals(2, generator.getRecords().size());
		
		Record record1 = generator.getRecords().get(0);
		assertEquals(2020, record1.getItems().get(0).getValue());
		assertEquals("Alex", record1.getItems().get(1).getValue());
		assertEquals("Marketing", record1.getItems().get(2).getValue());
		assertEquals(date, record1.getItems().get(3).getValue());
		
		Record record2 = generator.getRecords().get(1);
		assertEquals(2021, record2.getItems().get(0).getValue());
		assertEquals("Chris", record2.getItems().get(1).getValue());
		assertEquals("Sales", record2.getItems().get(2).getValue());
		assertEquals(date, record2.getItems().get(3).getValue());
	}
	
	@Test
	public void generateTest() {
		ExcelGenerator generator = new ExcelGenerator(data);
		Workbook workbook = generator.generate();
		assertEquals(1, workbook.getNumberOfSheets());
		
		Sheet sheet = workbook.getSheetAt(0);
		assertEquals(3, sheet.getPhysicalNumberOfRows());

		Row headers = sheet.getRow(0);
		assertEquals("Year", headers.getCell(0).getStringCellValue());
		assertEquals("Name", headers.getCell(1).getStringCellValue());
		assertEquals("Group", headers.getCell(2).getStringCellValue());
		assertEquals("Touch Date", headers.getCell(3).getStringCellValue());

		Row row1 = sheet.getRow(1);
		assertEquals("2020", row1.getCell(0).getStringCellValue());
		assertEquals("ALEX", row1.getCell(1).getStringCellValue());
		assertEquals("Marketing", row1.getCell(2).getStringCellValue());
		assertEquals("2020-06-28", row1.getCell(3).getStringCellValue());

		Row row2 = sheet.getRow(2);
		assertEquals("2021", row2.getCell(0).getStringCellValue());
		assertEquals("CHRIS", row2.getCell(1).getStringCellValue());
		assertEquals("Sales", row2.getCell(2).getStringCellValue());
		assertEquals("2020-06-28", row2.getCell(3).getStringCellValue());
	}

}
