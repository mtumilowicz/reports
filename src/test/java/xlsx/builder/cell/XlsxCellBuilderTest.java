package xlsx.builder.cell;

import core.xlsx.builder.cell.XlsxCellBuilder;
import org.junit.Test;

/**
 * Created by mtumilowicz on 2017-11-09.
 */
public class XlsxCellBuilderTest {
    
    @Test(expected = IllegalArgumentException.class)
    public void nullForegroundColor() {
        new XlsxCellBuilder().foregroundColor(null);
    }
    
}
