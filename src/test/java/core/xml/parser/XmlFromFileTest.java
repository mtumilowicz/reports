package core.xml.parser;

import com.thoughtworks.xstream.io.StreamException;
import entity.Book;
import entity.BookType;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

/**
 * Created by mtumilowicz on 2017-08-20.
 */
public class XmlFromFileTest {

    @Test(expected = IllegalArgumentException.class)
    public void parseNullPath_NotNullClass() {
        XmlFromFile.parse(null, Book.class);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parseNullPath_NullClass() {
        XmlFromFile.parse(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void parsePathExists_NullClass() {
        XmlFromFile.parse("src/test/resources/book.xml", null);
    }

    @Test(expected = StreamException.class)
    public void parsePathNotExists_NotNullClass() {
        XmlFromFile.parse("not/exists/path", Book.class);
    }
    
    @Test
    public void parseFullSuccess() {
        Book control = new Book.Builder()
                .id("bk001")
                .author("Writer")
                .title("The First Book")
                .genre("Fiction")
                .price(new BigDecimal("44.45"))
                .pubDate(new Date(Long.valueOf("1504303200000")))
                .review("An amazing story of nothing.")
                .type(BookType.PAPER)
                .build();
        
        assertEquals(control, XmlFromFile.parse("src/test/resources/book.xml", Book.class));
    }

    @Test
    public void parseFullFail() {
        Book control = new Book.Builder()
                .id("bk001")
                .author("Writer")
                .title("WRONG")
                .genre("Fiction")
                .price(new BigDecimal("44.45"))
                .pubDate(new Date(Long.valueOf("1504303200000")))
                .review("An amazing story of nothing.")
                .type(BookType.PAPER)
                .build();

        assertNotEquals(control, XmlFromFile.parse("src/test/resources/book.xml", Book.class));
    }
}
