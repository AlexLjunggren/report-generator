package io.ljunggren.report.generator.cell;

import java.lang.annotation.Annotation;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Workbook;

public abstract class CellChain {
    
    protected CellChain nextChain;
    
    public CellChain nextChain(CellChain nextChain) {
        this.nextChain = nextChain;
        return this;
    }
    
    public abstract void format(Annotation annotation, Cell cell, Workbook workbook);

}
