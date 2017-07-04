package xml.validator;

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
        private String fileName;
        private String schemaLanguage;
        
        public Builder fileName(String fileName) {
            this.fileName = fileName;
            
            return this;
        }

        public Builder schemaLanguage(String schemaLanguage) {
            this.schemaLanguage = schemaLanguage;
            
            return this;
        }

        public XmlSchemaFactory build() {
            SchemaFactory factory = SchemaFactory.newInstance(schemaLanguage);
            Schema schema;
            
            try {
                schema = factory.newSchema(new StreamSource(fileName));
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
