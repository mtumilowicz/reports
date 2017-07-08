package xml.validator;

import core.StaticDomDocumentBuilderFactory;
import org.xml.sax.SAXException;

import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Validator;
import java.io.IOException;

/**
 * Created by mtumilowicz on 2017-06-25.
 */
public class XmlValidatorWrapper {
    
    private final Validator validator;

    private XmlValidatorWrapper(Validator validator) {
        this.validator = validator;
    }
    
    public void validate(String xmlFilePath) {
        try {
            validator.validate(new DOMSource(StaticDomDocumentBuilderFactory.parse(xmlFilePath)));
            System.out.println("Xml is correct!");
        } catch (SAXException | IOException e) {
            System.out.println(e.getLocalizedMessage());
        }
    }
    
    public static final class Factory {
        public static XmlValidatorWrapper newInstance(String xmlSchemaName, String schemaLanguage) {
            return new XmlValidatorWrapper(XmlValidatorFactory.newInstance(xmlSchemaName, schemaLanguage));
        }
    }
}
