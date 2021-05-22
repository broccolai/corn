package broccolai.corn.context;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Optional;

public interface Context {

    /**
     * Get a value out of the context through a key.
     *
     * @param key Context key to query against
     * @param <T> Type associated with the key
     * @return Optional of the stored context key assigned ot the T type
     */
    <T> Optional<T> get(@NonNull ContextKey<T> key);

    /**
     * Store a value into the context, defined by a key.
     *
     * @param key Context key to assign with
     * @param value Value to store against the key
     * @param <T> Type associated with the key and value
     */
    <T> void put(@NonNull ContextKey<T> key, @NonNull T value);

}
