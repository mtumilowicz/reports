package core.entity;

import com.thoughtworks.xstream.XStream;

/**
 * Created by mtumilowicz on 2017-08-13.
 */
public class XmlEntity {
    
    public String toXmlString() {
        // it cannot be field (exception - Cannot marshal the XStream instance in action)
        final XStream xstream = new XStream();
        xstream.processAnnotations(this.getClass());
        return xstream.toXML(this);
    }
}
