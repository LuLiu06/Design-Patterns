package fi.metropolia.assignment9;

import fi.metropolia.assignment9.context.SortingContext;
import fi.metropolia.assignment9.strategy.*;

import java.util.Arrays;
import java.util.Random;

/**
 * Main class for testing and comparing sorting algorithm performance.
 * Demonstrates the Strategy design pattern with three sorting algorithms.
 */
public class Main {
    
    private static final int SMALL_ARRAY_SIZE = 30;
    private static final int LARGE_ARRAY_SIZE = 100_000;
    private static final int RANDOM_BOUND = 1_000_000;
    
    public static void main(String[] args) {
        printHeader();
        
        // Create sorting strategies
        SortingStrategy[] strategies = {
            new BubbleSort(),
            new QuickSort(),
            new MergeSort()
        };
        
        // Create context
        SortingContext context = new SortingContext();
        
        // Test with small array
        System.out.println("\n" + "═".repeat(60));
        System.out.println("TEST 1: Small Array (" + SMALL_ARRAY_SIZE + " elements)");
        System.out.println("═".repeat(60));
        
        int[] smallOriginal = generateRandomArray(SMALL_ARRAY_SIZE);
        System.out.println("\nOriginal array (first 30 elements):");
        printArray(smallOriginal, 30);
        
        for (SortingStrategy strategy : strategies) {
            int[] arrayCopy = Arrays.copyOf(smallOriginal, smallOriginal.length);
            context.setStrategy(strategy);
            
            long timeNano = context.sortAndMeasureTime(arrayCopy);
            
            System.out.println("\n" + "-".repeat(50));
            System.out.printf("Algorithm: %s%n", context.getAlgorithmName());
            System.out.printf("Time: %,d nanoseconds (%.4f ms)%n", timeNano, timeNano / 1_000_000.0);
            System.out.println("Sorted array:");
            printArray(arrayCopy, 30);
            System.out.println("Verification: " + (isSorted(arrayCopy) ? "✓ PASSED" : "✗ FAILED"));
        }
        
        // Test with large array
        System.out.println("\n\n" + "═".repeat(60));
        System.out.println("TEST 2: Large Array (" + String.format("%,d", LARGE_ARRAY_SIZE) + " elements)");
        System.out.println("═".repeat(60));
        
        int[] largeOriginal = generateRandomArray(LARGE_ARRAY_SIZE);
        System.out.println("\nOriginal array (first 20 elements): ");
        printArray(largeOriginal, 20);
        System.out.println("...");
        
        // Store results for comparison
        String[] names = new String[strategies.length];
        long[] times = new long[strategies.length];
        
        for (int i = 0; i < strategies.length; i++) {
            int[] arrayCopy = Arrays.copyOf(largeOriginal, largeOriginal.length);
            context.setStrategy(strategies[i]);
            
            long timeNano = context.sortAndMeasureTime(arrayCopy);
            names[i] = context.getAlgorithmName();
            times[i] = timeNano;
            
            System.out.println("\n" + "-".repeat(50));
            System.out.printf("Algorithm: %s%n", names[i]);
            System.out.printf("Time: %,d nanoseconds (%.2f ms)%n", timeNano, timeNano / 1_000_000.0);
            System.out.println("Sorted array (first 20 elements):");
            printArray(arrayCopy, 20);
            System.out.println("...");
            System.out.println("Verification: " + (isSorted(arrayCopy) ? "✓ PASSED" : "✗ FAILED"));
        }
        
        // Print performance summary
        printSummary(names, times);
    }
    
    private static void printHeader() {
        System.out.println("╔══════════════════════════════════════════════════════════╗");
        System.out.println("║     SORTING ALGORITHM PERFORMANCE COMPARISON             ║");
        System.out.println("║     ───────────────────────────────────────              ║");
        System.out.println("║     Strategy Pattern Implementation                      ║");
        System.out.println("╚══════════════════════════════════════════════════════════╝");
        System.out.println("\nAlgorithms being compared:");
        System.out.println("  1. Bubble Sort - O(n²) - Simple comparison-based sort");
        System.out.println("  2. Quick Sort  - O(n log n) avg - Divide and conquer");
        System.out.println("  3. Merge Sort  - O(n log n) - Stable divide and conquer");
    }
    
    private static int[] generateRandomArray(int size) {
        Random random = new Random(42); // Fixed seed for reproducibility
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = random.nextInt(RANDOM_BOUND);
        }
        return array;
    }
    
    private static void printArray(int[] array, int maxElements) {
        int limit = Math.min(array.length, maxElements);
        StringBuilder sb = new StringBuilder("[");
        for (int i = 0; i < limit; i++) {
            sb.append(array[i]);
            if (i < limit - 1) {
                sb.append(", ");
            }
        }
        if (array.length > maxElements) {
            sb.append(", ...");
        }
        sb.append("]");
        System.out.println(sb);
    }
    
    private static boolean isSorted(int[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            if (array[i] > array[i + 1]) {
                return false;
            }
        }
        return true;
    }
    
    private static void printSummary(String[] names, long[] times) {
        System.out.println("\n\n" + "═".repeat(60));
        System.out.println("PERFORMANCE SUMMARY (Large Array)");
        System.out.println("═".repeat(60));
        
        // Find fastest
        int fastestIndex = 0;
        for (int i = 1; i < times.length; i++) {
            if (times[i] < times[fastestIndex]) {
                fastestIndex = i;
            }
        }
        
        System.out.println("\n┌────────────────────┬──────────────────┬──────────────┐");
        System.out.println("│ Algorithm          │ Time (ms)        │ Relative     │");
        System.out.println("├────────────────────┼──────────────────┼──────────────┤");
        
        for (int i = 0; i < names.length; i++) {
            double timeMs = times[i] / 1_000_000.0;
            double relative = (double) times[i] / times[fastestIndex];
            String marker = (i == fastestIndex) ? " ★" : "";
            
            System.out.printf("│ %-18s │ %14.2f ms │ %10.2fx%s │%n", 
                    names[i], timeMs, relative, marker);
        }
        
        System.out.println("└────────────────────┴──────────────────┴──────────────┘");
        System.out.println("\n★ = Fastest algorithm");
        
        System.out.println("\n" + "═".repeat(60));
        System.out.println("ANALYSIS");
        System.out.println("═".repeat(60));
        System.out.println("""
            
            Bubble Sort: O(n²) complexity makes it extremely slow for large
            datasets. Only suitable for small arrays or nearly sorted data.
            
            Quick Sort: O(n log n) average case makes it very efficient.
            However, worst case is O(n²) with already sorted data.
            
            Merge Sort: O(n log n) guaranteed performance. Slightly more
            memory overhead but consistent and stable sorting.
            """);
    }
}
