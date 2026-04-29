package fi.metropolia.assignment21;

import java.io.IOException;

/**
 * Demonstrates {@link ApiFacade}: Chuck Norris joke, a second public API, and error handling.
 */
public class Main {

    private static final String CHUCK_URL = "https://api.chucknorris.io/jokes/random";
    private static final String FX_URL = "https://api.fxratesapi.com/latest";

    public static void main(String[] args) {
        ApiFacade api = new ApiFacade();

        System.out.println("=== Assignment 21: API Facade ===\n");

        // 1) Chuck Norris (same data as original JokeClient, via facade)
        System.out.println("1) Random Chuck Norris joke (attribute \"value\"):");
        try {
            String joke = api.getAttributeValueFromJson(CHUCK_URL, "value");
            System.out.println("   " + joke);
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("   Failed: " + e.getMessage());
        }

        // 2) Second API — latest FX rates (base currency code)
        System.out.println("\n2) FX rates API (attribute \"base\"):");
        try {
            String base = api.getAttributeValueFromJson(FX_URL, "base");
            System.out.println("   Base currency: " + base);
            String date = api.getAttributeValueFromJson(FX_URL, "date");
            System.out.println("   Rate date: " + date);
        } catch (IOException | IllegalArgumentException e) {
            System.out.println("   Failed (network or API change): " + e.getMessage());
        }

        // 3) Error: missing attribute -> IllegalArgumentException
        System.out.println("\n3) Error demo — attribute not in JSON (expect IllegalArgumentException):");
        try {
            api.getAttributeValueFromJson(CHUCK_URL, "thisKeyDoesNotExist");
        } catch (IllegalArgumentException e) {
            System.out.println("   Caught: " + e.getMessage());
        } catch (IOException e) {
            System.out.println("   Unexpected IOException: " + e.getMessage());
        }

        // 4) Error: bad host / connection failure -> IOException
        System.out.println("\n4) Error demo — invalid host (expect IOException):");
        try {
            api.getAttributeValueFromJson("https://this-host-should-not-resolve-12345.invalid/json", "x");
        } catch (IOException e) {
            System.out.println("   Caught: " + e.getClass().getSimpleName() + ": " + e.getMessage());
        }

        System.out.println("\n=== Done ===");
    }
}
