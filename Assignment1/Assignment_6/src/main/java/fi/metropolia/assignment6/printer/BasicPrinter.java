package fi.metropolia.assignment6.printer;

/**
 * BasicPrinter is the base implementation that prints messages directly to the console.
 */
public class BasicPrinter implements Printer {
    
    @Override
    public void print(String message) {
        System.out.println(message);
    }
}

