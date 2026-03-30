package fi.metropolia.assignment13.element;

import fi.metropolia.assignment13.visitor.FileSystemVisitor;

import java.util.ArrayList;
import java.util.List;

/**
 * Directory class representing a folder in the file system.
 * Can contain multiple files and subdirectories.
 */
public class Directory implements FileSystemElement {
    
    private final String name;
    private final List<FileSystemElement> children;
    
    public Directory(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    /**
     * Adds a file system element (file or directory) to this directory.
     * @param element the element to add
     * @return this directory (for method chaining)
     */
    public Directory add(FileSystemElement element) {
        children.add(element);
        return this;
    }
    
    /**
     * Adds multiple file system elements to this directory.
     * @param elements the elements to add
     * @return this directory (for method chaining)
     */
    public Directory addAll(FileSystemElement... elements) {
        for (FileSystemElement element : elements) {
            children.add(element);
        }
        return this;
    }
    
    /**
     * Gets all children of this directory.
     * @return list of child elements
     */
    public List<FileSystemElement> getChildren() {
        return children;
    }
    
    /**
     * Gets the number of direct children in this directory.
     * @return number of children
     */
    public int getChildCount() {
        return children.size();
    }
    
    @Override
    public void accept(FileSystemVisitor visitor) {
        visitor.visit(this);
    }
    
    @Override
    public String toString() {
        return String.format("Directory[%s, %d items]", name, children.size());
    }
}
