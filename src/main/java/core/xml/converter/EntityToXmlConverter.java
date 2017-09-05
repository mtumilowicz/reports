package core.xml.converter;

import com.google.common.base.Preconditions;
import com.thoughtworks.xstream.XStream;

/**
 * Created by mtumilowicz on 2017-09-05.
 */
public final class EntityToXmlConverter {
    private EntityToXmlConverter() {
        
    }
    
    public static final String asString(Object obj) {
        Preconditions.checkArgument(obj != null);
        
        // it cannot be field (exception - Cannot marshal the XStream instance in action)
        final XStream xstream = new XStream();
        xstream.processAnnotations(obj.getClass());
        return xstream.toXML(obj);
    }
}
