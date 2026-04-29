package fi.metropolia.assignment20;

import java.util.Iterator;

/**
 * Demonstrates the Iterator pattern with a Fibonacci {@link Sequence}.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("=== First 10 Fibonacci numbers (1, 1, 2, 3, 5, …) ===");
        System.out.println("(bounded sequence: new FibonacciSequence(10))\n");

        Sequence bounded = new FibonacciSequence(10);
        Iterator<Integer> it = bounded.iterator();
        int index = 1;
        while (it.hasNext()) {
            System.out.printf("  F%d = %d%n", index++, it.next());
        }

        System.out.println("\n=== Two independent iterators from the same sequence ===");
        Sequence shared = new FibonacciSequence(5);
        Iterator<Integer> first = shared.iterator();
        Iterator<Integer> second = shared.iterator();
        System.out.print("Iterator A: ");
        while (first.hasNext()) {
            System.out.print(first.next() + " ");
        }
        System.out.print("\nIterator B: ");
        while (second.hasNext()) {
            System.out.print(second.next() + " ");
        }
        System.out.println("\n(both print 1 1 2 3 5 — separate cursors)");

        System.out.println("\n=== Unbounded iterator (first 12 values, client-side limit) ===");
        Iterator<Integer> infinite = new FibonacciSequence().iterator();
        for (int i = 0; i < 12 && infinite.hasNext(); i++) {
            System.out.print(infinite.next() + (i < 11 ? ", " : ""));
        }
        System.out.println();
    }
}
