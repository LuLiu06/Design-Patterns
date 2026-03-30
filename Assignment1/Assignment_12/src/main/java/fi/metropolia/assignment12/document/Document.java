package fi.metropolia.assignment12.document;

import fi.metropolia.assignment12.user.User;

import java.time.LocalDate;

/**
 * Real Document class containing the actual document data.
 * For protected documents, this should only be accessed through DocumentProxy.
 */
public class Document implements IDocument {
    
    private final String id;
    private final LocalDate creationDate;
    private final String content;
    
    /**
     * Constructor for creating a document.
     * For protected documents, use Library factory methods.
     */
    public Document(String id, LocalDate creationDate, String content) {
        this.id = id;
        this.creationDate = creationDate;
        this.content = content;
    }
    
    @Override
    public String getId() {
        return id;
    }
    
    @Override
    public LocalDate getCreationDate() {
        return creationDate;
    }
    
    @Override
    public String getContent(User user) {
        // Unprotected document - no access control
        return content;
    }
    
    /**
     * Internal method for proxy to access content directly.
     */
    public String getContentInternal() {
        return content;
    }
    
    @Override
    public String toString() {
        return String.format("Document[id=%s, created=%s]", id, creationDate);
    }
}
