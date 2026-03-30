package fi.metropolia.assignment12.exception;

/**
 * Exception thrown when a user tries to access a document they are not allowed to access.
 */
public class AccessDeniedException extends Exception {
    
    private final String username;
    private final String documentId;
    
    public AccessDeniedException(String username, String documentId) {
        super(String.format("Access denied: User '%s' is not allowed to access document '%s'", 
                username, documentId));
        this.username = username;
        this.documentId = documentId;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getDocumentId() {
        return documentId;
    }
}
