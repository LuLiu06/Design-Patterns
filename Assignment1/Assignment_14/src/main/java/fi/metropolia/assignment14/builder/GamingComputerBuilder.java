package fi.metropolia.assignment14.builder;

import fi.metropolia.assignment14.product.Computer;

/**
 * Concrete builder for constructing high-performance gaming computers.
 * Configures components optimized for gaming.
 */
public class GamingComputerBuilder implements ComputerBuilder {
    
    private Computer computer;
    
    public GamingComputerBuilder() {
        this.computer = new Computer();
    }
    
    @Override
    public void buildProcessor() {
        computer.setProcessor("Intel Core i9-13900K (24 cores, 5.8 GHz)");
        System.out.println("  ✓ Installed high-performance gaming processor");
    }
    
    @Override
    public void buildRAM() {
        computer.setRamSize(32);
        System.out.println("  ✓ Installed 32 GB DDR5 RAM");
    }
    
    @Override
    public void buildHardDrive() {
        computer.setHardDrive("2 TB NVMe SSD");
        System.out.println("  ✓ Installed fast NVMe SSD storage");
    }
    
    @Override
    public void buildGraphicsCard() {
        computer.setGraphicsCard("NVIDIA GeForce RTX 4090 (24 GB)");
        System.out.println("  ✓ Installed top-tier gaming graphics card");
    }
    
    @Override
    public void buildOperatingSystem() {
        computer.setOperatingSystem("Windows 11 Pro");
        System.out.println("  ✓ Installed gaming-optimized OS");
    }
    
    @Override
    public Computer getComputer() {
        return computer;
    }
}
