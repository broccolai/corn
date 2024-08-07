package love.broccolai.corn.properties.property;

import java.util.HashMap;
import java.util.Map;
import love.broccolai.corn.properties.PropertyHolder;
import org.jspecify.annotations.NullMarked;

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
