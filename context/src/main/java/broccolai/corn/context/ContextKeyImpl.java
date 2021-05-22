package broccolai.corn.context;

import io.leangen.geantyref.TypeToken;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.util.Objects;

final class ContextKeyImpl<T> implements ContextKey<T> {

    private final String namespace;
    private final String name;
    private final TypeToken<T> token;

    ContextKeyImpl(final @NonNull String namespace, final @NonNull String name, final @NonNull TypeToken<T> token) {
        this.namespace = namespace;
        this.name = name;
        this.token = token;
    }

    @Override
    public @NonNull String namespace() {
        return this.namespace;
    }

    @Override
    public @NonNull String name() {
        return this.name;
    }

    @Override
    public @NonNull TypeToken<@NonNull T> token() {
        return this.token;
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
        if (!(other instanceof ContextKey<?>)) {
            return false;
        }

        ContextKey<?> that = (ContextKey<?>) other;
        return Objects.equals(this.namespace(), that.namespace()) && Objects.equals(this.name(), that.name());
    }

}
