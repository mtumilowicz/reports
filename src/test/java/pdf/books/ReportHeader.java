package pdf.books;

import com.itextpdf.layout.element.Table;
import core.pdf.writer.AbstractInsertablePdfTable;

/**
 * Created by mtumilowicz on 2017-09-05.
 */
final class ReportHeader extends AbstractInsertablePdfTable {

    @Override
    public Table get() {
        Table table = new Table(new float[]{1});
        table.setWidthPercent(100)
                .addHeaderCell(
                        getCellBuilder()
                                .value(getBundles().get("report.header"))
                                .center()
                                .noBorder()
                                .singleCellFontSize(20)
                                .build());
        
        return table;
    }
}
