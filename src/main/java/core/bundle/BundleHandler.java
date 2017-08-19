package core.bundle;

import java.util.ResourceBundle;

/**
 * Created by mtumilowicz on 2017-07-08.
 */
public final class BundleHandler {
    
    private final String clazzName;

    public BundleHandler(Class clazz) {
        this.clazzName = clazz.getSimpleName();
    }

    public String get(String value) {
        return ResourceBundle.getBundle(clazzName).getString(value);
    }
    
    public <T extends Enum<T>> String get(T e) {
        return EnumBundleHandler.get(e);
    }
}
