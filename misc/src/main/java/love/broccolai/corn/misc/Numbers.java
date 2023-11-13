package love.broccolai.corn.misc;

import java.util.function.Function;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@NullMarked
public final class Numbers {

    private Numbers() {
    }

    /**
     * Get the value of a String or null if it could not be read.
     *
     * @param input    String to get the number from
     * @param function Function to be used to find value
     * @param <T>      Type of number
     * @return Parsed number or null if it could not be parsed
     */
    public static <T extends Number> @Nullable T valueOrNull(
        final @Nullable String input,
        final Function<String, T> function
    ) {
        if (input == null) {
            return null;
        }

        try {
            return function.apply(input);
        } catch (Exception e) {
            return null;
        }
    }

}
