package fi.metropolia.assignment6.printer;

import java.util.Base64;

/**
 * EncryptedPrinter decorator that encrypts the message before printing.
 * Uses Base64 encoding for simple, reversible encryption.
 * 
 * To decrypt: use Base64.getDecoder().decode(encryptedString)
 */
public class EncryptedPrinter extends PrinterDecorator {
    
    public EncryptedPrinter(Printer printer) {
        super(printer);
    }
    
    @Override
    public void print(String message) {
        String encryptedMessage = encrypt(message);
        wrappedPrinter.print(encryptedMessage);
    }
    
    /**
     * Encrypts the message using Base64 encoding.
     * @param message the original message
     * @return the encrypted message
     */
    private String encrypt(String message) {
        return Base64.getEncoder().encodeToString(message.getBytes());
    }
    
    /**
     * Decrypts a Base64 encoded message.
     * @param encryptedMessage the encrypted message
     * @return the original message
     */
    public static String decrypt(String encryptedMessage) {
        byte[] decodedBytes = Base64.getDecoder().decode(encryptedMessage);
        return new String(decodedBytes);
    }
}

