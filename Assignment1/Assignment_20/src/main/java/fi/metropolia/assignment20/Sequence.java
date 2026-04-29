package fi.metropolia.assignment20;

import java.util.Iterator;

/**
 * Aggregate role (named "Sequence" in the assignment) for a computed series.
 * Exposes an iterator without requiring a backing static collection.
 */
public interface Sequence {

    /**
     * Creates an iterator over this sequence.
     * Each call should return a new iterator with its own traversal state.
     *
     * @return iterator of Fibonacci values as {@link Integer}
     */
    Iterator<Integer> iterator();
}
