package fi.metropolia.assignment18;

import java.util.Objects;

/**
 * Book entity in the recommendation system.
 * Supports cloning as part of the Prototype pattern.
 */
public class Book {
    
    private final String author;
    private final String title;
    private final String genre;
    private final int publicationYear;
    
    public Book(String author, String title, String genre, int publicationYear) {
        this.author = Objects.requireNonNull(author, "author");
        this.title = Objects.requireNonNull(title, "title");
        this.genre = genre != null ? genre : "Unknown";
        this.publicationYear = publicationYear;
    }
    
    public String getAuthor() {
        return author;
    }
    
    public String getTitle() {
        return title;
    }
    
    public String getGenre() {
        return genre;
    }
    
    public int getPublicationYear() {
        return publicationYear;
    }
    
    /**
     * Prototype-style clone: returns a new Book with the same field values.
     * Independent instance so lists can be deep-copied safely.
     */
    public Book clone() {
        return new Book(author, title, genre, publicationYear);
    }
    
    @Override
    public String toString() {
        return String.format("\"%s\" by %s (%s, %d)", title, author, genre, publicationYear);
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Book book = (Book) o;
        return publicationYear == book.publicationYear
                && author.equals(book.author)
                && title.equals(book.title)
                && genre.equals(book.genre);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(author, title, genre, publicationYear);
    }
}
