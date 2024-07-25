package io.ljunggren.report.generator.excel;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import io.ljunggren.report.generator.Generator;
import io.ljunggren.report.generator.Item;
import io.ljunggren.report.generator.Record;
import io.ljunggren.report.generator.cell.CellCatchAll;
import io.ljunggren.report.generator.cell.HyperlinkChain;

public class ExcelGenerator extends Generator {

	public ExcelGenerator(List<?> data) {
		super(data);
	}

	@Override
	public Workbook generate() {
		Workbook workbook = new XSSFWorkbook();
		Sheet sheet = workbook.createSheet();
		setHeaders(workbook, sheet, getHeaders());
		setData(workbook, sheet, getRecords());
        setAutoSizedColumns(sheet, getAutoSizedColumns());
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
				formatCell(item, cell, workbook);
			}
		}
	}
	
	private void formatCell(Item item, Cell cell, Workbook workbook) {
	    Annotation[] annotations = item.getAnnotations();
	    Arrays.stream(annotations).forEach(annotation -> processChain(annotation, cell, workbook));
	}
	
    private void processChain(Annotation annotation, Cell cell, Workbook workbook) {
        new HyperlinkChain().nextChain(
                new CellCatchAll()
                        ).format(annotation, cell, workbook);
    }

    private void setAutoSizedColumns(Sheet sheet, List<Integer> autoSizedColumns) {
        autoSizedColumns.stream().forEach(column -> sheet.autoSizeColumn(column));
    }
    
}
