package core.xml.validator;

import com.google.common.base.Preconditions;
import org.apache.commons.lang3.StringUtils;
import org.xml.sax.SAXException;

import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

/**
 * Created by mtumilowicz on 2017-06-25.
 */
public class XmlSchemaFactory {
    
    private final Schema schema;

    private XmlSchemaFactory(Schema schema) {
        this.schema = schema;
    }

    public Schema getSchema() {
        return schema;
    }

    public static class Builder {
        private String schemaPath;
        private String schemaLanguage;
        
        public Builder schemaPath(String schemaPath) {
            Preconditions.checkArgument(StringUtils.isNotEmpty(schemaPath));
            this.schemaPath = schemaPath;
            
            return this;
        }

        public Builder schemaLanguage(String schemaLanguage) {
            Preconditions.checkArgument(StringUtils.isNotEmpty(schemaLanguage));
            this.schemaLanguage = schemaLanguage;
            
            return this;
        }

        public XmlSchemaFactory build() {
            Preconditions.checkState(StringUtils.isNotEmpty(schemaLanguage));
            SchemaFactory factory = SchemaFactory.newInstance(schemaLanguage);
            Schema schema;
            
            try {
                Preconditions.checkState(StringUtils.isNotEmpty(schemaPath));
                schema = factory.newSchema(new StreamSource(schemaPath));
            } catch (SAXException e) {
                throw new XmlSchemaFactoryException(e.getLocalizedMessage());
            }
            
            return new XmlSchemaFactory(schema);
        }
    }

    private static class XmlSchemaFactoryException  extends RuntimeException  {
        private XmlSchemaFactoryException(String message) {
            super(message);
        }
    }
}
