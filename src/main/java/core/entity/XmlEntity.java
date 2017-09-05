package core.entity;

import core.xml.converter.EntityToXmlConverter;

/**
 * Created by mtumilowicz on 2017-08-13.
 */
public class XmlEntity {
    
    public String toXmlString() {
        return EntityToXmlConverter.asString(this);
    }
}
