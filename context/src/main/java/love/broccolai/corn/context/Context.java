package love.broccolai.corn.context;

import java.util.Optional;
import java.util.function.BiConsumer;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface Context {

    /**
     * Get a value out of the context through a key.
     *
     * @param key Context key to query against
     * @param <T> Type associated with the key
     * @return Optional of the stored context key assigned ot the T type
     */
    <T> Optional<T> get(ContextKey<T> key);

    /**
     * Store a value into the context, defined by a key.
     *
     * @param key   Context key to assign with
     * @param value Value to store against the key
     * @param <T>   Type associated with the key and value
     */
    <T> void put(ContextKey<T> key, T value);

    /**
     * Performs given consumer on all entries stored.
     *
     * @param consumer Consumer to be applied
     */
    void forEach(BiConsumer<ContextKey<?>, Object> consumer);

}
