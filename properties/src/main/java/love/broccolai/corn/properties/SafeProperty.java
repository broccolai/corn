package love.broccolai.corn.properties;

import org.jspecify.annotations.NullMarked;

@NullMarked
public interface SafeProperty extends Property {

    /**
     * Construct a Property with an Object as it's value
     *
     * @param name   Name of the property
     * @param object Value of the property
     * @return Constructed Property
     */
    static Property of(final String name, final Object object) {
        return new ObjectSafeProperty(name, object);
    }

}
