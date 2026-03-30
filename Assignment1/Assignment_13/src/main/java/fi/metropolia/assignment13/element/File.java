package fi.metropolia.assignment13.element;

import fi.metropolia.assignment13.visitor.FileSystemVisitor;

/**
 * File class representing a file in the file system.
 * Has a name and size (in megabytes).
 */
public class File implements FileSystemElement {
    
    private final String name;
    private final double size; // Size in megabytes
    
    public File(String name, double size) {
        this.name = name;
        this.size = size;
    }
    
    @Override
    public String getName() {
        return name;
    }
    
    /**
     * Gets the file size in megabytes.
     * @return file size in MB
     */
    public double getSize() {
        return size;
    }
    
    /**
     * Gets the file extension.
     * @return file extension (e.g., "txt", "java") or empty string if none
     */
    public String getExtension() {
        int lastDot = name.lastIndexOf('.');
        if (lastDot > 0 && lastDot < name.length() - 1) {
            return name.substring(lastDot + 1).toLowerCase();
        }
        return "";
    }
    
    @Override
    public void accept(FileSystemVisitor visitor) {
        visitor.visit(this);
    }
    
    @Override
    public String toString() {
        return String.format("File[%s, %.2f MB]", name, size);
    }
}
