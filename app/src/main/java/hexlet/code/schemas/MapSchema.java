package hexlet.code.schemas;

import java.util.Map;

public final class MapSchema extends BaseSchema<Map> {

    public MapSchema required() {
        super.required();
        return this;
    }

    public MapSchema sizeof(int size) {
        addValidation("sizeof", o -> (o == null || o.size() == size));
        return this;
    }

    public MapSchema shape(Map<String, BaseSchema<String>> schemasMap) {
        addValidation("shape", o -> {
            for (var e : schemasMap.entrySet()) {
                if (!e.getValue().isValid(o.get(e.getKey()))) {
                    return false;
                }
            }
            return true;
        });
        return this;
    }
}
