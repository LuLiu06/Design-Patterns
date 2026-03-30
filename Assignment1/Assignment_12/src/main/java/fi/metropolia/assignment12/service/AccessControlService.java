package fi.metropolia.assignment12.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Singleton service that manages access control for protected documents.
 * Stores (documentId -> set of allowed usernames) pairs.
 */
public class AccessControlService {
    
    private static AccessControlService instance;
    
    // Map of document ID to set of allowed usernames
    private final Map<String, Set<String>> accessList;
    
    private AccessControlService() {
        this.accessList = new HashMap<>();
    }
    
    /**
     * Gets the singleton instance of AccessControlService.
     * @return the singleton instance
     */
    public static synchronized AccessControlService getInstance() {
        if (instance == null) {
            instance = new AccessControlService();
        }
        return instance;
    }
    
    /**
     * Checks if a user is allowed to access a document.
     * @param documentId the document identifier
     * @param username the username
     * @return true if allowed, false otherwise
     */
    public boolean isAllowed(String documentId, String username) {
        Set<String> allowedUsers = accessList.get(documentId);
        if (allowedUsers == null) {
            return false;
        }
        return allowedUsers.contains(username);
    }
    
    /**
     * Grants access to a user for a specific document.
     * @param documentId the document identifier
     * @param username the username to grant access
     */
    public void grantAccess(String documentId, String username) {
        accessList.computeIfAbsent(documentId, k -> new HashSet<>()).add(username);
        System.out.printf("Access granted: User '%s' can now access document '%s'%n", 
                username, documentId);
    }
    
    /**
     * Grants access to multiple users for a specific document.
     * @param documentId the document identifier
     * @param usernames the usernames to grant access
     */
    public void grantAccess(String documentId, String... usernames) {
        for (String username : usernames) {
            grantAccess(documentId, username);
        }
    }
    
    /**
     * Revokes access from a user for a specific document.
     * @param documentId the document identifier
     * @param username the username to revoke access
     */
    public void revokeAccess(String documentId, String username) {
        Set<String> allowedUsers = accessList.get(documentId);
        if (allowedUsers != null) {
            allowedUsers.remove(username);
            System.out.printf("Access revoked: User '%s' can no longer access document '%s'%n", 
                    username, documentId);
        }
    }
    
    /**
     * Gets all users who have access to a document.
     * @param documentId the document identifier
     * @return set of usernames with access
     */
    public Set<String> getAllowedUsers(String documentId) {
        return accessList.getOrDefault(documentId, new HashSet<>());
    }
    
    /**
     * Clears all access control entries. Useful for testing.
     */
    public void clearAll() {
        accessList.clear();
    }
}
