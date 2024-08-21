package hexlet.code;

import hexlet.code.schemas.BaseSchema;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public final class MapSchemaTests {

    private Validator validator;

    @BeforeEach
    void setUp() {
        validator = new Validator();
    }

    @Test
    void testMapSchemaRequired() {
        var schema = validator.map();
        Map<String, String> testMap = new HashMap<>();
        assertTrue(schema.isValid(null));
        assertTrue(schema.isValid(testMap));
        schema.required();
        assertFalse(schema.isValid(null));
        assertTrue(schema.isValid(testMap));
        testMap.put("key1", "value1");
        assertTrue(schema.isValid(testMap));
    }

    @Test
    void testMapSchemaSizeOf() {
        var schema = validator.map();
        assertTrue(schema.isValid(null));
        Map<String, String> testMap = new HashMap<>();
        testMap.put("key1", "value1");
        assertTrue(schema.isValid(testMap));
        schema.sizeof(2);
        assertTrue(schema.isValid(null));
        assertFalse(schema.isValid(testMap));
        testMap.put("key2", "value2");
        assertTrue(schema.isValid(testMap));
    }

    @Test
    void testShapedMapSchema() {
        var schema = validator.map();
        Map<String, BaseSchema<String>> schemas = new HashMap<>();
        schemas.put("firstName", validator.string().required());
        schemas.put("lastName", validator.string().required().minLength(2));
        schema.shape(schemas);

        Map<String, String> human1 = new HashMap<>();
        human1.put("firstName", "John");
        human1.put("lastName", "Smith");
        assertTrue(schema.isValid(human1));

        Map<String, String> human2 = new HashMap<>();
        human2.put("firstName", "John");
        human2.put("lastName", null);
        assertFalse(schema.isValid(human2));

        Map<String, String> human3 = new HashMap<>();
        human3.put("firstName", "Anna");
        human3.put("lastName", "B");
        assertFalse(schema.isValid(human3));

        Map<String, Object> human4 = new HashMap<>();
        human4.put("name", "");
        human4.put("lastName", null);
        assertFalse(schema.isValid(human4));

        Map<String, Object> human5 = new HashMap<>();
        human5.put("name", null);
        human5.put("age", "Smith");
        assertFalse(schema.isValid(human5));
    }
}
