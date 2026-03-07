package fi.metropolia.assignment9.strategy;

/**
 * Bubble Sort implementation of the SortingStrategy interface.
 * 
 * Time Complexity: O(n^2) in worst and average case
 * Space Complexity: O(1)
 * 
 * Reference: https://www.geeksforgeeks.org/bubble-sort/
 * 
 * How it works:
 * - Repeatedly steps through the list, compares adjacent elements
 * - Swaps them if they are in the wrong order
 * - The pass through the list is repeated until the list is sorted
 * - Largest elements "bubble up" to the end of the array
 */
public class BubbleSort implements SortingStrategy {
    
    @Override
    public void sort(int[] array) {
        int n = array.length;
        boolean swapped;
        
        for (int i = 0; i < n - 1; i++) {
            swapped = false;
            
            for (int j = 0; j < n - i - 1; j++) {
                if (array[j] > array[j + 1]) {
                    // Swap array[j] and array[j+1]
                    int temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;
                    swapped = true;
                }
            }
            
            // If no swapping occurred in this pass, array is already sorted
            if (!swapped) {
                break;
            }
        }
    }
    
    @Override
    public String getName() {
        return "Bubble Sort";
    }
}
