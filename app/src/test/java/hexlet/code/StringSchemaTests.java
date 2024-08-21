package hexlet.code;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class StringSchemaTests {

    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = new Validator();
    }

    @Test
    void testStringSchemaRequired() {
        var schema = validator.string();
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(""));
        assertTrue(schema.isValid("Test"));
        schema.required();
        assertFalse(schema.isValid(null));
        assertFalse(schema.isValid(""));
        assertTrue(schema.isValid("Test"));
    }

    @Test
    void testStringSchemaMinLength() {
        var schema = validator.string();
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid("Test"));
        schema.minLength(5);
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid("Test5"));
        assertFalse(schema.isValid("Test"));
    }

    @Test
    void testStringSchemaContains() {
        var schema = validator.string();
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid("Test"));
        schema.contains("Test5");
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid("Test5"));
        assertFalse(schema.isValid("Test6"));
    }

    @Test
    void testStringSchemaChained() {
        var schema = validator.string();
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid("Test"));
        schema.required().minLength(5).contains("Test5");
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid("Test5"));
        assertFalse(schema.isValid("Test6"));
    }
}
