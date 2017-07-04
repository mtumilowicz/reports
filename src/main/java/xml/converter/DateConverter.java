package xml.converter;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import org.apache.commons.lang3.time.FastDateFormat;

import java.text.ParseException;
import java.util.Date;

/**
 * Created by mtumilowicz on 2017-06-25.
 */
public class DateConverter implements Converter {
    
    private final FastDateFormat fdf = FastDateFormat.getInstance("yyyy-MM-dd");

    public DateConverter() {
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
        Date date;
        try {
            date = fdf.parse(reader.getValue());
        } catch (ParseException e) {
            throw new ConversionException(e.getMessage(), e);
        }
        return date;
    }

}
