package pdf.books;

import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Image;
import core.pdf.builder.ImageBuilder;

/**
 * Created by mtumilowicz on 2017-09-05.
 */
final class HarvardEmblem {

    private HarvardEmblem() {
    }

    static void insertInto(Document document) {
        Image icon = ImageBuilder.Factory.get("src/main/resources/harvard.png")
                .widthAndHeight(100, 100)
                .position(document.getLeftMargin(),
                        PageSize.A4.rotate().getHeight() - document.getTopMargin() - 100)
                .build();

        document.add(icon);
    }
}
