package io.ljunggren.report.generator.cell;

import java.lang.annotation.Annotation;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;

public class CellCatchAll extends CellChain {

    @Override
    public void format(Annotation annotation, Cell cell, Workbook workbook) {
        return;
    }

}
