package fi.metropolia.assignment14;

import fi.metropolia.assignment14.builder.ComputerBuilder;
import fi.metropolia.assignment14.builder.GamingComputerBuilder;
import fi.metropolia.assignment14.builder.OfficeComputerBuilder;
import fi.metropolia.assignment14.director.ComputerDirector;
import fi.metropolia.assignment14.product.Computer;

/**
 * Main class demonstrating the Builder design pattern for computer construction.
 */
public class Main {
    
    public static void main(String[] args) {
        printHeader();
        
        // Create director
        ComputerDirector director = new ComputerDirector();
        
        // =====================================================
        // SCENARIO 1: Build a Gaming Computer
        // =====================================================
        printScenario(1, "Building a High-Performance Gaming Computer");
        
        ComputerBuilder gamingBuilder = new GamingComputerBuilder();
        director.setBuilder(gamingBuilder);
        Computer gamingComputer = director.constructComputer();
        
        System.out.println("Gaming Computer Configuration:");
        gamingComputer.displayConfiguration();
        
        // =====================================================
        // SCENARIO 2: Build an Office Computer
        // =====================================================
        printScenario(2, "Building an Office/Business Computer");
        
        ComputerBuilder officeBuilder = new OfficeComputerBuilder();
        director.setBuilder(officeBuilder);
        Computer officeComputer = director.constructComputer();
        
        System.out.println("Office Computer Configuration:");
        officeComputer.displayConfiguration();
        
        // =====================================================
        // SCENARIO 3: Build Multiple Computers
        // =====================================================
        printScenario(3, "Building Multiple Computers for Different Purposes");
        
        System.out.println("Building 2 gaming computers for a gaming cafe...");
        Computer gamingPC1 = director.constructComputer(new GamingComputerBuilder());
        Computer gamingPC2 = director.constructComputer(new GamingComputerBuilder());
        
        System.out.println("\nBuilding 3 office computers for a company...");
        Computer officePC1 = director.constructComputer(new OfficeComputerBuilder());
        Computer officePC2 = director.constructComputer(new OfficeComputerBuilder());
        Computer officePC3 = director.constructComputer(new OfficeComputerBuilder());
        
        System.out.println("\nAll computers built successfully!");
        
        // =====================================================
        // SCENARIO 4: Comparison
        // =====================================================
        printScenario(4, "Comparing Gaming vs Office Computer Specifications");
        
        System.out.println("GAMING COMPUTER:");
        gamingComputer.displayConfiguration();
        
        System.out.println("\nOFFICE COMPUTER:");
        officeComputer.displayConfiguration();
        
        printComparison(gamingComputer, officeComputer);
        
        // =====================================================
        // SUMMARY
        // =====================================================
        printSummary();
    }
    
    private static void printHeader() {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║     COMPUTER BUILDER - BUILDER PATTERN DEMO              ║");
        System.out.println("║     ─────────────────────────────────────────            ║");
        System.out.println("║     Constructing different computer configurations       ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
    }
    
    private static void printScenario(int number, String title) {
        System.out.println("\n" + "═".repeat(60));
        System.out.printf("SCENARIO %d: %s%n", number, title);
        System.out.println("═".repeat(60));
    }
    
    private static void printComparison(Computer gaming, Computer office) {
        System.out.println("\n" + "─".repeat(60));
        System.out.println("COMPARISON SUMMARY:");
        System.out.println("─".repeat(60));
        
        System.out.println("\n┌────────────────────┬──────────────────────┬──────────────────────┐");
        System.out.println("│ Component          │ Gaming Computer      │ Office Computer      │");
        System.out.println("├────────────────────┼──────────────────────┼──────────────────────┤");
        
        // Processor comparison
        String gamingProc = shortenText(gaming.getProcessor(), 20);
        String officeProc = shortenText(office.getProcessor(), 20);
        System.out.printf("│ Processor          │ %-20s │ %-20s │%n", gamingProc, officeProc);
        
        // RAM comparison
        System.out.printf("│ RAM                │ %-20s │ %-20s │%n", 
                gaming.getRamSize() + " GB", 
                office.getRamSize() + " GB");
        
        // Storage comparison
        String gamingHD = shortenText(gaming.getHardDrive(), 20);
        String officeHD = shortenText(office.getHardDrive(), 20);
        System.out.printf("│ Storage            │ %-20s │ %-20s │%n", gamingHD, officeHD);
        
        // Graphics comparison
        String gamingGPU = shortenText(gaming.getGraphicsCard(), 20);
        String officeGPU = shortenText(office.getGraphicsCard(), 20);
        System.out.printf("│ Graphics           │ %-20s │ %-20s │%n", gamingGPU, officeGPU);
        
        System.out.println("└────────────────────┴──────────────────────┴──────────────────────┘");
        
        System.out.println("\nKey Differences:");
        System.out.println("  • Gaming: High-end GPU, more RAM, larger storage");
        System.out.println("  • Office: Integrated graphics, adequate RAM, smaller storage");
        System.out.println("  • Gaming: Optimized for performance and graphics");
        System.out.println("  • Office: Optimized for cost-effectiveness and reliability");
    }
    
    private static String shortenText(String text, int maxLength) {
        if (text == null) return "";
        if (text.length() <= maxLength) return text;
        return text.substring(0, maxLength - 3) + "...";
    }
    
    private static void printSummary() {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("BUILDER PATTERN BENEFITS DEMONSTRATED");
        System.out.println("═".repeat(60));
        
        System.out.println("""
                
                ✓ Separation of Concerns:
                  - Director orchestrates construction
                  - Builders handle specific configurations
                  - Product (Computer) is independent
                
                ✓ Flexibility:
                  - Easy to add new computer types (e.g., WorkstationBuilder)
                  - Same construction process for different products
                  - Can build multiple computers with same configuration
                
                ✓ Controlled Construction:
                  - Director ensures all components are built
                  - Consistent assembly process
                  - Complex object creation made simple
                
                ✓ Code Reusability:
                  - Director can work with any builder
                  - Builders can be reused for multiple constructions
                """);
        
        System.out.println("═".repeat(60));
        System.out.println("Demo completed successfully!");
        System.out.println("═".repeat(60));
    }
}
