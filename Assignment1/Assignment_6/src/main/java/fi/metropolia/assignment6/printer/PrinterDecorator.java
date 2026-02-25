package fi.metropolia.assignment6.printer;

/**
 * Abstract decorator class that wraps a Printer and delegates printing to it.
 * Concrete decorators extend this class to add additional behavior.
 */
public abstract class PrinterDecorator implements Printer {
    
    protected final Printer wrappedPrinter;
    
    public PrinterDecorator(Printer printer) {
        this.wrappedPrinter = printer;
    }
    
    @Override
    public void print(String message) {
        wrappedPrinter.print(message);
    }
}

