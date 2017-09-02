package core.xml.converter;

import com.google.common.base.Preconditions;
import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import core.date.CoreDateUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by mtumilowicz on 2017-06-25.
 */
public class DateOnlyConverter implements Converter {

    public DateOnlyConverter() {
        super();
    }

    public boolean canConvert(Class clazz) {
        return Date.class.isAssignableFrom(clazz);
    }

    public void marshal(Object value, HierarchicalStreamWriter writer,
                        MarshallingContext context) {
        Preconditions.checkArgument(value instanceof Date);
        writer.setValue(CoreDateUtils.DATE_ONLY.format((Date) value));
}

    public Object unmarshal(HierarchicalStreamReader reader,
                            UnmarshallingContext context) {
        String dateToParse = reader.getValue();
        if (StringUtils.isEmpty(dateToParse)) {
            return null;
        }
        try {
            return CoreDateUtils.DATE_ONLY.parse(dateToParse);
        } catch (ParseException e) {
            throw new ConversionException(e.getMessage(), e);
        }
    }

}
