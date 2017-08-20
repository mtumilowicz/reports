package core.xml.validator;

import core.dom.StaticDomDocumentBuilderFactory;
import org.xml.sax.SAXException;

import javax.xml.transform.dom.DOMSource;
import javax.xml.validation.Validator;
import java.io.IOException;
import java.util.Objects;

/**
 * Created by mtumilowicz on 2017-06-25.
 */
public class XmlValidatorWrapper {
    
    private final Validator validator;

    private XmlValidatorWrapper(Validator validator) {
        this.validator = validator;
    }
    
    public void validate(String xmlFilePath) throws SAXException, IOException {
        validator.validate(new DOMSource(StaticDomDocumentBuilderFactory.parse(Objects.requireNonNull(xmlFilePath))));
        System.out.println("Xml is correct!");
    }
    
    public static final class Factory {
        public static XmlValidatorWrapper newInstance(String xmlSchemaName, String schemaLanguage) {
            return new XmlValidatorWrapper(
                    XmlValidatorFactory.newInstance(Objects.requireNonNull(xmlSchemaName), 
                            Objects.requireNonNull(schemaLanguage)));
        }
    }
}
