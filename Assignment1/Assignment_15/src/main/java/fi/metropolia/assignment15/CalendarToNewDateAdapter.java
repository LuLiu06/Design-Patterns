package fi.metropolia.assignment15;

import java.util.Calendar;

/**
 * Adapter class that adapts java.util.Calendar to NewDateInterface.
 * This allows client code to use the simpler NewDateInterface while
 * internally using the more complex Calendar API.
 */
public class CalendarToNewDateAdapter implements NewDateInterface {
    
    private final Calendar calendar;
    
    /**
     * Creates a new adapter with a Calendar instance initialized to current date.
     */
    public CalendarToNewDateAdapter() {
        this.calendar = Calendar.getInstance();
    }
    
    /**
     * Creates a new adapter with a Calendar instance initialized to the specified date.
     * @param year the year
     * @param month the month (1-12)
     * @param day the day of the month
     */
    public CalendarToNewDateAdapter(int year, int month, int day) {
        this.calendar = Calendar.getInstance();
        setYear(year);
        setMonth(month);
        setDay(day);
    }
    
    @Override
    public void setDay(int day) {
        // Calendar uses DAY_OF_MONTH field
        calendar.set(Calendar.DAY_OF_MONTH, day);
    }
    
    @Override
    public void setMonth(int month) {
        // Calendar months are 0-based (0 = January, 11 = December)
        // Our interface uses 1-based (1 = January, 12 = December)
        calendar.set(Calendar.MONTH, month - 1);
    }
    
    @Override
    public void setYear(int year) {
        calendar.set(Calendar.YEAR, year);
    }
    
    @Override
    public int getDay() {
        return calendar.get(Calendar.DAY_OF_MONTH);
    }
    
    @Override
    public int getMonth() {
        // Convert Calendar's 0-based month to 1-based
        return calendar.get(Calendar.MONTH) + 1;
    }
    
    @Override
    public int getYear() {
        return calendar.get(Calendar.YEAR);
    }
    
    @Override
    public void advanceDays(int days) {
        // Calendar.add() handles month/year rollovers automatically
        calendar.add(Calendar.DAY_OF_MONTH, days);
    }
    
    /**
     * Gets the day of the week as a string.
     * @return day name (e.g., "Monday")
     */
    public String getDayOfWeek() {
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        String[] days = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
        return days[dayOfWeek - 1];
    }
    
    /**
     * Gets the month name as a string.
     * @return month name (e.g., "January")
     */
    public String getMonthName() {
        String[] months = {
            "January", "February", "March", "April", "May", "June",
            "July", "August", "September", "October", "November", "December"
        };
        return months[calendar.get(Calendar.MONTH)];
    }
    
    /**
     * Returns a formatted date string.
     * @return formatted date (e.g., "Monday, March 30, 2026")
     */
    public String getFormattedDate() {
        return String.format("%s, %s %d, %d",
                getDayOfWeek(),
                getMonthName(),
                getDay(),
                getYear());
    }
    
    @Override
    public String toString() {
        return String.format("%04d-%02d-%02d", getYear(), getMonth(), getDay());
    }
}
