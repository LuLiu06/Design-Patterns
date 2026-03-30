package fi.metropolia.assignment12;

import fi.metropolia.assignment12.document.IDocument;
import fi.metropolia.assignment12.exception.AccessDeniedException;
import fi.metropolia.assignment12.library.Library;
import fi.metropolia.assignment12.service.AccessControlService;
import fi.metropolia.assignment12.user.User;

import java.time.LocalDate;

/**
 * Main class demonstrating the Protection Proxy pattern for document access control.
 */
public class Main {
    
    public static void main(String[] args) {
        printHeader();
        
        // Create library
        Library library = new Library();
        
        // Create users
        User alice = new User("alice");
        User bob = new User("bob");
        User charlie = new User("charlie");
        User admin = new User("admin");
        
        System.out.println("Created users: alice, bob, charlie, admin\n");
        
        // =====================================================
        // SCENARIO 1: Create unprotected documents
        // =====================================================
        printScenario(1, "Creating Unprotected Documents");
        
        library.addUnprotectedDocument(
                "DOC-001",
                LocalDate.of(2024, 1, 15),
                "This is a public document. Anyone can read it."
        );
        
        library.addUnprotectedDocument(
                "DOC-002",
                LocalDate.of(2024, 2, 20),
                "Another public document with general information."
        );
        
        // =====================================================
        // SCENARIO 2: Create protected documents
        // =====================================================
        printScenario(2, "Creating Protected Documents");
        
        library.addProtectedDocument(
                "DOC-SECRET-001",
                LocalDate.of(2024, 3, 10),
                "TOP SECRET: This document contains confidential information about Project X.",
                "alice", "admin"  // Only alice and admin can access
        );
        
        library.addProtectedDocument(
                "DOC-SECRET-002",
                LocalDate.of(2024, 4, 5),
                "CLASSIFIED: Financial report Q1 2024 - Internal use only.",
                "bob", "admin"  // Only bob and admin can access
        );
        
        library.addProtectedDocument(
                "DOC-SECRET-003",
                LocalDate.of(2024, 5, 1),
                "RESTRICTED: Employee performance reviews - HR Department.",
                "admin"  // Only admin can access
        );
        
        // =====================================================
        // SCENARIO 3: Access unprotected documents
        // =====================================================
        printScenario(3, "Accessing Unprotected Documents");
        
        IDocument doc1 = library.getDocument("DOC-001");
        System.out.println("Document: " + doc1);
        System.out.println("Creation Date: " + doc1.getCreationDate());
        tryAccessDocument(doc1, alice);
        tryAccessDocument(doc1, bob);
        
        // =====================================================
        // SCENARIO 4: Access protected documents - authorized users
        // =====================================================
        printScenario(4, "Accessing Protected Documents - Authorized Users");
        
        IDocument secretDoc1 = library.getDocument("DOC-SECRET-001");
        System.out.println("Document: " + secretDoc1);
        System.out.println("Creation Date: " + secretDoc1.getCreationDate() + " (always accessible)");
        System.out.println("Is Protected: " + library.isProtected("DOC-SECRET-001"));
        System.out.println();
        
        // Alice has access
        tryAccessDocument(secretDoc1, alice);
        
        // Admin has access
        tryAccessDocument(secretDoc1, admin);
        
        // =====================================================
        // SCENARIO 5: Access protected documents - unauthorized users
        // =====================================================
        printScenario(5, "Accessing Protected Documents - Unauthorized Users");
        
        // Bob tries to access DOC-SECRET-001 (not allowed)
        tryAccessDocument(secretDoc1, bob);
        
        // Charlie tries to access DOC-SECRET-001 (not allowed)
        tryAccessDocument(secretDoc1, charlie);
        
        // =====================================================
        // SCENARIO 6: Dynamic access control
        // =====================================================
        printScenario(6, "Dynamic Access Control - Granting and Revoking");
        
        System.out.println("--- Before granting access to Charlie ---");
        tryAccessDocument(secretDoc1, charlie);
        
        System.out.println("\n--- Granting access to Charlie ---");
        library.grantAccess("DOC-SECRET-001", "charlie");
        
        System.out.println("\n--- After granting access to Charlie ---");
        tryAccessDocument(secretDoc1, charlie);
        
        System.out.println("\n--- Revoking access from Alice ---");
        library.revokeAccess("DOC-SECRET-001", "alice");
        
        System.out.println("\n--- After revoking access from Alice ---");
        tryAccessDocument(secretDoc1, alice);
        
        // =====================================================
        // SCENARIO 7: Admin accessing multiple documents
        // =====================================================
        printScenario(7, "Admin Accessing Multiple Protected Documents");
        
        IDocument secretDoc2 = library.getDocument("DOC-SECRET-002");
        IDocument secretDoc3 = library.getDocument("DOC-SECRET-003");
        
        tryAccessDocument(secretDoc2, admin);
        tryAccessDocument(secretDoc3, admin);
        
        // =====================================================
        // SUMMARY
        // =====================================================
        printSummary(library);
    }
    
    private static void tryAccessDocument(IDocument document, User user) {
        System.out.printf("%n[%s] trying to access [%s]%n", user.getUsername(), document.getId());
        try {
            String content = document.getContent(user);
            System.out.println("✓ SUCCESS - Content: " + content);
        } catch (AccessDeniedException e) {
            System.out.println("✗ FAILED - " + e.getMessage());
        }
    }
    
    private static void printHeader() {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║     PROTECTED DOCUMENTS - PROXY PATTERN DEMO             ║");
        System.out.println("║     ─────────────────────────────────────────            ║");
        System.out.println("║     Controlling access to documents using proxies        ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝\n");
    }
    
    private static void printScenario(int number, String title) {
        System.out.println("\n" + "═".repeat(60));
        System.out.printf("SCENARIO %d: %s%n", number, title);
        System.out.println("═".repeat(60));
    }
    
    private static void printSummary(Library library) {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("SUMMARY");
        System.out.println("═".repeat(60));
        System.out.println("\nDocuments in Library:");
        
        for (IDocument doc : library.getAllDocuments()) {
            String status = library.isProtected(doc.getId()) ? "PROTECTED" : "PUBLIC";
            System.out.printf("  • [%s] %s (Created: %s)%n", 
                    status, doc.getId(), doc.getCreationDate());
        }
        
        System.out.println("\nAccess Control Service (Singleton) Summary:");
        AccessControlService acs = AccessControlService.getInstance();
        System.out.println("  • DOC-SECRET-001 allowed users: " + acs.getAllowedUsers("DOC-SECRET-001"));
        System.out.println("  • DOC-SECRET-002 allowed users: " + acs.getAllowedUsers("DOC-SECRET-002"));
        System.out.println("  • DOC-SECRET-003 allowed users: " + acs.getAllowedUsers("DOC-SECRET-003"));
        
        System.out.println("\n" + "═".repeat(60));
        System.out.println("Demo completed successfully!");
        System.out.println("═".repeat(60));
    }
}
