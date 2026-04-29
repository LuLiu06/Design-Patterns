package fi.metropolia.assignment20;

import java.util.Iterator;

/**
 * Fibonacci sequence as a "pseudo-collection": values are computed on demand.
 * <p>
 * <b>Bounded vs unbounded:</b>
 * <ul>
 *   <li>{@code new FibonacciSequence()} — iterator runs until integer overflow is detected.</li>
 *   <li>{@code new FibonacciSequence(n)} — iterator stops after {@code n} values (e.g. first 10 numbers).</li>
 * </ul>
 * <p>
 * <b>Where state lives (design note):</b><br>
 * The rolling Fibonacci pair (previous two values) is kept inside {@link FibonacciIterator}, not here.
 * This class only stores configuration ({@code maxElements} if bounded). That way, each call to
 * {@link #iterator()} yields an <em>independent</em> traversal: two iterators do not advance each
 * other's state. If the pair were stored on {@code FibonacciSequence}, sharing one sequence among
 * several iterators would either require cloning state per iterator anyway, or iterators would
 * corrupt each other's position — so iterator-local state is the clearer choice.
 */
public class FibonacciSequence implements Sequence {

    /**
     * {@code null} = unbounded (until overflow); otherwise stop after this many values.
     */
    private final Integer maxElements;

    /**
     * Unbounded sequence (limited in practice by {@code int} overflow guards in the iterator).
     */
    public FibonacciSequence() {
        this.maxElements = null;
    }

    /**
     * Bounded sequence: exactly {@code maxElements} Fibonacci numbers (starting 1, 1, …).
     *
     * @param maxElements positive count; e.g. 10 for the first ten numbers
     */
    public FibonacciSequence(int maxElements) {
        if (maxElements <= 0) {
            throw new IllegalArgumentException("maxElements must be positive: " + maxElements);
        }
        this.maxElements = maxElements;
    }

    boolean isBounded() {
        return maxElements != null;
    }

    int getMaxElements() {
        return maxElements != null ? maxElements : Integer.MAX_VALUE;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new FibonacciIterator(this);
    }
}
