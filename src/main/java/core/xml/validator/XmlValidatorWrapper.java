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
    
    public void validate(String filePath) {
        try {
            validator.validate(new DOMSource(StaticDomDocumentBuilderFactory.parse(Objects.requireNonNull(filePath))));
        } catch (SAXException | IOException e) {
            throw new XmlValidatorWrapperException(e.getLocalizedMessage());
        }
    }
    
    public static final class Factory {
        public static XmlValidatorWrapper newInstance(String filePath, String schemaLanguage) {
            return new XmlValidatorWrapper(
                    XmlValidatorFactory.newInstance(Objects.requireNonNull(filePath), 
                            Objects.requireNonNull(schemaLanguage)));
        }
    }
    
    public static class XmlValidatorWrapperException extends RuntimeException {
        public XmlValidatorWrapperException(String message) {
            super(message);
        }
    }
}
