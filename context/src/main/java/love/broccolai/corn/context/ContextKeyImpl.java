package love.broccolai.corn.context;

import io.leangen.geantyref.TypeToken;
import org.jspecify.annotations.NullMarked;

@NullMarked
record ContextKeyImpl<T>(String namespace, String name, TypeToken<T> token) implements ContextKey<T> {

}
