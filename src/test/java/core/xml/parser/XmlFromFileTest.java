package core.xml.parser;

import com.thoughtworks.xstream.io.StreamException;
import core.builder.GenericBuilder;
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
        Book control = GenericBuilder.of(Book::new)
                .with(Book::setId, "bk001")
                .with(Book::setAuthor, "Writer")
                .with(Book::setTitle, "The First Book")
                .with(Book::setGenre, "Fiction")
                .with(Book::setPrice, new BigDecimal("44.45"))
                .with(Book::setPubDate, new Date(Long.valueOf("1504303200000")))
                .with(Book::setReview, "An amazing story of nothing.")
                .with(Book::setType, BookType.PAPER)
                .build();
        assertEquals(control, XmlFromFile.parse("src/test/resources/book.xml", Book.class));
    }

    @Test
    public void parseFullFail() {
        Book control = GenericBuilder.of(Book::new)
                .with(Book::setId, "bk001")
                .with(Book::setAuthor, "WRONG")
                .with(Book::setTitle, "The First Book")
                .with(Book::setGenre, "Fiction")
                .with(Book::setPrice, new BigDecimal("44.45"))
                .with(Book::setPubDate, new Date(Long.valueOf("1504303200000")))
                .with(Book::setReview, "An amazing story of nothing.")
                .with(Book::setType, BookType.PAPER)
                .build();

        assertNotEquals(control, XmlFromFile.parse("src/test/resources/book.xml", Book.class));
    }
}
