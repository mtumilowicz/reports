package core.xml.converter;

import com.thoughtworks.xstream.converters.ConversionException;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.text.ParseException;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.mockito.Mockito.when;

/**
 * Created by mtumilowicz on 2017-08-20.
 */
@RunWith(MockitoJUnitRunner.class)
public class DateConverterTest {

    private static FastDateFormat fdf = FastDateFormat.getInstance("yyyy-MM-dd");
    
    @Mock
    HierarchicalStreamWriter writer;
    
    @Mock
    MarshallingContext marshallingContext;
    
    @Mock
    HierarchicalStreamReader reader;
    
    @Mock
    UnmarshallingContext unmarshallingContext;

    @Test(expected = IllegalArgumentException.class)
    public void marshalNullArgument() {
        new DateOnlyConverter().marshal(null, writer, marshallingContext);
    }

    @Test(expected = IllegalArgumentException.class)
    public void marshalIntegerArgument() {
        new DateOnlyConverter().marshal(10, writer, marshallingContext);
    }
    
    @Test
    public void marshalNotNullDateArgument() {
        new DateOnlyConverter().marshal(new Date(), writer, marshallingContext);
    }
    
    @Test
    public void unmarshalNullArgument() {
        when(reader.getValue()).thenReturn(null);
        assertNull(new DateOnlyConverter().unmarshal(reader, unmarshallingContext));
    }

    @Test
    public void unmarshalEmptyStringArgument() {
        when(reader.getValue()).thenReturn(StringUtils.EMPTY);
        assertNull(new DateOnlyConverter().unmarshal(reader, unmarshallingContext));
    }

    @Test(expected = ConversionException.class)
    public void unmarshalNotDateArgument() {
        when(reader.getValue()).thenReturn("not date string");
        new DateOnlyConverter().unmarshal(reader, unmarshallingContext);
    }

    @Test
    public void unmarshalDateArgument() throws ParseException {
        when(reader.getValue()).thenReturn("2017-09-02");
        assertEquals(fdf.parse("2017-09-02"), 
                new DateOnlyConverter().unmarshal(reader, unmarshallingContext));
    }
    
}
