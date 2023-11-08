package love.broccolai.corn.properties;

import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.Objects;

@NullMarked
final class ObjectSafeProperty implements Property {

    private final String name;
    private final @Nullable Object object;

    ObjectSafeProperty(final String name, final @Nullable Object object) {
        this.name = name;
        this.object = object;
    }

    @Override
    public String name() {
        return name;
    }

    @Override
    public boolean equals(final @Nullable Object obj) {
        if (obj == this) {
            return true;
        }

        if (!(obj instanceof ObjectSafeProperty objectProperty)) {
            return false;
        }

        return Objects.equals(this.object, objectProperty.object);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, object);
    }

}
