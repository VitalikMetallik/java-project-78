package hexlet.code.schemas;

public final class StringSchema extends BaseSchema<String> {

    public StringSchema required() {
        addValidation("required", o -> o != null && !o.isEmpty());
        return this;
    }

    public StringSchema minLength(int length) {
        addValidation("minLength", o -> o == null || o.length() > length - 1);
        return this;
    }

    public StringSchema contains(String substring) {
        addValidation("contains", o -> o == null || o.contains(substring));
        return this;
    }
}
