package core.xml.validator;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;

import javax.xml.validation.Validator;

/**
 * Created by mtumilowicz on 2017-06-25.
 */
public class XmlValidatorFactory {
    
    public static Validator newInstance(String schemaFileName, String schemaLanguage) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(schemaFileName));
        Preconditions.checkArgument(StringUtils.isNotEmpty(schemaLanguage));
        
        return new XmlSchemaFactory.Builder()
                .schemaLanguage(schemaLanguage)
                .schemaPath(schemaFileName)
                .build()
                .getSchema()
                .newValidator();
    }
}
