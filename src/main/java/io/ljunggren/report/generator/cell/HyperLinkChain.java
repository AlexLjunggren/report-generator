package io.ljunggren.report.generator.cell;

import java.lang.annotation.Annotation;

import org.apache.poi.common.usermodel.HyperlinkType;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFHyperlink;

import io.ljunggren.report.generator.annotation.HyperLink;

public class HyperLinkChain extends CellChain {

    @Override
    public void format(Annotation annotation, Cell cell, Workbook workbook) {
        if (annotation.annotationType() == HyperLink.class) {
            format(cell, workbook);
            return;
        }
        nextChain.format(annotation, cell, workbook);
    }
    
    private void format(Cell cell, Workbook workbook) {
        CellStyle style = generateCellStyle(workbook);
        XSSFHyperlink link = generateLink(cell, workbook);
        cell.setHyperlink((XSSFHyperlink) link);
        cell.setCellStyle(style);
    }
    
    private CellStyle generateCellStyle(Workbook workbook) {
        CellStyle style = workbook.createCellStyle();
        Font font = workbook.createFont();
        font.setUnderline(Font.U_SINGLE);
        font.setColor(IndexedColors.BLUE.index);
        style.setFont(font);
        return style;
    }
    
    private XSSFHyperlink generateLink(Cell cell, Workbook workbook) {
        CreationHelper createHelper = workbook.getCreationHelper();
        XSSFHyperlink link = (XSSFHyperlink) createHelper.createHyperlink(HyperlinkType.URL);
        link.setAddress(cell.getStringCellValue());
        return link;
    }
    
}
