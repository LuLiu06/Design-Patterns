package fi.metropolia.assignment13.visitor;

import fi.metropolia.assignment13.element.Directory;
import fi.metropolia.assignment13.element.File;
import fi.metropolia.assignment13.element.FileSystemElement;

/**
 * Concrete visitor that calculates the total size of all files in the file system.
 * Demonstrates accumulating state while traversing the file system.
 */
public class SizeCalculatorVisitor implements FileSystemVisitor {
    
    private double totalSize = 0.0;
    private int fileCount = 0;
    private int directoryCount = 0;
    
    @Override
    public void visit(File file) {
        totalSize += file.getSize();
        fileCount++;
        System.out.printf("  Visited file: %s (%.2f MB)%n", file.getName(), file.getSize());
    }
    
    @Override
    public void visit(Directory directory) {
        directoryCount++;
        System.out.printf("  Entering directory: %s%n", directory.getName());
        
        // Visit all children in the directory
        for (FileSystemElement child : directory.getChildren()) {
            child.accept(this);
        }
        
        System.out.printf("  Leaving directory: %s%n", directory.getName());
    }
    
    /**
     * Gets the total size of all files visited (in megabytes).
     * @return total size in MB
     */
    public double getTotalSize() {
        return totalSize;
    }
    
    /**
     * Gets the total size formatted as a string with appropriate unit.
     * @return formatted size string
     */
    public String getFormattedTotalSize() {
        if (totalSize >= 1024) {
            return String.format("%.2f GB", totalSize / 1024);
        } else {
            return String.format("%.2f MB", totalSize);
        }
    }
    
    /**
     * Gets the number of files visited.
     * @return file count
     */
    public int getFileCount() {
        return fileCount;
    }
    
    /**
     * Gets the number of directories visited.
     * @return directory count
     */
    public int getDirectoryCount() {
        return directoryCount;
    }
    
    /**
     * Resets the accumulated state.
     */
    public void reset() {
        totalSize = 0.0;
        fileCount = 0;
        directoryCount = 0;
    }
    
    /**
     * Gets a summary of the calculation results.
     * @return summary string
     */
    public String getSummary() {
        return String.format(
                "Total: %s across %d file(s) in %d directory(ies)",
                getFormattedTotalSize(), fileCount, directoryCount
        );
    }
}
