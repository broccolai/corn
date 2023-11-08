package love.broccolai.corn.properties;

import org.jspecify.annotations.NullMarked;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@NullMarked
record CollectionProperty(
        String name,
        Collection<PropertyHolder> collection
) implements FlattenableProperty {

    @Override
    public Map<String, Property> flatten() {
        Map<String, Property> results = new HashMap<>();

        for (final PropertyHolder propertyHolder : this.collection) {
            for (final Property property : propertyHolder.properties()) {
                results.put(this.name + ":" + property.name(), property);
            }
        }

        return results;
    }

}
