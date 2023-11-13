package love.broccolai.corn.context;

import io.leangen.geantyref.TypeToken;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface ContextKey<T> {

    /**
     * Get the namespace name.
     *
     * @return Namespace of key
     */
    String namespace();

    /**
     * Get the name of the key.
     *
     * @return Name of key
     */
    String name();

    /**
     * Get the type via a TypeToken of the expected value.
     *
     * @return TypeToken of the expected type of value
     */
    TypeToken<T> token();

    /**
     * Create a ContextKey through TypeToken.
     *
     * @param namespace Namespace of key
     * @param name      Name of key
     * @param token     TypeToken of the keys expected value
     * @param <T>       Type associated with the key
     * @return Built ContextKey with T generic
     */
    static <T> ContextKey<T> of(final String namespace, final String name, final TypeToken<T> token) {
        return new ContextKeyImpl<>(namespace, name, token);
    }

    /**
     * Create a ContextKey through Class.
     *
     * @param namespace Namespace of key
     * @param name      Name of key
     * @param clazz     Clazz of the keys expected value
     * @param <T>       Type associated with the key
     * @return Built ContextKey with T generic
     */
    static <T> ContextKey<T> of(final String namespace, final String name, final Class<T> clazz) {
        return new ContextKeyImpl<>(namespace, name, TypeToken.get(clazz));
    }

}
