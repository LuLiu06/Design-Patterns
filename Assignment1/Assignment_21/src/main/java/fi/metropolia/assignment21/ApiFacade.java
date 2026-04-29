package fi.metropolia.assignment21;

import fi.metropolia.assignment21.subsystem.HttpJsonTransport;
import fi.metropolia.assignment21.subsystem.JsonAttributeLookup;

import java.io.IOException;

/**
 * Facade for simple JSON-over-HTTP GET access.
 * <p>
 * Hides {@link HttpJsonTransport} (connection lifecycle, status codes, streams) and
 * {@link JsonAttributeLookup} (parsing, tree search) behind one method suitable for client code.
 */
public class ApiFacade {

    /**
     * Sends an HTTP GET to {@code urlString}, reads the JSON body, and returns the value of the
     * first property named {@code attributeName} found in the document (depth-first).
     *
     * @param urlString      full URL for a GET request
     * @param attributeName JSON object key to locate (non-blank)
     * @return string representation of the value (strings as-is; numbers/booleans via {@link String#valueOf};
     *         objects/arrays via {@link Object#toString()})
     * @throws IOException              invalid URL, network failure, non-success HTTP status, empty body, or invalid JSON
     * @throws IllegalArgumentException attribute not found anywhere in the parsed JSON
     */
    public String getAttributeValueFromJson(String urlString, String attributeName)
            throws IllegalArgumentException, IOException {
        if (urlString == null || urlString.isBlank()) {
            throw new IOException("urlString must not be blank");
        }
        String json = HttpJsonTransport.getBody(urlString);
        if (json.isBlank()) {
            throw new IOException("Empty response body");
        }
        return JsonAttributeLookup.findFirstValue(json, attributeName);
    }
}
