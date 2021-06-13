package broccolai.corn.properties;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Collection;
import java.util.Iterator;

public record CollectionProperty(
        @NonNull String name,
        @NonNull Collection<PropertyHolder> collection
) implements Iterable<PropertyHolder>, Property {

    @Override
    public @NonNull Iterator<PropertyHolder> iterator() {
        return this.collection.iterator();
    }

}
