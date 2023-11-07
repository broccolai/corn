package love.broccolai.corn.context;

import io.leangen.geantyref.TypeToken;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Objects;

record ContextKeyImpl<T>(String namespace, String name, TypeToken<T> token) implements ContextKey<T> {

    ContextKeyImpl(final @NonNull String namespace, final @NonNull String name, final @NonNull TypeToken<T> token) {
        this.namespace = namespace;
        this.name = name;
        this.token = token;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.namespace, this.name);
    }

    @Override
    public boolean equals(final @Nullable Object other) {
        if (this == other) {
            return true;
        }
        if (!(other instanceof ContextKey<?> that)) {
            return false;
        }

        return Objects.equals(this.namespace(), that.namespace()) && Objects.equals(this.name(), that.name());
    }

}
