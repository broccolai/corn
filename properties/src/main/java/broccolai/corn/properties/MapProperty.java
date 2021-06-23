package broccolai.corn.properties;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.HashMap;
import java.util.Map;

record MapProperty(
        @NonNull String name,
        @NonNull Map<String, PropertyHolder> entries
) implements FlattenableProperty {

    @Override
    public @NonNull Map<String, Property> flatten() {
        Map<String, Property> results = new HashMap<>();

        this.entries.forEach((key, propertyHolder) -> {
            String keyedName = this.name + ":" + key;
            for (final Property property : propertyHolder.properties()) {
                results.put(keyedName + ":" + property.name(), property);
            }
        });

        return results;
    }

}
