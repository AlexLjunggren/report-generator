package io.ljunggren.report.generator.utils;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import io.ljunggren.report.generator.utils.GeneratorUtils;

public class GeneratorUtilsTest {

    @Test
    public void columnToIntTest() {
        assertEquals(GeneratorUtils.columnToInt("A"), 0);
        assertEquals(GeneratorUtils.columnToInt("Z"), 25);
        assertEquals(GeneratorUtils.columnToInt("AA"), 26);
        assertEquals(GeneratorUtils.columnToInt("AAA"), 702);
    }
    
    @Test
    public void columnNonLetterTest() throws Exception {
        assertEquals(GeneratorUtils.columnToInt("1"), 1);
        assertEquals(GeneratorUtils.columnToInt("0"), 0);
    }
    
    @Test(expected = RuntimeException.class)
    public void columnExceptionTest() {
        GeneratorUtils.columnToInt("*");
    }

}
