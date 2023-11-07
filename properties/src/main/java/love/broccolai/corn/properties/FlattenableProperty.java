package love.broccolai.corn.properties;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Map;

public interface FlattenableProperty extends Property {

    /**
     * Flatten the property into a map of properties
     *
     * @return Map of keys to properties
     */
    @NonNull Map<String, Property> flatten();

}
