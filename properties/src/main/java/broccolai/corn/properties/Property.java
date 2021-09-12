package broccolai.corn.properties;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Collection;

public interface Property {

    /**
     * Get the name of the property
     *
     * @return Name of the property
     */
    @NonNull String name();

    /**
     * Construct a Property with an Object as it's value
     *
     * @param name   Name of the property
     * @param object Value of the property
     * @return Constructed Property
     */
    static @NonNull Property of(final @NonNull String name, final @NonNull Object object) {
        return new ObjectProperty(name, object);
    }

    /**
     * Construct a Property with a {@link PropertyHolder} as it's value
     *
     * @param name           Name of the property
     * @param propertyHolder Value of the property
     * @return Constructed Property
     */
    static @NonNull Property of(final @NonNull String name, final @NonNull PropertyHolder propertyHolder) {
        return new NestedProperty(name, propertyHolder);
    }

    /**
     * Construct a Property with a {@link Collection<PropertyHolder>} as it's value
     *
     * @param name               Name of the property
     * @param propertyCollection Value of the property
     * @return Constructed Property
     */
    static @NonNull Property of(final @NonNull String name, final @NonNull Collection<PropertyHolder> propertyCollection) {
        return new CollectionProperty(name, propertyCollection);
    }

}
