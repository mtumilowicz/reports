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
    
    public void defaultConstructor_GeneralProperties() {
        assertEquals(new BundleHandler().get("test.key"), "testValue");
    }

    @Test
    public void stringBundleValue() {
        BundleHandler bundle = new BundleHandler(this.getClass());
        assertEquals(bundle.get("test.key"), "testValue");
    }

    @Test
    public void enumBundleValue() {
        BundleHandler bundle = new BundleHandler(this.getClass());
        assertEquals(bundle.get(BundleHandlerTestEnum.TEST), "enumValue");
    }
}
