package xml;

import entity.Book;
import org.xml.sax.SAXException;
import core.xml.parser.XmlFromFile;
import core.xml.validator.XmlValidatorWrapper;

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

        Book book = new Book.Builder()
                .id("1")
                .title("title")
                .author("writer")
                .price(BigDecimal.TEN)
                .pubDate(new Date())
                .build();

        String s = book.toXmlString();
        System.out.println(s);
        
        Book bookFromXml = XmlFromFile.parse("src/main/resources/book.xml", Book.class);
        System.out.println(bookFromXml);
    }
}
