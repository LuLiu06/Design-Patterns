package fi.metropolia.assignment13.element;

import fi.metropolia.assignment13.visitor.FileSystemVisitor;

/**
 * Base interface for all file system elements (files and directories).
 * Supports the Visitor design pattern through the accept method.
 */
public interface FileSystemElement {
    
    /**
     * Gets the name of the file system element.
     * @return element name
     */
    String getName();
    
    /**
     * Accepts a visitor to perform operations on this element.
     * @param visitor the visitor to accept
     */
    void accept(FileSystemVisitor visitor);
}
