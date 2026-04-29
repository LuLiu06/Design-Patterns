package fi.metropolia.assignment20;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Iterator over a Fibonacci {@link FibonacciSequence}.
 * <p>
 * <b>State placement (assignment reflection):</b><br>
 * All traversal state — the two consecutive values {@code a} and {@code b} used to produce the next
 * term, plus how many values have been emitted — lives in <em>this</em> iterator instance.
 * {@link FibonacciSequence} does not mutate when you iterate; it only describes bounds.
 * <ul>
 *   <li><b>Independence:</b> two iterators from the same {@code FibonacciSequence} advance separately.</li>
 *   <li><b>Sharing:</b> if state lived on the sequence, iterators would share one cursor and interfere.</li>
 *   <li><b>Role split:</b> the sequence is the factory / policy (bounded or not); the iterator is the
 *       mechanism that knows "where we are" in the stream of computed ints.</li>
 * </ul>
 * We use {@code long} for {@code a} and {@code b} while computing the next value to postpone overflow
 * slightly; results are still returned as {@link Integer} per the API contract.
 */
public class FibonacciIterator implements Iterator<Integer> {

    private final FibonacciSequence sequence;

    /** Consecutive Fibonacci values; invariant before next(): next emitted value is (int) a. */
    private long a = 1;
    private long b = 1;

    private int producedCount = 0;

    FibonacciIterator(FibonacciSequence sequence) {
        this.sequence = sequence;
    }

    @Override
    public boolean hasNext() {
        if (sequence.isBounded()) {
            return producedCount < sequence.getMaxElements();
        }
        // Unbounded: stop if next Fibonacci step would not fit in positive long range sensibly
        if (a <= 0 || b < 0) {
            return false;
        }
        long sum = a + b;
        return sum > 0 && a <= Integer.MAX_VALUE;
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        int value = (int) a;
        long nextA = b;
        long nextB = a + b;
        a = nextA;
        b = nextB;
        producedCount++;
        return value;
    }
}
