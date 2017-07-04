package xml.validator;

import javax.xml.validation.Schema;
import javax.xml.validation.Validator;

/**
 * Created by mtumilowicz on 2017-06-25.
 */
public class XmlValidatorFactory {
    
    public static Validator newInstance(String schemaFileName, String schemaLanguage) {
        return new XmlSchemaFactory.Builder()
                .schemaLanguage(schemaLanguage)
                .fileName(schemaFileName)
                .build()
                .getSchema()
                .newValidator();
    }

    public static Validator newInstance(Schema schema) {
        return schema.newValidator();
    }
}
