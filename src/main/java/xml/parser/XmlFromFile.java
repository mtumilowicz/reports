package xml.parser;

import com.google.common.base.Preconditions;
import com.thoughtworks.xstream.XStream;
import org.apache.commons.lang3.StringUtils;

import java.io.File;

/**
 * Created by mtumilowicz on 2017-08-13.
 */
public class XmlFromFile {
    public static <T> T parse(String path, Class<T> clazz) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(path));
        Preconditions.checkArgument(clazz != null);
        final XStream xstream = new XStream();
        xstream.processAnnotations(clazz);
        return (T) xstream.fromXML(new File(path));
    }
}
