package fi.metropolia.assignment6;

import fi.metropolia.assignment6.printer.*;

/**
 * Main class to demonstrate the Decorator pattern with Printer implementations.
 */
public class Main {
    
    public static void main(String[] args) {
        System.out.println("=== Basic Printer ===");
        Printer printer = new BasicPrinter();
        printer.print("Hello World!");
        
        System.out.println("\n=== XML Printer ===");
        Printer xmlPrinter = new XMLPrinter(new BasicPrinter());
        xmlPrinter.print("Hello World!");
        
        System.out.println("\n=== Encrypted Printer ===");
        Printer encryptedPrinter = new EncryptedPrinter(new BasicPrinter());
        encryptedPrinter.print("Hello World!");
        
        System.out.println("\n=== Encrypted + XML Printer (as per assignment) ===");
        Printer printer2 = new EncryptedPrinter(new XMLPrinter(new BasicPrinter()));
        printer2.print("Hello World!");
        
        // Demonstrate decryption
        System.out.println("\n=== Decryption Demo ===");
        String encrypted = "PG1lc3NhZ2U+SGVsbG8gV29ybGQhPC9tZXNzYWdlPg==";
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + EncryptedPrinter.decrypt(encrypted));
    }
}

