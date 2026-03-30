package fi.metropolia.assignment12.document;

import fi.metropolia.assignment12.user.User;
import fi.metropolia.assignment12.exception.AccessDeniedException;

import java.time.LocalDate;

/**
 * Common interface for Document and DocumentProxy.
 * Allows the proxy to be used in place of the real document.
 */
public interface IDocument {
    
    /**
     * Gets the unique identifier of the document.
     * @return document identifier
     */
    String getId();
    
    /**
     * Gets the creation date of the document.
     * No access control required.
     * @return creation date
     */
    LocalDate getCreationDate();
    
    /**
     * Gets the content of the document.
     * For protected documents, access control is enforced.
     * @param user the user requesting access
     * @return document content
     * @throws AccessDeniedException if user is not allowed to access
     */
    String getContent(User user) throws AccessDeniedException;
}
