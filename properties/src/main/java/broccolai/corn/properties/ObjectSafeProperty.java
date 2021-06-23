package broccolai.corn.properties;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Objects;

final class ObjectSafeProperty implements Property {

    private final @NonNull String name;
    private final @Nullable Object object;

    ObjectSafeProperty(final @NonNull String name, final @Nullable Object object) {
        this.name = name;
        this.object = object;
    }

    @Override
    public @NonNull String name() {
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
