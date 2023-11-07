package love.broccolai.corn.properties;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Objects;

final class ObjectProperty implements Property {

    private final @NonNull String name;
    private final @Nullable Integer objectHash;

    ObjectProperty(final @NonNull String name, final @Nullable Object object) {
        this.name = name;
        this.objectHash = object != null ? object.hashCode() : null;
    }

    @Override
    public @NonNull String name() {
        return this.name;
    }

    @Override
    public boolean equals(final @Nullable Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ObjectProperty objectProperty)) {
            return false;
        }

        return Objects.equals(this.name(), objectProperty.name()) && Objects.equals(this.objectHash, objectProperty.objectHash);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, objectHash);
    }

}
