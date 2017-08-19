package core.bundle;

import java.util.ResourceBundle;

/**
 * Created by mtumilowicz on 2017-07-08.
 */
public final class EnumBundleHandler {

    private final static ResourceBundle bundle = ResourceBundle.getBundle("Enums");

    private EnumBundleHandler() {
    }

    public static <T extends Enum<T>> String get(T e) {
        return bundle.getString(e.getClass().getSimpleName() + '.' + e.name());
    }
}
