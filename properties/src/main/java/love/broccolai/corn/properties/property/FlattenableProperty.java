package love.broccolai.corn.properties.property;

import org.jspecify.annotations.NullMarked;

import java.util.Map;

@NullMarked
public interface FlattenableProperty extends Property {

    /**
     * Flatten the property into a map of properties
     *
     * @return Map of keys to properties
     */
    Map<String, Property> flatten();

}
