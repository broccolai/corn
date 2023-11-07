package love.broccolai.corn.context;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;

public final class MappedContext implements Context {

    private final Map<ContextKey<?>, Object> entries = new HashMap<>();

    @Override
    @SuppressWarnings("unchecked")
    public <T> Optional<T> get(final @NonNull ContextKey<T> key) {
        T value = (T) this.entries.get(key);
        return Optional.ofNullable(value);
    }

    @Override
    public <T> void put(final @NonNull ContextKey<T> key, @NonNull final T value) {
        this.entries.put(key, value);
    }

    @Override
    public void forEach(final @NonNull BiConsumer<ContextKey<?>, Object> consumer) {
        this.entries.forEach(consumer);
    }

}
