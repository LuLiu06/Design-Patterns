package fi.metropolia.assignment13.visitor;

import fi.metropolia.assignment13.element.File;
import fi.metropolia.assignment13.element.Directory;

/**
 * Visitor interface for the Visitor design pattern.
 * Defines visit methods for each type of file system element.
 */
public interface FileSystemVisitor {
    
    /**
     * Visits a file element.
     * @param file the file to visit
     */
    void visit(File file);
    
    /**
     * Visits a directory element.
     * @param directory the directory to visit
     */
    void visit(Directory directory);
}
