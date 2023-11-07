package love.broccolai.corn.properties;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

record NestedProperty(@NonNull String name, @Nullable PropertyHolder propertyHolder) implements FlattenableProperty {

    @Override
    public @NonNull Map<String, Property> flatten() {
        if (propertyHolder == null) {
            return Collections.emptyMap();
        }

        Map<String, Property> results = new HashMap<>();

        for (final Property property : this.propertyHolder.properties()) {
            if (property instanceof FlattenableProperty flattenableProperty) {
                flattenableProperty.flatten().forEach((key, value) -> {
                    results.put(property.name() + ":" + key, value);
                });
                continue;
            }

            results.put(property.name(), property);
        }

        return results;
    }

}
