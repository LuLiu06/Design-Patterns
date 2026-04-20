package fi.metropolia.assignment18;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * A recommendation list for a target audience, containing books.
 * Implements deep cloning per the Prototype pattern.
 */
public class Recommendation {
    
    private String targetAudience;
    private final List<Book> books;
    
    public Recommendation(String targetAudience) {
        this.targetAudience = Objects.requireNonNull(targetAudience, "targetAudience");
        this.books = new ArrayList<>();
    }
    
    public Recommendation(String targetAudience, List<Book> books) {
        this.targetAudience = Objects.requireNonNull(targetAudience, "targetAudience");
        this.books = new ArrayList<>(Objects.requireNonNull(books, "books"));
    }
    
    public String getTargetAudience() {
        return targetAudience;
    }
    
    /**
     * Updates the target audience label for this recommendation list.
     */
    public void setTargetAudience(String targetAudience) {
        this.targetAudience = Objects.requireNonNull(targetAudience, "targetAudience");
    }
    
    /**
     * Unmodifiable view of books (mutations go through add/remove methods).
     */
    public List<Book> getBooks() {
        return Collections.unmodifiableList(books);
    }
    
    public void addBook(Book book) {
        books.add(Objects.requireNonNull(book, "book"));
    }
    
    public boolean removeBook(int index) {
        if (index < 0 || index >= books.size()) {
            return false;
        }
        books.remove(index);
        return true;
    }
    
    public int getBookCount() {
        return books.size();
    }
    
    /**
     * Deep clone: new Recommendation with a new list containing cloned Book instances.
     * Modifying the clone's list or books does not affect the original.
     */
    public Recommendation clone() {
        List<Book> clonedBooks = new ArrayList<>();
        for (Book book : books) {
            clonedBooks.add(book.clone());
        }
        return new Recommendation(targetAudience, clonedBooks);
    }
    
    @Override
    public String toString() {
        return String.format("Recommendation[audience=%s, books=%d]", targetAudience, books.size());
    }
}
