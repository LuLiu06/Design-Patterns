package fi.metropolia.assignment14.director;

import fi.metropolia.assignment14.builder.ComputerBuilder;
import fi.metropolia.assignment14.product.Computer;

/**
 * Director class that orchestrates the construction process.
 * Takes a builder and constructs the computer by calling the builder's methods
 * in the correct sequence.
 */
public class ComputerDirector {
    
    private ComputerBuilder builder;
    
    /**
     * Sets the builder to be used for construction.
     * @param builder the computer builder
     */
    public void setBuilder(ComputerBuilder builder) {
        this.builder = builder;
    }
    
    /**
     * Constructs a computer using the configured builder.
     * Calls all builder methods in the appropriate order.
     * @return the constructed computer
     */
    public Computer constructComputer() {
        if (builder == null) {
            throw new IllegalStateException("Builder not set! Call setBuilder() first.");
        }
        
        System.out.println("\nStarting computer assembly...");
        
        // Build all components in sequence
        builder.buildProcessor();
        builder.buildRAM();
        builder.buildHardDrive();
        builder.buildGraphicsCard();
        builder.buildOperatingSystem();
        
        System.out.println("Computer assembly completed!\n");
        
        return builder.getComputer();
    }
    
    /**
     * Convenience method: constructs a computer with a specific builder in one call.
     * @param builder the computer builder to use
     * @return the constructed computer
     */
    public Computer constructComputer(ComputerBuilder builder) {
        setBuilder(builder);
        return constructComputer();
    }
}
