package love.broccolai.corn.properties;

import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

@NullMarked
record NestedProperty(String name, @Nullable PropertyHolder propertyHolder) implements FlattenableProperty {

    @Override
    public Map<String, Property> flatten() {
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
