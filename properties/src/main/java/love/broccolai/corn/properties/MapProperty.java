package love.broccolai.corn.properties;

import org.jspecify.annotations.NullMarked;

import java.util.HashMap;
import java.util.Map;

@NullMarked
record MapProperty(
        String name,
        Map<String, PropertyHolder> entries
) implements FlattenableProperty {

    @Override
    public Map<String, Property> flatten() {
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
