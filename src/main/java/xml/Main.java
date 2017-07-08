package xml;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.converters.extended.RegexPatternConverter;
import entity.Book;
import xml.validator.XmlValidatorWrapper;

import javax.xml.XMLConstants;
import java.io.File;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by mtumilowicz on 2017-06-25.
 */
public class Main {
    public static void main(String[] args) {
        XmlValidatorWrapper validatorWrapper = XmlValidatorWrapper.Factory.newInstance("src/main/resources/bookSchema.xsd", XMLConstants.W3C_XML_SCHEMA_NS_URI);
        validatorWrapper.validate("src/main/resources/bookSchemaImpl.xml");

        XStream xstream = new XStream();
        xstream.processAnnotations(Book.class);

        new RegexPatternConverter();

        Book book = new Book.Builder().id("1")
                .title("title")
                .author("writer")
                .price(BigDecimal.TEN)
                .pubDate(new Date())
                .build();

        String s = xstream.toXML(book);
        System.out.println(s);

        Book bookFromXml = (Book) xstream.fromXML(new File("src/main/resources/book.xml"));
        System.out.println(bookFromXml);
    }
}
