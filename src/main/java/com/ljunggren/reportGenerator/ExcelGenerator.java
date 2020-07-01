package com.ljunggren.reportGenerator;

import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelGenerator extends Generator {

	public ExcelGenerator(List<?> data) {
		super(data);
	}

	@Override
	public Workbook generate() {
		Workbook workbook = generateWorkbook(getHeaders(), getRecords());
		return workbook;
	}
	
	private Workbook generateWorkbook(List<String> headers, List<Record> records) {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet();
		setHeaders(workbook, sheet, headers);
		setData(workbook, sheet, records);
		return workbook;
	}

	private void setHeaders(Workbook workbook, Sheet sheet, List<String> headers) {
		Row row = sheet.createRow(0);
		int index = 0;
		for (String header: headers) {
			Cell cell = row.createCell(index++);
			cell.setCellValue(header);
		}
	}
	
	private void setData(Workbook workbook, Sheet sheet, List<Record> records) {
		int rowIndex = 1;
		for (Record record: records) {
			Row row = sheet.createRow(rowIndex++);
			int itemIndex = 0;
			for (Item item: record.getItems()) {
				Cell cell = row.createCell(itemIndex++);
				cell.setCellValue(getValueFromItem(item));
			}
		}
	}
	
}
