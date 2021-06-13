package broccolai.corn.properties;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;

final class PropertySnapshotImpl implements PropertySnapshot {

    private final Map<String, Property> flattenedProperties;

    PropertySnapshotImpl(final @NonNull Collection<@NonNull Property> properties) {
        this.flattenedProperties = this.flatten(properties);
    }

    @Override
    public @NonNull Iterator<Property> iterator() {
        return this.flattenedProperties.values().iterator();
    }

    private Map<String, Property> flatten(final @NonNull Iterable<@NonNull Property> properties) {
        Map<String, Property> intermediaryProperties = new HashMap<>();

        for (final Property property : properties) {
            if (property instanceof ObjectProperty) {
                intermediaryProperties.put(property.name(), property);
                continue;
            }

            if (property instanceof NestedProperty nestedProperty) {
                PropertyHolder propertyHolder = nestedProperty.propertyHolder();

                if (propertyHolder != null) {
                    intermediaryProperties.putAll(this.flatten(propertyHolder.properties()));
                }

                continue;
            }
        }

        return Collections.unmodifiableMap(intermediaryProperties);
    }

    @Override
    public int hashCode() {
        return Objects.hash(flattenedProperties);
    }

    @Override
    public boolean equals(final Object that) {
        if (!(that instanceof PropertySnapshotImpl propertySnapshot)) {
            return false;
        }

        if (this == that) {
            return true;
        }

        Map<String, Property> targetProperties = propertySnapshot.flattenedProperties;

        if (this.flattenedProperties.size() != targetProperties.size()) {
            return false;
        }

        for (Map.Entry<String, Property> entry : this.flattenedProperties.entrySet()) {
            String name = entry.getKey();
            Property property = entry.getValue();

            if (!targetProperties.containsKey(name)) {
                return false;
            }

            if (!targetProperties.get(name).equals(property)) {
                return false;
            }
        }

        return true;
    }

}
