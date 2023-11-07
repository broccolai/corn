package love.broccolai.corn.properties;

import org.checkerframework.checker.nullness.qual.NonNull;

public interface SafeProperty extends Property {

    /**
     * Construct a Property with an Object as it's value
     *
     * @param name   Name of the property
     * @param object Value of the property
     * @return Constructed Property
     */
    static @NonNull Property of(final @NonNull String name, final @NonNull Object object) {
        return new ObjectSafeProperty(name, object);
    }

}
