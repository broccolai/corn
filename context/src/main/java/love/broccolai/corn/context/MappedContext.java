package love.broccolai.corn.context;

import org.jspecify.annotations.NullMarked;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiConsumer;

@NullMarked
public final class MappedContext implements Context {

    private final Map<ContextKey<?>, Object> entries = new HashMap<>();

    @Override
    @SuppressWarnings("unchecked")
    public <T> Optional<T> get(final ContextKey<T> key) {
        T value = (T) this.entries.get(key);
        return Optional.ofNullable(value);
    }

    @Override
    public <T> void put(final ContextKey<T> key, final T value) {
        this.entries.put(key, value);
    }

    @Override
    public void forEach(final BiConsumer<ContextKey<?>, Object> consumer) {
        this.entries.forEach(consumer);
    }

}
