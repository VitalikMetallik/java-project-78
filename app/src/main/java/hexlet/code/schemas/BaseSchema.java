package hexlet.code.schemas;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Predicate;

public abstract class BaseSchema<T> {
    private final Map<String, Predicate<T>> predicateMap = new HashMap<String, Predicate<T>>();

    public final boolean isValid(Object obj) {
        for (var e : predicateMap.entrySet()) {
            if (!e.getValue().test((T) obj)) {
                return false;
            }
        }
        return true;
    }

    public BaseSchema<T> required() {
        addValidation("required", o -> o != null);
        return this;
    }

    protected final void addValidation(String validation, Predicate<T> predicate) {
        predicateMap.put(validation, predicate);
    }
}
