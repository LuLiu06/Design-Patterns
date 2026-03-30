package fi.metropolia.assignment12.document;

import fi.metropolia.assignment12.exception.AccessDeniedException;
import fi.metropolia.assignment12.service.AccessControlService;
import fi.metropolia.assignment12.user.User;

import java.time.LocalDate;

/**
 * Protection Proxy for Document.
 * Controls access to protected documents by checking with AccessControlService.
 */
public class DocumentProxy implements IDocument {
    
    private final Document realDocument;
    private final AccessControlService accessControlService;
    
    /**
     * Constructor for creating a document proxy.
     * Use Library factory methods to create protected documents.
     */
    public DocumentProxy(Document realDocument) {
        this.realDocument = realDocument;
        this.accessControlService = AccessControlService.getInstance();
    }
    
    @Override
    public String getId() {
        return realDocument.getId();
    }
    
    @Override
    public LocalDate getCreationDate() {
        // Creation date is always accessible without access control
        return realDocument.getCreationDate();
    }
    
    @Override
    public String getContent(User user) throws AccessDeniedException {
        // Check access control before allowing content access
        String username = user.getUsername();
        String documentId = realDocument.getId();
        
        System.out.printf("Checking access for user '%s' to document '%s'...%n", 
                username, documentId);
        
        if (accessControlService.isAllowed(documentId, username)) {
            System.out.println("Access granted. Returning content.");
            return realDocument.getContentInternal();
        } else {
            System.out.println("Access denied!");
            throw new AccessDeniedException(username, documentId);
        }
    }
    
    /**
     * Checks if a user has access to this document without throwing exception.
     * @param user the user to check
     * @return true if user has access
     */
    public boolean hasAccess(User user) {
        return accessControlService.isAllowed(realDocument.getId(), user.getUsername());
    }
    
    @Override
    public String toString() {
        return String.format("DocumentProxy[id=%s, created=%s, PROTECTED]", 
                realDocument.getId(), realDocument.getCreationDate());
    }
}
