package fi.metropolia.assignment12.library;

import fi.metropolia.assignment12.document.Document;
import fi.metropolia.assignment12.document.DocumentProxy;
import fi.metropolia.assignment12.document.IDocument;
import fi.metropolia.assignment12.service.AccessControlService;

import java.time.LocalDate;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * Library class that stores documents.
 * Contains factory methods for creating both protected and unprotected documents.
 */
public class Library {
    
    private final Map<String, IDocument> documents;
    private final AccessControlService accessControlService;
    
    public Library() {
        this.documents = new HashMap<>();
        this.accessControlService = AccessControlService.getInstance();
    }
    
    /**
     * Factory method to create and add an unprotected document.
     * @param id unique document identifier
     * @param creationDate creation date
     * @param content document content
     * @return the created document
     */
    public IDocument addUnprotectedDocument(String id, LocalDate creationDate, String content) {
        Document document = new Document(id, creationDate, content);
        documents.put(id, document);
        System.out.printf("Added unprotected document: %s%n", id);
        return document;
    }
    
    /**
     * Factory method to create and add a protected document.
     * Creates both the real document and its proxy.
     * @param id unique document identifier
     * @param creationDate creation date
     * @param content document content
     * @param allowedUsernames users allowed to access this document
     * @return the document proxy
     */
    public IDocument addProtectedDocument(String id, LocalDate creationDate, String content, 
                                          String... allowedUsernames) {
        // Create real document (package-private, not directly accessible)
        Document realDocument = new Document(id, creationDate, content);
        
        // Create proxy that controls access to the real document
        DocumentProxy proxy = new DocumentProxy(realDocument);
        
        // Grant access to specified users
        for (String username : allowedUsernames) {
            accessControlService.grantAccess(id, username);
        }
        
        // Store only the proxy in the library
        documents.put(id, proxy);
        System.out.printf("Added protected document: %s%n", id);
        
        return proxy;
    }
    
    /**
     * Gets a document by its identifier.
     * @param id document identifier
     * @return the document (or proxy), or null if not found
     */
    public IDocument getDocument(String id) {
        return documents.get(id);
    }
    
    /**
     * Checks if a document exists in the library.
     * @param id document identifier
     * @return true if document exists
     */
    public boolean hasDocument(String id) {
        return documents.containsKey(id);
    }
    
    /**
     * Gets all documents in the library.
     * @return collection of all documents
     */
    public Collection<IDocument> getAllDocuments() {
        return documents.values();
    }
    
    /**
     * Gets the number of documents in the library.
     * @return document count
     */
    public int getDocumentCount() {
        return documents.size();
    }
    
    /**
     * Checks if a document is protected.
     * @param id document identifier
     * @return true if document is protected (is a proxy)
     */
    public boolean isProtected(String id) {
        IDocument doc = documents.get(id);
        return doc instanceof DocumentProxy;
    }
    
    /**
     * Grants access to a protected document for a user.
     * @param documentId document identifier
     * @param username username to grant access
     */
    public void grantAccess(String documentId, String username) {
        if (isProtected(documentId)) {
            accessControlService.grantAccess(documentId, username);
        } else {
            System.out.printf("Document '%s' is not protected, no access control needed%n", documentId);
        }
    }
    
    /**
     * Revokes access to a protected document for a user.
     * @param documentId document identifier
     * @param username username to revoke access
     */
    public void revokeAccess(String documentId, String username) {
        if (isProtected(documentId)) {
            accessControlService.revokeAccess(documentId, username);
        }
    }
}
