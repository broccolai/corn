package love.broccolai.corn.context;

import io.leangen.geantyref.TypeToken;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.util.Objects;

@NullMarked
record ContextKeyImpl<T>(String namespace, String name, TypeToken<T> token) implements ContextKey<T> {

    ContextKeyImpl(final String namespace, final String name, final TypeToken<T> token) {
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
