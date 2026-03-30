package fi.metropolia.assignment13.visitor;

import fi.metropolia.assignment13.element.Directory;
import fi.metropolia.assignment13.element.File;
import fi.metropolia.assignment13.element.FileSystemElement;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

/**
 * Concrete visitor that searches for files matching specified criteria.
 * Demonstrates accumulating state (matched files) while traversing the file system.
 */
public class SearchVisitor implements FileSystemVisitor {
    
    private final Predicate<File> searchCriteria;
    private final String criteriaDescription;
    private final List<File> matchedFiles;
    private String currentPath = "";
    private final List<String> matchedPaths;
    
    /**
     * Creates a search visitor with the specified criteria.
     * @param criteria predicate to test files against
     * @param description human-readable description of the criteria
     */
    public SearchVisitor(Predicate<File> criteria, String description) {
        this.searchCriteria = criteria;
        this.criteriaDescription = description;
        this.matchedFiles = new ArrayList<>();
        this.matchedPaths = new ArrayList<>();
    }
    
    /**
     * Factory method: creates a visitor that searches by file extension.
     * @param extension the file extension to search for (e.g., "txt", "java")
     * @return configured SearchVisitor
     */
    public static SearchVisitor byExtension(String extension) {
        String ext = extension.toLowerCase().replace(".", "");
        return new SearchVisitor(
                file -> file.getExtension().equals(ext),
                "extension = ." + ext
        );
    }
    
    /**
     * Factory method: creates a visitor that searches by name pattern.
     * @param pattern the pattern to match (case-insensitive contains)
     * @return configured SearchVisitor
     */
    public static SearchVisitor byNameContains(String pattern) {
        String lowerPattern = pattern.toLowerCase();
        return new SearchVisitor(
                file -> file.getName().toLowerCase().contains(lowerPattern),
                "name contains '" + pattern + "'"
        );
    }
    
    /**
     * Factory method: creates a visitor that searches by minimum size.
     * @param minSizeMB minimum file size in megabytes
     * @return configured SearchVisitor
     */
    public static SearchVisitor byMinSize(double minSizeMB) {
        return new SearchVisitor(
                file -> file.getSize() >= minSizeMB,
                "size >= " + minSizeMB + " MB"
        );
    }
    
    /**
     * Factory method: creates a visitor that searches by size range.
     * @param minSizeMB minimum file size in megabytes
     * @param maxSizeMB maximum file size in megabytes
     * @return configured SearchVisitor
     */
    public static SearchVisitor bySizeRange(double minSizeMB, double maxSizeMB) {
        return new SearchVisitor(
                file -> file.getSize() >= minSizeMB && file.getSize() <= maxSizeMB,
                String.format("size between %.2f MB and %.2f MB", minSizeMB, maxSizeMB)
        );
    }
    
    @Override
    public void visit(File file) {
        if (searchCriteria.test(file)) {
            matchedFiles.add(file);
            String fullPath = currentPath.isEmpty() ? file.getName() : currentPath + "/" + file.getName();
            matchedPaths.add(fullPath);
            System.out.printf("  ✓ MATCH: %s (%.2f MB)%n", fullPath, file.getSize());
        }
    }
    
    @Override
    public void visit(Directory directory) {
        String previousPath = currentPath;
        currentPath = currentPath.isEmpty() ? directory.getName() : currentPath + "/" + directory.getName();
        
        System.out.printf("  Searching in: %s%n", currentPath);
        
        // Visit all children in the directory
        for (FileSystemElement child : directory.getChildren()) {
            child.accept(this);
        }
        
        currentPath = previousPath;
    }
    
    /**
     * Gets the list of matched files.
     * @return list of files matching the criteria
     */
    public List<File> getMatchedFiles() {
        return new ArrayList<>(matchedFiles);
    }
    
    /**
     * Gets the list of matched file paths.
     * @return list of full paths to matched files
     */
    public List<String> getMatchedPaths() {
        return new ArrayList<>(matchedPaths);
    }
    
    /**
     * Gets the number of matched files.
     * @return match count
     */
    public int getMatchCount() {
        return matchedFiles.size();
    }
    
    /**
     * Gets the total size of all matched files.
     * @return total size in MB
     */
    public double getTotalMatchedSize() {
        return matchedFiles.stream()
                .mapToDouble(File::getSize)
                .sum();
    }
    
    /**
     * Gets the search criteria description.
     * @return criteria description
     */
    public String getCriteriaDescription() {
        return criteriaDescription;
    }
    
    /**
     * Resets the search results.
     */
    public void reset() {
        matchedFiles.clear();
        matchedPaths.clear();
        currentPath = "";
    }
    
    /**
     * Gets a summary of the search results.
     * @return summary string
     */
    public String getSummary() {
        return String.format(
                "Search criteria: %s%nFound %d file(s), total size: %.2f MB",
                criteriaDescription, matchedFiles.size(), getTotalMatchedSize()
        );
    }
}
