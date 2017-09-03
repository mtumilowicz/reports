package xml;

import core.builder.GenericBuilder;
import core.xml.validator.XmlValidatorWrapper;
import entity.Book;
import entity.BookType;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by mtumilowicz on 2017-06-25.
 */
public class Main {
    public static void main(String[] args) {
        XmlValidatorWrapper validatorWrapper = XmlValidatorWrapper.Factory.newInstance("src/main/resources/bookSchema.xsd", XMLConstants.W3C_XML_SCHEMA_NS_URI);
        try {
            validatorWrapper.validate("src/main/resources/bookSchemaImpl.xml");
        } catch (SAXException | IOException e) {
            System.out.println(e.getLocalizedMessage());
        }

        Book book = GenericBuilder.of(Book::new)
                .with(Book::setId, "1")
                .with(Book::setAuthor, "Mrożek Sławomir")
                .with(Book::setTitle, "Tango")
                .with(Book::setGenre, "Plays")
                .with(Book::setPrice, BigDecimal.TEN)
                .with(Book::setPubDate, new Date())
                .with(Book::setReview, "3/5")
                .with(Book::setType, BookType.PAPER)
                .build();
        
        String s = book.toXmlString();
        System.out.println(s);
    }
}
