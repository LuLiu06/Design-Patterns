package fi.metropolia.assignment18;

import java.util.Scanner;

/**
 * Command-line UI: view lists, clone and edit, save new lists.
 */
public class BookRecommendationCli {
    
    private final RecommendationCatalog catalog;
    private final Scanner scanner;
    private Recommendation workingCopy;
    
    public BookRecommendationCli(RecommendationCatalog catalog) {
        this.catalog = catalog;
        this.scanner = new Scanner(System.in);
    }
    
    public void run() {
        printWelcome();
        boolean exit = false;
        while (!exit) {
            printMainMenu();
            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1" -> viewAllRecommendations();
                case "2" -> viewRecommendationDetail();
                case "3" -> cloneFromExisting();
                case "4" -> editWorkingCopy();
                case "5" -> saveWorkingCopy();
                case "6" -> createEmptyWorkingCopy();
                case "0" -> exit = true;
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
        System.out.println("Goodbye!");
    }
    
    private void printWelcome() {
        System.out.println("""
                
                ╔══════════════════════════════════════════════════════════╗
                ║     Book Recommendations System (Prototype Pattern)      ║
                ╚══════════════════════════════════════════════════════════╝
                Clone a list to get a deep copy, then edit and save as new.
                """);
    }
    
    private void printMainMenu() {
        System.out.println("\n--- Main Menu ---");
        System.out.println("1. View all recommendation lists");
        System.out.println("2. View one list (details)");
        System.out.println("3. Clone an existing list into editor (deep copy)");
        System.out.println("4. Edit current draft (audience / add / remove book)");
        System.out.println("5. Save current draft as a new list in catalog");
        System.out.println("6. Start new empty draft");
        System.out.println("0. Exit");
        System.out.print("Choice: ");
    }
    
    private void viewAllRecommendations() {
        if (catalog.size() == 0) {
            System.out.println("No saved lists yet.");
            return;
        }
        for (int i = 0; i < catalog.size(); i++) {
            Recommendation r = catalog.get(i);
            System.out.printf("%d. %s — %d book(s)%n", i + 1, r.getTargetAudience(), r.getBookCount());
        }
    }
    
    private void viewRecommendationDetail() {
        Integer idx = readListIndex("Index of list to show (1-based): ");
        if (idx == null) return;
        Recommendation r = catalog.get(idx);
        printRecommendation(r, idx + 1);
    }
    
    private void cloneFromExisting() {
        if (catalog.size() == 0) {
            System.out.println("No lists to clone. Add seed data or create empty draft first.");
            return;
        }
        Integer idx = readListIndex("Index to clone (1-based): ");
        if (idx == null) return;
        Recommendation original = catalog.get(idx);
        workingCopy = original.clone();
        System.out.println("Cloned (deep copy) into editor. Original list unchanged.");
        printRecommendation(workingCopy, -1);
    }
    
    private void editWorkingCopy() {
        if (workingCopy == null) {
            System.out.println("No draft. Use option 3 or 6 first.");
            return;
        }
        boolean back = false;
        while (!back) {
            System.out.println("\n--- Edit draft ---");
            System.out.println("1. Set target audience");
            System.out.println("2. Add a book");
            System.out.println("3. Remove a book by index");
            System.out.println("4. Show current draft");
            System.out.println("0. Back to main menu");
            System.out.print("Choice: ");
            String c = scanner.nextLine().trim();
            switch (c) {
                case "1" -> {
                    System.out.print("New target audience: ");
                    workingCopy.setTargetAudience(scanner.nextLine().trim());
                    System.out.println("Updated.");
                }
                case "2" -> addBookFlow();
                case "3" -> removeBookFlow();
                case "4" -> printRecommendation(workingCopy, -1);
                case "0" -> back = true;
                default -> System.out.println("Invalid choice.");
            }
        }
    }
    
    private void addBookFlow() {
        System.out.print("Title: ");
        String title = scanner.nextLine().trim();
        System.out.print("Author: ");
        String author = scanner.nextLine().trim();
        System.out.print("Genre: ");
        String genre = scanner.nextLine().trim();
        System.out.print("Publication year: ");
        int year = readInt();
        workingCopy.addBook(new Book(author, title, genre.isEmpty() ? "General" : genre, year));
        System.out.println("Book added.");
    }
    
    private void removeBookFlow() {
        if (workingCopy.getBookCount() == 0) {
            System.out.println("No books to remove.");
            return;
        }
        printRecommendation(workingCopy, -1);
        System.out.print("Index of book to remove (1-based): ");
        int oneBased = readInt();
        if (workingCopy.removeBook(oneBased - 1)) {
            System.out.println("Removed.");
        } else {
            System.out.println("Invalid index.");
        }
    }
    
    private void saveWorkingCopy() {
        if (workingCopy == null) {
            System.out.println("No draft to save.");
            return;
        }
        Recommendation toStore = workingCopy.clone();
        catalog.add(toStore);
        System.out.println("Saved as new list #" + catalog.size() + " (deep copy of draft).");
    }
    
    private void createEmptyWorkingCopy() {
        System.out.print("Target audience for new empty list: ");
        String audience = scanner.nextLine().trim();
        if (audience.isEmpty()) {
            audience = "General readers";
        }
        workingCopy = new Recommendation(audience);
        System.out.println("Empty draft ready.");
    }
    
    private Integer readListIndex(String prompt) {
        System.out.print(prompt);
        int oneBased = readInt();
        int idx = oneBased - 1;
        if (!catalog.isValidIndex(idx)) {
            System.out.println("Invalid index.");
            return null;
        }
        return idx;
    }
    
    private int readInt() {
        try {
            return Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            return 0;
        }
    }
    
    private void printRecommendation(Recommendation r, int displayNumber) {
        if (displayNumber > 0) {
            System.out.println("\nList #" + displayNumber);
        }
        System.out.println("Target audience: " + r.getTargetAudience());
        System.out.println("Books (" + r.getBookCount() + "):");
        int i = 1;
        for (Book b : r.getBooks()) {
            System.out.printf("  %d. %s%n", i++, b);
        }
    }
}
