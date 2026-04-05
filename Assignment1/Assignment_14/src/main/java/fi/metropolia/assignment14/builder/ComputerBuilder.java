package fi.metropolia.assignment14.builder;

import fi.metropolia.assignment14.product.Computer;

/**
 * Builder interface for constructing Computer objects.
 * Defines methods for setting each component of the computer.
 */
public interface ComputerBuilder {
    
    /**
     * Builds and sets the processor component.
     */
    void buildProcessor();
    
    /**
     * Builds and sets the RAM component.
     */
    void buildRAM();
    
    /**
     * Builds and sets the hard drive component.
     */
    void buildHardDrive();
    
    /**
     * Builds and sets the graphics card component.
     */
    void buildGraphicsCard();
    
    /**
     * Builds and sets the operating system component.
     */
    void buildOperatingSystem();
    
    /**
     * Returns the constructed computer.
     * @return the built computer
     */
    Computer getComputer();
}
