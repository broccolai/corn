package broccolai.corn.core;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

public final class Lists {

    private Lists() {
    }

    /**
     * Group a list by a given function
     *
     * @param <R>      Type to be grouped by
     * @param <T>      Type of the lists elements
     * @param input    List to be grouped
     * @param function Function to group the result by
     * @return Map of R as the keys and T as the values
     */
    public static <@Nullable T, @Nullable R> @NonNull Map<R, @NonNull List<T>> group(
            final @NonNull Iterable<T> input,
            final @NonNull Function<T, R> function
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
    public static <@Nullable R, @Nullable T> @NonNull List<R> map(
            final @NonNull Iterable<T> input,
            final @NonNull Function<T, R> function
    ) {
        final List<R> output = new ArrayList<>();

        for (final T value : input) {
            output.add(function.apply(value));
        }

        return output;
    }

}
