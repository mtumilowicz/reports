package core;

import java.util.ResourceBundle;

/**
 * Created by mtumilowicz on 2017-07-08.
 */
public final class BundleHandler {
    
    private final String clazzName;

    public BundleHandler(String clazzName) {
        this.clazzName = clazzName;
    }

    public String get(String value) {
        return ResourceBundle.getBundle(clazzName).getString(value);
    }
}
