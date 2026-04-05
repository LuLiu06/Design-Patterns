package fi.metropolia.assignment15;

/**
 * New date interface that the client code will use.
 * Provides simple methods for date manipulation.
 */
public interface NewDateInterface {
    
    /**
     * Sets the day of the month.
     * @param day day of the month (1-31)
     */
    void setDay(int day);
    
    /**
     * Sets the month.
     * @param month month (1-12, where 1 = January, 12 = December)
     */
    void setMonth(int month);
    
    /**
     * Sets the year.
     * @param year the year (e.g., 2024)
     */
    void setYear(int year);
    
    /**
     * Gets the current day of the month.
     * @return day of the month (1-31)
     */
    int getDay();
    
    /**
     * Gets the current month.
     * @return month (1-12, where 1 = January, 12 = December)
     */
    int getMonth();
    
    /**
     * Gets the current year.
     * @return the year (e.g., 2024)
     */
    int getYear();
    
    /**
     * Advances the date by the specified number of days.
     * @param days number of days to advance (can be negative to go back)
     */
    void advanceDays(int days);
}
