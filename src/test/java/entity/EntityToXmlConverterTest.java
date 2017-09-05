package entity;

import core.builder.GenericBuilder;
import org.custommonkey.xmlunit.XMLUnit;
import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

import static org.custommonkey.xmlunit.XMLAssert.assertXMLEqual;
import static org.custommonkey.xmlunit.XMLAssert.assertXMLNotEqual;
import static org.junit.Assert.assertNull;

/**
 * Created by mtumilowicz on 2017-09-03.
 */
public class EntityToXmlConverterTest {
    private String control = "<book id=\"bk001\">\n" +
            "        <author>Writer</author>\n" +
            "        <title>The First Book</title>\n" +
            "        <genre>Fiction</genre>\n" +
            "        <price>44.45</price>\n" +
            "        <pub-date>2017-09-02</pub-date>\n" +
            "        <review>An amazing story of nothing.</review>\n" +
            "        <type>PAPER</type>\n" +
            "    </book>";

    @Before
    public void setUp() {
        XMLUnit.setNormalizeWhitespace(true);
    }
    
    @Test
    public void toXmlTestPositive() {
        Book test = GenericBuilder.of(Book::new)
                .with(Book::setId, "bk001")
                .with(Book::setAuthor, "Writer")
                .with(Book::setTitle, "The First Book")
                .with(Book::setGenre, "Fiction")
                .with(Book::setPrice, new BigDecimal("44.45"))
                .with(Book::setPubDate, new Date(Long.valueOf("1504303200000")))
                .with(Book::setReview, "An amazing story of nothing.")
                .with(Book::setType, BookType.PAPER)
                .build();

        try {
            XMLUnit.setNormalizeWhitespace(true);
            assertXMLEqual(control, test.toXmlString());
        } catch (SAXException | IOException e) {
            assertNull(e);
        }
    }

    @Test
    public void toXmlTestNegative() {
        Book test = GenericBuilder.of(Book::new)
                .with(Book::setId, "bk001")
                .with(Book::setAuthor, "WRONG")
                .with(Book::setTitle, "The First Book")
                .with(Book::setGenre, "Fiction")
                .with(Book::setPrice, new BigDecimal("44.45"))
                .with(Book::setPubDate, new Date(Long.valueOf("1504303200000")))
                .with(Book::setReview, "An amazing story of nothing.")
                .with(Book::setType, BookType.PAPER)
                .build();

        try {
            XMLUnit.setNormalizeWhitespace(true);
            assertXMLNotEqual(control, test.toXmlString());
        } catch (SAXException | IOException e) {
            assertNull(e);
        }
    }
}
