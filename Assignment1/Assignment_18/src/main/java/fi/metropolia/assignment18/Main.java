package fi.metropolia.assignment18;

/**
 * Entry point: seeds sample recommendations and starts the CLI.
 */
public class Main {
    
    public static void main(String[] args) {
        RecommendationCatalog catalog = new RecommendationCatalog();
        seedSampleData(catalog);
        
        BookRecommendationCli cli = new BookRecommendationCli(catalog);
        cli.run();
    }
    
    private static void seedSampleData(RecommendationCatalog catalog) {
        Recommendation youngAdult = new Recommendation("Young adult readers");
        youngAdult.addBook(new Book("Angie Thomas", "The Hate U Give", "Young Adult", 2017));
        youngAdult.addBook(new Book("John Green", "The Fault in Our Stars", "Young Adult", 2012));
        youngAdult.addBook(new Book("Rainbow Rowell", "Eleanor & Park", "Young Adult", 2012));
        catalog.add(youngAdult);
        
        Recommendation sciFi = new Recommendation("Science fiction fans");
        sciFi.addBook(new Book("Frank Herbert", "Dune", "Science Fiction", 1965));
        sciFi.addBook(new Book("Andy Weir", "The Martian", "Science Fiction", 2011));
        sciFi.addBook(new Book("Liu Cixin", "The Three-Body Problem", "Science Fiction", 2008));
        catalog.add(sciFi);
        
        Recommendation mystery = new Recommendation("Mystery & thriller readers");
        mystery.addBook(new Book("Agatha Christie", "Murder on the Orient Express", "Mystery", 1934));
        mystery.addBook(new Book("Gillian Flynn", "Gone Girl", "Thriller", 2012));
        catalog.add(mystery);
    }
}
