package core.bundle;

import com.google.common.base.Preconditions;

import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Created by mtumilowicz on 2017-07-08.
 */
public final class BundleHandler {
    
    private final ResourceBundle bundle;

    public BundleHandler(Class clazz) {
        Preconditions.checkArgument(clazz != null);
        bundle = ResourceBundle.getBundle(clazz.getSimpleName());
    }

    public String get(String value) {
        return Objects.requireNonNull(bundle.getString(value));
    }
    
    public <T extends Enum<T>> String get(T e) {
        return get(e.getClass().getSimpleName() + '.' + e.name());
    }
}
