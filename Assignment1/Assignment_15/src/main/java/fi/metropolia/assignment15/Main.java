package fi.metropolia.assignment15;

/**
 * Main class (client code) demonstrating the Calendar Adapter pattern.
 * Uses NewDateInterface to work with dates, without directly dealing with Calendar API.
 */
public class Main {
    
    public static void main(String[] args) {
        printHeader();
        
        // =====================================================
        // SCENARIO 1: Create adapter with current date
        // =====================================================
        printScenario(1, "Creating Adapter with Current Date");
        
        NewDateInterface date1 = new CalendarToNewDateAdapter();
        printDateInfo("Initial date", date1);
        
        // =====================================================
        // SCENARIO 2: Set a specific date
        // =====================================================
        printScenario(2, "Setting a Specific Date");
        
        NewDateInterface date2 = new CalendarToNewDateAdapter();
        System.out.println("Setting date to: January 1, 2024");
        date2.setYear(2024);
        date2.setMonth(1);  // 1 = January
        date2.setDay(1);
        printDateInfo("New Year's Day 2024", date2);
        
        // =====================================================
        // SCENARIO 3: Advance days forward
        // =====================================================
        printScenario(3, "Advancing Days Forward");
        
        NewDateInterface date3 = new CalendarToNewDateAdapter(2024, 12, 25);
        printDateInfo("Starting date (Christmas 2024)", date3);
        
        System.out.println("\nAdvancing 7 days forward...");
        date3.advanceDays(7);
        printDateInfo("After advancing 7 days", date3);
        
        System.out.println("\nAdvancing 30 days forward...");
        date3.advanceDays(30);
        printDateInfo("After advancing 30 more days", date3);
        
        // =====================================================
        // SCENARIO 4: Advance days backward
        // =====================================================
        printScenario(4, "Going Back in Time");
        
        NewDateInterface date4 = new CalendarToNewDateAdapter(2026, 3, 30);
        printDateInfo("Starting date", date4);
        
        System.out.println("\nGoing back 10 days...");
        date4.advanceDays(-10);
        printDateInfo("After going back 10 days", date4);
        
        System.out.println("\nGoing back 100 days...");
        date4.advanceDays(-100);
        printDateInfo("After going back 100 more days", date4);
        
        // =====================================================
        // SCENARIO 5: Month boundary crossing
        // =====================================================
        printScenario(5, "Testing Month and Year Boundaries");
        
        NewDateInterface date5 = new CalendarToNewDateAdapter(2024, 1, 28);
        printDateInfo("Starting: End of January 2024", date5);
        
        System.out.println("\nAdvancing 5 days (crossing into February)...");
        date5.advanceDays(5);
        printDateInfo("After crossing month boundary", date5);
        
        NewDateInterface date6 = new CalendarToNewDateAdapter(2024, 12, 30);
        printDateInfo("\nStarting: End of December 2024", date6);
        
        System.out.println("\nAdvancing 5 days (crossing into new year)...");
        date6.advanceDays(5);
        printDateInfo("After crossing year boundary", date6);
        
        // =====================================================
        // SCENARIO 6: Leap year handling
        // =====================================================
        printScenario(6, "Leap Year Handling");
        
        NewDateInterface leapYear = new CalendarToNewDateAdapter(2024, 2, 28);
        printDateInfo("Feb 28, 2024 (leap year)", leapYear);
        
        System.out.println("\nAdvancing 1 day...");
        leapYear.advanceDays(1);
        printDateInfo("Next day (Feb 29 exists in leap year)", leapYear);
        
        System.out.println("\nAdvancing 1 more day...");
        leapYear.advanceDays(1);
        printDateInfo("Next day (March 1)", leapYear);
        
        // =====================================================
        // SCENARIO 7: Multiple operations
        // =====================================================
        printScenario(7, "Complex Date Manipulation");
        
        NewDateInterface date7 = new CalendarToNewDateAdapter();
        System.out.println("Starting with current date");
        printDateInfo("Current", date7);
        
        System.out.println("\nSetting to: March 15, 2025");
        date7.setYear(2025);
        date7.setMonth(3);
        date7.setDay(15);
        printDateInfo("After setting", date7);
        
        System.out.println("\nAdvancing 45 days...");
        date7.advanceDays(45);
        printDateInfo("After advancing 45 days", date7);
        
        System.out.println("\nGoing back 20 days...");
        date7.advanceDays(-20);
        printDateInfo("After going back 20 days", date7);
        
        // =====================================================
        // SUMMARY
        // =====================================================
        printSummary();
    }
    
    private static void printDateInfo(String label, NewDateInterface date) {
        System.out.println("\n" + label + ":");
        System.out.println("  Date: " + date);
        
        // Additional formatting if adapter supports it
        if (date instanceof CalendarToNewDateAdapter adapter) {
            System.out.println("  Formatted: " + adapter.getFormattedDate());
            System.out.println("  Day of week: " + adapter.getDayOfWeek());
        }
        
        System.out.println("  Year: " + date.getYear());
        System.out.println("  Month: " + date.getMonth());
        System.out.println("  Day: " + date.getDay());
    }
    
    private static void printHeader() {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║     CALENDAR ADAPTER - ADAPTER PATTERN DEMO              ║");
        System.out.println("║     ─────────────────────────────────────────            ║");
        System.out.println("║     Adapting java.util.Calendar to NewDateInterface      ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
    }
    
    private static void printScenario(int number, String title) {
        System.out.println("\n" + "═".repeat(60));
        System.out.printf("SCENARIO %d: %s%n", number, title);
        System.out.println("═".repeat(60));
    }
    
    private static void printSummary() {
        System.out.println("\n" + "═".repeat(60));
        System.out.println("ADAPTER PATTERN BENEFITS DEMONSTRATED");
        System.out.println("═".repeat(60));
        
        System.out.println("""
                
                ✓ Simplified Interface:
                  - NewDateInterface provides intuitive methods
                  - No need to understand Calendar's complex API
                  - 1-based months (1=Jan) instead of 0-based
                
                ✓ Encapsulation:
                  - Calendar complexity hidden from client
                  - Easy to switch implementations if needed
                  - Client code remains simple and clean
                
                ✓ Automatic Handling:
                  - Month/year boundaries handled automatically
                  - Leap years handled correctly
                  - No manual date arithmetic required
                
                ✓ Flexibility:
                  - Can add extra utility methods (getDayOfWeek, etc.)
                  - Client uses familiar interface
                  - Easy to extend functionality
                """);
        
        System.out.println("═".repeat(60));
        System.out.println("Demo completed successfully!");
        System.out.println("═".repeat(60));
    }
}
