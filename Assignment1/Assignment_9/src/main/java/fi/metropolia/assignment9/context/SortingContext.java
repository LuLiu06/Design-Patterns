package fi.metropolia.assignment9.context;

import fi.metropolia.assignment9.strategy.SortingStrategy;

/**
 * Context class for the Strategy design pattern.
 * Maintains a reference to a SortingStrategy and delegates sorting to it.
 */
public class SortingContext {
    
    private SortingStrategy strategy;
    
    public SortingContext() {
        this.strategy = null;
    }
    
    public SortingContext(SortingStrategy strategy) {
        this.strategy = strategy;
    }
    
    /**
     * Sets the sorting strategy to be used.
     * @param strategy the sorting strategy
     */
    public void setStrategy(SortingStrategy strategy) {
        this.strategy = strategy;
    }
    
    /**
     * Gets the current sorting strategy.
     * @return the current strategy
     */
    public SortingStrategy getStrategy() {
        return strategy;
    }
    
    /**
     * Sorts the given array using the current strategy.
     * @param array the array to sort
     */
    public void sort(int[] array) {
        if (strategy == null) {
            throw new IllegalStateException("Sorting strategy not set!");
        }
        strategy.sort(array);
    }
    
    /**
     * Sorts the given array and measures the time taken.
     * @param array the array to sort
     * @return the time taken in nanoseconds
     */
    public long sortAndMeasureTime(int[] array) {
        if (strategy == null) {
            throw new IllegalStateException("Sorting strategy not set!");
        }
        
        long startTime = System.nanoTime();
        strategy.sort(array);
        long endTime = System.nanoTime();
        
        return endTime - startTime;
    }
    
    /**
     * Gets the name of the current sorting algorithm.
     * @return algorithm name
     */
    public String getAlgorithmName() {
        if (strategy == null) {
            return "No strategy set";
        }
        return strategy.getName();
    }
}
