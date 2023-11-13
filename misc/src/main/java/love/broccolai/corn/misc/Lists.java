package love.broccolai.corn.misc;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.function.Predicate;
import org.jspecify.annotations.NullMarked;

@NullMarked
public final class Lists {

    private Lists() {
    }

    /**
     * Group a list by a given function.
     *
     * @param <R>      Type to be grouped by
     * @param <T>      Type of the lists elements
     * @param input    List to be grouped
     * @param function Function to group the result by
     * @return Map of R as the keys and T as the values
     */
    public static <T, R> Map<R, List<T>> group(
        final Iterable<T> input,
        final Function<T, R> function
    ) {
        final Map<R, List<T>> output = new HashMap<>();

        for (final T value : input) {
            final R type = function.apply(value);
            output.putIfAbsent(type, new ArrayList<>());

            final List<T> current = output.get(type);
            current.add(value);

            output.put(type, current);
        }

        return output;
    }

    /**
     * Map a list using a given function.
     *
     * @param input    the list to be mapped
     * @param function the function to be applied to all elements
     * @param <R>      new type of the lists elements
     * @param <T>      current type of the lists elements
     * @return a list with the mapped elements
     */
    public static <R, T> List<R> map(
        final Iterable<T> input,
        final Function<T, R> function
    ) {
        final List<R> output = new ArrayList<>();

        for (final T value : input) {
            output.add(function.apply(value));
        }

        return output;
    }

    /**
     * Find the last element in a List, matching a predicate.
     *
     * @param input     the list to search
     * @param predicate the predicate to match against
     * @param <T>       type of list elements
     * @return the found value
     */
    public static <T> T last(
        final List<T> input,
        final Predicate<T> predicate
    ) {
        for (int i = input.size() - 1; i >= 0; i--) {
            final T value = input.get(i);

            if (predicate.test(value)) {
                return value;
            }
        }

        throw new IllegalArgumentException();
    }

}
