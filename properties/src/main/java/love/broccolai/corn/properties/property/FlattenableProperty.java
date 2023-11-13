package love.broccolai.corn.properties.property;

import java.util.Map;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface FlattenableProperty extends Property {

    /**
     * Flatten the property into a map of properties.
     *
     * @return Map of keys to properties
     */
    Map<String, Property> flatten();

}
