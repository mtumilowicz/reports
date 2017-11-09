package xlsx.builder.cell;

import core.xlsx.builder.cell.XlsxCellBuilder;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * Created by mtumilowicz on 2017-11-09.
 */
public class XlsxCellBuilderTest {
    
    private XSSFRow row;
    private short dataFormat;

    @Before
    public void before() {
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet();
        row = sheet.createRow(0);

        dataFormat = workbook.createDataFormat().getFormat("0.00");

    }
    
    @Test(expected = IllegalArgumentException.class)
    public void nullForegroundColor() {
        new XlsxCellBuilder().foregroundColor(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullRowNegativeColumnStringValue() {
        new XlsxCellBuilder().cell(null, -1, StringUtils.EMPTY);
    }

    @Test(expected = IllegalArgumentException.class)
    public void notNullRowNegativeColumnStringValue() {
        new XlsxCellBuilder().cell(row, -1, StringUtils.EMPTY);
    }
    
    @Test(expected = IllegalArgumentException.class)
    public void nullRowNegativeColumnBigDecimalValue() {
        new XlsxCellBuilder().cell(null, -1, BigDecimal.ONE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void notNullRowNegativeColumnBigDecimalValue() {
        new XlsxCellBuilder().cell(row, -1, BigDecimal.ONE);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullRowNegativeColumnIntValue() {
        new XlsxCellBuilder().cell(null, -1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void notNullRowNegativeColumnIntValue() {
        new XlsxCellBuilder().cell(row, -1, 1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullRowNegativeColumnDateValue() {
        new XlsxCellBuilder().cell(null, -1, new Date(), dataFormat);
    }

    @Test(expected = IllegalArgumentException.class)
    public void notNullRowNegativeColumnDateValue() {
        new XlsxCellBuilder().cell(row, -1, new Date(), dataFormat);
    }
    
    @Test
    public void nullStringValue() {
        String expected = null;
        Cell cell = new XlsxCellBuilder().cell(row, 0, expected).build();
        
        assertEquals(StringUtils.EMPTY, cell.getStringCellValue());
    }

    @Test
    public void emptyStringValue() {
        String expected = StringUtils.EMPTY;
        Cell cell = new XlsxCellBuilder().cell(row, 0, expected).build();

        assertEquals(expected, cell.getStringCellValue());
    }

    @Test
    public void notEmptyStringValue() {
        String expected = "test";
        Cell cell = new XlsxCellBuilder().cell(row, 0, expected).build();

        assertEquals(expected, cell.getStringCellValue());
    }
}
