package pdf;

import com.itextpdf.io.font.FontConstants;
import com.itextpdf.kernel.color.Color;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.geom.PageSize;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.Style;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Table;
import dao.BookDAO;
import entity.Book;
import pdf.builder.PdfCellBuilder;

import java.io.IOException;
import java.util.List;

/**
 * Created by mtumilowicz on 2017-07-05.
 */
public class PdfGenerationTest {
    public static final String DEST = "output/pdf/test.pdf";
    
    public static final PdfCellBuilder cellBuilder = new PdfCellBuilder();

    public static void main(String args[]) throws IOException {
        new PdfGenerationTest().createPdf(DEST);
    }

    public void createPdf(String dest) throws IOException {
        //Initialize PDF writer
        PdfWriter writer = new PdfWriter(dest);

        //Initialize PDF document
        PdfDocument pdf = new PdfDocument(writer);

        // Initialize document
        try (Document document = new Document(pdf, PageSize.A4.rotate())) {
            document.setMargins(20, 20, 20, 20);

            PdfFont font = PdfFontFactory.createFont(FontConstants.HELVETICA, "Cp1250", true);
            PdfFont bold = PdfFontFactory.createFont(FontConstants.HELVETICA_BOLD, "Cp1250", true);


            Style normal = new Style();
            
            Table table = new Table(new float[]{1, 1, 1, 1, 1, 1, 1, 1});
            table.setFixedLayout();
            table.setWidthPercent(100);
            table.addHeaderCell(new Cell().add("ID").setBackgroundColor(Color.LIGHT_GRAY));
            table.addHeaderCell(new Cell().add("Author").setBackgroundColor(Color.LIGHT_GRAY));
            table.addHeaderCell(new Cell().add("Title").setBackgroundColor(Color.LIGHT_GRAY));
            table.addHeaderCell(new Cell().add("Genre").setBackgroundColor(Color.LIGHT_GRAY));
            table.addHeaderCell(new Cell().add("Price").setBackgroundColor(Color.LIGHT_GRAY));
            table.addHeaderCell(new Cell().add("PubDate").setBackgroundColor(Color.LIGHT_GRAY));
            table.addHeaderCell(new Cell().add("Review").setBackgroundColor(Color.LIGHT_GRAY));
            table.addHeaderCell(new Cell().add("Type").setBackgroundColor(Color.LIGHT_GRAY));
            List<Book> allEntities = BookDAO.getAllEntities();
            allEntities.stream().forEach(book -> process(table, book));
            document.add(table);

        }
    }

    public void process(Table table, Book book) {
        table.addCell(cellBuilder.value(book.getId()).build());
        table.addCell(cellBuilder.value(book.getAuthor()).build());
        table.addCell(cellBuilder.value(book.getTitle()).build());
        table.addCell(cellBuilder.value(book.getGenre()).build());
        table.addCell(cellBuilder.value(book.getPrice()).build());
        table.addCell(cellBuilder.value(book.getPubDate()).build());
        table.addCell(cellBuilder.value(book.getReview()).build());
        table.addCell(cellBuilder.value(book.getType().name()).build());
    }
}
