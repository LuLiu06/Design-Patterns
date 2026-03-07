package fi.metropolia.assignment9.strategy;

/**
 * Strategy interface for the Strategy design pattern.
 * Defines the contract for all sorting algorithm implementations.
 */
public interface SortingStrategy {
    
    /**
     * Sorts the given array of integers in ascending order.
     * @param array the array to be sorted
     */
    void sort(int[] array);
    
    /**
     * Returns the name of the sorting algorithm.
     * @return algorithm name
     */
    String getName();
}
