package fi.metropolia.assignment14.builder;

import fi.metropolia.assignment14.product.Computer;

/**
 * Concrete builder for constructing office/productivity computers.
 * Configures components suitable for business and office tasks.
 */
public class OfficeComputerBuilder implements ComputerBuilder {
    
    private Computer computer;
    
    public OfficeComputerBuilder() {
        this.computer = new Computer();
    }
    
    @Override
    public void buildProcessor() {
        computer.setProcessor("Intel Core i5-13600 (14 cores, 4.8 GHz)");
        System.out.println("  ✓ Installed efficient office processor");
    }
    
    @Override
    public void buildRAM() {
        computer.setRamSize(16);
        System.out.println("  ✓ Installed 16 GB DDR4 RAM");
    }
    
    @Override
    public void buildHardDrive() {
        computer.setHardDrive("512 GB SSD");
        System.out.println("  ✓ Installed reliable SSD storage");
    }
    
    @Override
    public void buildGraphicsCard() {
        computer.setGraphicsCard("Integrated Intel UHD Graphics 770");
        System.out.println("  ✓ Using integrated graphics (sufficient for office work)");
    }
    
    @Override
    public void buildOperatingSystem() {
        computer.setOperatingSystem("Windows 11 Professional");
        System.out.println("  ✓ Installed business-grade OS");
    }
    
    @Override
    public Computer getComputer() {
        return computer;
    }
}
