package core.xml.validator;

import org.junit.Test;

import javax.xml.XMLConstants;

/**
 * Created by mtumilowicz on 2017-09-16.
 */
public class XmlValidatorWrapperTest {
    @Test
    public void correctImpl() {
        XmlValidatorWrapper validatorWrapper = XmlValidatorWrapper.Factory.newInstance("src/test/resources/bookSchema.xsd", XMLConstants.W3C_XML_SCHEMA_NS_URI);
        validatorWrapper.validate("src/test/resources/bookSchemaCorrectImpl.xml");
    }

    @Test(expected = XmlValidatorWrapper.XmlValidatorWrapperException.class)
    public void wrongImpl() {
        XmlValidatorWrapper validatorWrapper = XmlValidatorWrapper.Factory.newInstance("src/test/resources/bookSchema.xsd", XMLConstants.W3C_XML_SCHEMA_NS_URI);
        validatorWrapper.validate("src/test/resources/bookSchemaWrongImpl.xml");
    }
}
