package core.xml.validator;

import com.google.common.base.Preconditions;
import core.xml.schema.XmlSchemaFactory;
import org.apache.commons.lang3.StringUtils;

import javax.xml.validation.Validator;

/**
 * Created by mtumilowicz on 2017-06-25.
 */
final class XmlValidatorFactory {
    
    final static Validator newInstance(String filePath, String schemaLanguage) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(filePath));
        Preconditions.checkArgument(StringUtils.isNotEmpty(schemaLanguage));
        
        return new XmlSchemaFactory.Builder()
                .schemaLanguage(schemaLanguage)
                .schemaPath(filePath)
                .build()
                .getSchema()
                .newValidator();
    }
}
