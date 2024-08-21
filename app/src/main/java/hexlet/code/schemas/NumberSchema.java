package hexlet.code.schemas;

public final class NumberSchema extends BaseSchema<Integer> {

    public NumberSchema required() {
        super.required();
        return this;
    }

    public NumberSchema positive() {
        addValidation("positive", o -> (o == null || o > 0));
        return this;
    }

    public NumberSchema range(int min, int max) {
        addValidation("range", o -> (o == null || (o >= min && o <= max)));
        return this;
    }
}
