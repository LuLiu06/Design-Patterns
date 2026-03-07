package fi.metropolia.assignment9.strategy;

/**
 * Quick Sort implementation of the SortingStrategy interface.
 * 
 * Time Complexity: O(n log n) average case, O(n^2) worst case
 * Space Complexity: O(log n) due to recursion stack
 * 
 * Reference: https://www.geeksforgeeks.org/quick-sort/
 * 
 * How it works:
 * - Picks an element as a pivot (here we use the last element)
 * - Partitions the array around the pivot: smaller elements to the left, larger to the right
 * - Recursively applies the same logic to the left and right subarrays
 * - This is a divide-and-conquer algorithm
 */
public class QuickSort implements SortingStrategy {
    
    @Override
    public void sort(int[] array) {
        quickSort(array, 0, array.length - 1);
    }
    
    private void quickSort(int[] array, int low, int high) {
        if (low < high) {
            // Find partition index
            int partitionIndex = partition(array, low, high);
            
            // Recursively sort elements before and after partition
            quickSort(array, low, partitionIndex - 1);
            quickSort(array, partitionIndex + 1, high);
        }
    }
    
    private int partition(int[] array, int low, int high) {
        // Choose the rightmost element as pivot
        int pivot = array[high];
        
        // Index of smaller element
        int i = low - 1;
        
        for (int j = low; j < high; j++) {
            // If current element is smaller than or equal to pivot
            if (array[j] <= pivot) {
                i++;
                // Swap array[i] and array[j]
                int temp = array[i];
                array[i] = array[j];
                array[j] = temp;
            }
        }
        
        // Swap array[i+1] and array[high] (put pivot in correct position)
        int temp = array[i + 1];
        array[i + 1] = array[high];
        array[high] = temp;
        
        return i + 1;
    }
    
    @Override
    public String getName() {
        return "Quick Sort";
    }
}
