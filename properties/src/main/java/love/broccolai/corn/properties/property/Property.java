package love.broccolai.corn.properties.property;

import java.util.Collection;
import love.broccolai.corn.properties.PropertyHolder;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface Property {

    /**
     * Get the name of the property.
     *
     * @return Name of the property
     */
    String name();

    /**
     * Construct a Property with an Object as it's value.
     *
     * @param name   Name of the property
     * @param object Value of the property
     * @return Constructed Property
     */
    static Property of(final String name, final Object object) {
        return new ObjectProperty(name, object);
    }

    /**
     * Construct a Property with a {@link PropertyHolder} as it's value.
     *
     * @param name           Name of the property
     * @param propertyHolder Value of the property
     * @return Constructed Property
     */
    static Property of(final String name, final PropertyHolder propertyHolder) {
        return new NestedProperty(name, propertyHolder);
    }

    /**
     * Construct a Property with a {@link Collection} as its value.
     *
     * @param name               Name of the property
     * @param propertyCollection Value of the property
     * @return Constructed Property
     */
    static Property of(final String name, final Collection<PropertyHolder> propertyCollection) {
        return new CollectionProperty(name, propertyCollection);
    }

}
