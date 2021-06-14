package broccolai.corn.context;

import broccolai.corn.properties.Property;
import broccolai.corn.properties.PropertySnapshot;
import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.ArrayList;
import java.util.Collection;
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

    @Override
    public @NonNull PropertySnapshot properties() {
        Collection<Property> properties = new ArrayList<>();
        this.entries.forEach((key, value) -> {
            properties.add(Property.of(key.namespace() + ":" + key.name(), value));
        });

        return PropertySnapshot.of(
                properties.toArray(new Property[0])
        );
    }

}
