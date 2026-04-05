package fi.metropolia.assignment14.product;

/**
 * Computer class representing the final product in the Builder pattern.
 * Contains various components that make up a computer.
 */
public class Computer {
    
    private String processor;
    private int ramSize; // in GB
    private String hardDrive;
    private String graphicsCard;
    private String operatingSystem;
    
    // Setters - typically called by builders
    public void setProcessor(String processor) {
        this.processor = processor;
    }
    
    public void setRamSize(int ramSize) {
        this.ramSize = ramSize;
    }
    
    public void setHardDrive(String hardDrive) {
        this.hardDrive = hardDrive;
    }
    
    public void setGraphicsCard(String graphicsCard) {
        this.graphicsCard = graphicsCard;
    }
    
    public void setOperatingSystem(String operatingSystem) {
        this.operatingSystem = operatingSystem;
    }
    
    // Getters
    public String getProcessor() {
        return processor;
    }
    
    public int getRamSize() {
        return ramSize;
    }
    
    public String getHardDrive() {
        return hardDrive;
    }
    
    public String getGraphicsCard() {
        return graphicsCard;
    }
    
    public String getOperatingSystem() {
        return operatingSystem;
    }
    
    /**
     * Displays the computer configuration in a formatted way.
     */
    public void displayConfiguration() {
        System.out.println("┌─────────────────────────────────────────────────┐");
        System.out.println("│         COMPUTER CONFIGURATION                  │");
        System.out.println("├─────────────────────────────────────────────────┤");
        System.out.printf("│ Processor:       %-30s │%n", processor != null ? processor : "Not Set");
        System.out.printf("│ RAM:             %-30s │%n", ramSize > 0 ? ramSize + " GB" : "Not Set");
        System.out.printf("│ Hard Drive:      %-30s │%n", hardDrive != null ? hardDrive : "Not Set");
        System.out.printf("│ Graphics Card:   %-30s │%n", graphicsCard != null ? graphicsCard : "Not Set");
        System.out.printf("│ Operating System:%-30s │%n", operatingSystem != null ? operatingSystem : "Not Set");
        System.out.println("└─────────────────────────────────────────────────┘");
    }
    
    @Override
    public String toString() {
        return String.format(
                "Computer[processor=%s, RAM=%dGB, hardDrive=%s, graphicsCard=%s, OS=%s]",
                processor, ramSize, hardDrive, graphicsCard, operatingSystem
        );
    }
}
