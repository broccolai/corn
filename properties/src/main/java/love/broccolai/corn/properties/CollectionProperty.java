package love.broccolai.corn.properties;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

record CollectionProperty(
        @NonNull String name,
        @NonNull Collection<PropertyHolder> collection
) implements FlattenableProperty {

    @Override
    public @NonNull Map<String, Property> flatten() {
        Map<String, Property> results = new HashMap<>();

        for (final PropertyHolder propertyHolder : this.collection) {
            for (final Property property : propertyHolder.properties()) {
                results.put(this.name + ":" + property.name(), property);
            }
        }

        return results;
    }

}
