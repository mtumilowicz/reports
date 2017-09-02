package core.xml.converter;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import core.date.CoreDateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by mtumilowicz on 2017-06-25.
 */
public class DateOnlyConverter implements Converter {
    
    private final FastDateFormat fdf = CoreDateUtils.DATE_ONLY;

    public DateOnlyConverter() {
        super();
    }

    public boolean canConvert(Class clazz) {
        return Date.class.isAssignableFrom(clazz);
    }

    public void marshal(Object value, HierarchicalStreamWriter writer,
                        MarshallingContext context) {
        Date date = (Date) value;
        writer.setValue(fdf.format(date));
}

    public Object unmarshal(HierarchicalStreamReader reader,
                            UnmarshallingContext context) {
        String dateToParse = reader.getValue();
        if (StringUtils.isEmpty(dateToParse)) {
            return null;
        }
        try {
            return fdf.parse(dateToParse);
        } catch (ParseException e) {
            throw new ConversionException(e.getMessage(), e);
        }
    }

}
