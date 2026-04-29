package fi.metropolia.assignment21.subsystem;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.IOException;

/**
 * Subsystem: JSON parsing and lookup of the first matching attribute name in the tree.
 * <p>
 * Traversal is depth-first: own keys of an object are checked in iteration order, then nested
 * structures are visited. This satisfies "first one found" when the same logical name may
 * appear at different depths (e.g. nested objects in a larger payload).
 */
public final class JsonAttributeLookup {

    private JsonAttributeLookup() {
    }

    /**
     * Parses JSON text and returns the string form of the first value whose key equals
     * {@code attributeName} anywhere in the document.
     *
     * @throws IOException      if the body is not valid JSON
     * @throws IllegalArgumentException if no such attribute exists
     */
    public static String findFirstValue(String jsonBody, String attributeName) throws IOException {
        if (attributeName == null || attributeName.isBlank()) {
            throw new IllegalArgumentException("attributeName must not be blank");
        }
        Object root;
        try {
            root = new JSONParser().parse(jsonBody);
        } catch (ParseException e) {
            throw new IOException("Invalid JSON response", e);
        }
        String found = search(root, attributeName);
        if (found == null) {
            throw new IllegalArgumentException("No attribute named \"" + attributeName + "\" in JSON");
        }
        return found;
    }

    @SuppressWarnings("unchecked")
    private static String search(Object node, String attributeName) {
        if (node == null) {
            return null;
        }
        if (node instanceof JSONObject object) {
            for (Object keyObj : object.keySet()) {
                String key = String.valueOf(keyObj);
                if (attributeName.equals(key)) {
                    return stringify(object.get(keyObj));
                }
            }
            for (Object keyObj : object.keySet()) {
                String nested = search(object.get(keyObj), attributeName);
                if (nested != null) {
                    return nested;
                }
            }
        } else if (node instanceof JSONArray array) {
            for (Object item : array) {
                String nested = search(item, attributeName);
                if (nested != null) {
                    return nested;
                }
            }
        }
        return null;
    }

    private static String stringify(Object value) {
        if (value == null) {
            return "null";
        }
        if (value instanceof String s) {
            return s;
        }
        if (value instanceof Number || value instanceof Boolean) {
            return String.valueOf(value);
        }
        if (value instanceof JSONObject || value instanceof JSONArray) {
            return value.toString();
        }
        return String.valueOf(value);
    }
}
