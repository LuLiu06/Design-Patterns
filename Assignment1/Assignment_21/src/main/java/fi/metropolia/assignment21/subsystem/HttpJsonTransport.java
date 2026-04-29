package fi.metropolia.assignment21.subsystem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

/**
 * Subsystem: synchronous HTTP GET and raw response body handling.
 * Used only through the facade in this assignment.
 */
public final class HttpJsonTransport {

    private static final int CONNECT_TIMEOUT_MS = 10_000;
    private static final int READ_TIMEOUT_MS = 15_000;

    private HttpJsonTransport() {
    }

    /**
     * Performs a GET request and returns the response body as a UTF-8 string.
     *
     * @throws IOException if the URL is invalid, connection fails, or HTTP status is not 2xx
     */
    public static String getBody(String urlString) throws IOException {
        final URI uri;
        try {
            uri = new URI(urlString);
        } catch (URISyntaxException e) {
            throw new IOException("Invalid URL: " + urlString, e);
        }

        HttpURLConnection connection = null;
        try {
            connection = (HttpURLConnection) uri.toURL().openConnection();
            connection.setRequestMethod("GET");
            connection.setConnectTimeout(CONNECT_TIMEOUT_MS);
            connection.setReadTimeout(READ_TIMEOUT_MS);
            connection.setRequestProperty("Accept", "application/json");

            int code = connection.getResponseCode();
            if (code < 200 || code >= 300) {
                String errBody = readStreamFully(connection.getErrorStream());
                throw new IOException("HTTP " + code + (errBody.isBlank() ? "" : (": " + errBody)));
            }

            return readStreamFully(connection.getInputStream());
        } finally {
            if (connection != null) {
                connection.disconnect();
            }
        }
    }

    private static String readStreamFully(InputStream stream) throws IOException {
        if (stream == null) {
            return "";
        }
        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(stream, StandardCharsets.UTF_8))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        }
    }
}
