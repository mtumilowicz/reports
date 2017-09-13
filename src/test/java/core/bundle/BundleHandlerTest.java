package core.bundle;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Created by mtumilowicz on 2017-08-19.
 */
public class BundleHandlerTest {
    public enum BundleHandlerTestEnum {
        TEST
    }

    @Test(expected = IllegalArgumentException.class)
    public void nullArgumentInConstructor() {
        new BundleHandler(null);
    }
    
    @Test
    public void defaultConstructor_GeneralProperties() {
        assertEquals("testValue", new BundleHandler().get("test.key"));
    }

    @Test
    public void stringBundleValue() {
        BundleHandler bundle = new BundleHandler(this.getClass());
        assertEquals("testValue", bundle.get("test.key"));
    }

    @Test
    public void enumBundleValue() {
        BundleHandler bundle = new BundleHandler(this.getClass());
        assertEquals("enumValue", bundle.get(BundleHandlerTestEnum.TEST));
    }
}
