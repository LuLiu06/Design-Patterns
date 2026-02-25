package fi.metropolia.assignment6.printer;

/**
 * XMLPrinter decorator that wraps the message in XML tags before printing.
 * For example, "Hello World!" becomes "<message>Hello World!</message>".
 */
public class XMLPrinter extends PrinterDecorator {
    
    public XMLPrinter(Printer printer) {
        super(printer);
    }
    
    @Override
    public void print(String message) {
        String xmlMessage = "<message>" + message + "</message>";
        wrappedPrinter.print(xmlMessage);
    }
}

