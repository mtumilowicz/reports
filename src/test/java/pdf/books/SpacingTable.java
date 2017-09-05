package pdf.books;

import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Table;
import core.pdf.builder.PdfCellBuilder;

/**
 * Created by mtumilowicz on 2017-09-05.
 */
final class SpacingTable {

    private SpacingTable() {
    }

    static void insertInto(Document doc) {
        Table table = new Table(new float[]{1});
        table.setDocument(doc);

        table.setWidthPercent(100)
                .setHeight(50)
                .addCell(PdfCellBuilder.EMPTY_CELL)
                .complete();
    }
}
