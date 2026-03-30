package fi.metropolia.assignment13;

import fi.metropolia.assignment13.element.Directory;
import fi.metropolia.assignment13.element.File;
import fi.metropolia.assignment13.visitor.SearchVisitor;
import fi.metropolia.assignment13.visitor.SizeCalculatorVisitor;

/**
 * Main class demonstrating the Visitor design pattern for file system operations.
 */
public class Main {
    
    public static void main(String[] args) {
        printHeader();
        
        // Create a sample directory structure
        Directory root = createSampleFileSystem();
        
        // Display the file system structure
        System.out.println("FILE SYSTEM STRUCTURE:");
        System.out.println("═".repeat(50));
        printStructure(root, 0);
        
        // =====================================================
        // VISITOR 1: SizeCalculatorVisitor
        // =====================================================
        System.out.println("\n" + "═".repeat(50));
        System.out.println("VISITOR 1: Size Calculator");
        System.out.println("═".repeat(50));
        System.out.println("Calculating total size of all files...\n");
        
        SizeCalculatorVisitor sizeCalculator = new SizeCalculatorVisitor();
        root.accept(sizeCalculator);
        
        System.out.println("\n" + "-".repeat(50));
        System.out.println("RESULT: " + sizeCalculator.getSummary());
        
        // =====================================================
        // VISITOR 2: SearchVisitor - By Extension
        // =====================================================
        System.out.println("\n" + "═".repeat(50));
        System.out.println("VISITOR 2: Search by Extension (.java)");
        System.out.println("═".repeat(50));
        
        SearchVisitor javaSearch = SearchVisitor.byExtension("java");
        root.accept(javaSearch);
        
        System.out.println("\n" + "-".repeat(50));
        System.out.println(javaSearch.getSummary());
        
        // =====================================================
        // VISITOR 3: SearchVisitor - By Name Pattern
        // =====================================================
        System.out.println("\n" + "═".repeat(50));
        System.out.println("VISITOR 3: Search by Name Pattern ('report')");
        System.out.println("═".repeat(50));
        
        SearchVisitor nameSearch = SearchVisitor.byNameContains("report");
        root.accept(nameSearch);
        
        System.out.println("\n" + "-".repeat(50));
        System.out.println(nameSearch.getSummary());
        
        // =====================================================
        // VISITOR 4: SearchVisitor - By Minimum Size
        // =====================================================
        System.out.println("\n" + "═".repeat(50));
        System.out.println("VISITOR 4: Search Large Files (>= 50 MB)");
        System.out.println("═".repeat(50));
        
        SearchVisitor largeFileSearch = SearchVisitor.byMinSize(50.0);
        root.accept(largeFileSearch);
        
        System.out.println("\n" + "-".repeat(50));
        System.out.println(largeFileSearch.getSummary());
        
        // =====================================================
        // VISITOR 5: SearchVisitor - By Size Range
        // =====================================================
        System.out.println("\n" + "═".repeat(50));
        System.out.println("VISITOR 5: Search Files (1-10 MB)");
        System.out.println("═".repeat(50));
        
        SearchVisitor mediumFileSearch = SearchVisitor.bySizeRange(1.0, 10.0);
        root.accept(mediumFileSearch);
        
        System.out.println("\n" + "-".repeat(50));
        System.out.println(mediumFileSearch.getSummary());
        
        // =====================================================
        // SUMMARY
        // =====================================================
        printSummary(sizeCalculator, javaSearch, nameSearch, largeFileSearch);
    }
    
    /**
     * Creates a sample file system structure for demonstration.
     */
    private static Directory createSampleFileSystem() {
        // Create root directory
        Directory root = new Directory("MyComputer");
        
        // Documents folder
        Directory documents = new Directory("Documents");
        documents.add(new File("resume.pdf", 2.5));
        documents.add(new File("report_q1.docx", 4.2));
        documents.add(new File("report_q2.docx", 3.8));
        documents.add(new File("notes.txt", 0.1));
        
        // Projects folder with subdirectories
        Directory projects = new Directory("Projects");
        
        Directory javaProject = new Directory("JavaProject");
        javaProject.add(new File("Main.java", 0.05));
        javaProject.add(new File("Utils.java", 0.03));
        javaProject.add(new File("Database.java", 0.08));
        javaProject.add(new File("pom.xml", 0.01));
        javaProject.add(new File("README.md", 0.02));
        
        Directory webProject = new Directory("WebProject");
        webProject.add(new File("index.html", 0.02));
        webProject.add(new File("styles.css", 0.01));
        webProject.add(new File("app.js", 0.04));
        webProject.add(new File("logo.png", 1.5));
        
        projects.add(javaProject);
        projects.add(webProject);
        
        // Media folder
        Directory media = new Directory("Media");
        
        Directory photos = new Directory("Photos");
        photos.add(new File("vacation_2024.jpg", 5.2));
        photos.add(new File("family_photo.jpg", 4.8));
        photos.add(new File("landscape.png", 8.3));
        
        Directory videos = new Directory("Videos");
        videos.add(new File("tutorial.mp4", 150.0));
        videos.add(new File("presentation.mp4", 75.5));
        videos.add(new File("clip.avi", 25.0));
        
        Directory music = new Directory("Music");
        music.add(new File("song1.mp3", 8.5));
        music.add(new File("song2.mp3", 7.2));
        music.add(new File("podcast.mp3", 45.0));
        
        media.add(photos);
        media.add(videos);
        media.add(music);
        
        // Downloads folder
        Directory downloads = new Directory("Downloads");
        downloads.add(new File("setup.exe", 85.0));
        downloads.add(new File("ebook.pdf", 12.0));
        downloads.add(new File("data_report.xlsx", 3.5));
        downloads.add(new File("archive.zip", 120.0));
        
        // Add all to root
        root.add(documents);
        root.add(projects);
        root.add(media);
        root.add(downloads);
        
        return root;
    }
    
    /**
     * Prints the file system structure with indentation.
     */
    private static void printStructure(fi.metropolia.assignment13.element.FileSystemElement element, int depth) {
        String indent = "  ".repeat(depth);
        
        if (element instanceof Directory dir) {
            System.out.println(indent + "📁 " + dir.getName() + "/");
            for (fi.metropolia.assignment13.element.FileSystemElement child : dir.getChildren()) {
                printStructure(child, depth + 1);
            }
        } else if (element instanceof File file) {
            System.out.printf("%s📄 %s (%.2f MB)%n", indent, file.getName(), file.getSize());
        }
    }
    
    private static void printHeader() {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║     FILE SYSTEM HANDLING - VISITOR PATTERN DEMO          ║");
        System.out.println("║     ─────────────────────────────────────────            ║");
        System.out.println("║     Demonstrating SizeCalculator and Search Visitors     ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝\n");
    }
    
    private static void printSummary(SizeCalculatorVisitor sizeCalc, 
                                     SearchVisitor javaSearch,
                                     SearchVisitor nameSearch,
                                     SearchVisitor largeSearch) {
        System.out.println("\n" + "═".repeat(50));
        System.out.println("FINAL SUMMARY");
        System.out.println("═".repeat(50));
        
        System.out.println("\n┌─────────────────────────────────────────────────┐");
        System.out.println("│ Size Calculation Results                        │");
        System.out.println("├─────────────────────────────────────────────────┤");
        System.out.printf("│ Total Files:       %,10d                   │%n", sizeCalc.getFileCount());
        System.out.printf("│ Total Directories: %,10d                   │%n", sizeCalc.getDirectoryCount());
        System.out.printf("│ Total Size:        %10s                   │%n", sizeCalc.getFormattedTotalSize());
        System.out.println("└─────────────────────────────────────────────────┘");
        
        System.out.println("\n┌─────────────────────────────────────────────────┐");
        System.out.println("│ Search Results                                  │");
        System.out.println("├─────────────────────────────────────────────────┤");
        System.out.printf("│ .java files:      %3d found                     │%n", javaSearch.getMatchCount());
        System.out.printf("│ 'report' files:   %3d found                     │%n", nameSearch.getMatchCount());
        System.out.printf("│ Large files (50+):%3d found                     │%n", largeSearch.getMatchCount());
        System.out.println("└─────────────────────────────────────────────────┘");
        
        System.out.println("\n" + "═".repeat(50));
        System.out.println("Demo completed successfully!");
        System.out.println("═".repeat(50));
    }
}
