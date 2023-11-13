package love.broccolai.corn.context;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.jspecify.annotations.NullMarked;

@NullMarked
public class DelegatingContextKeyRegistry implements ContextKeyRegistry {

    private final Set<ContextKey<?>> keys = new HashSet<>();

    /**
     * Register an array of context keys to the registry.
     *
     * @param keys keys to register
     */
    public final void register(final ContextKey<?>... keys) {
        Collections.addAll(this.keys, keys);
    }

    @Override
    public final Iterator<ContextKey<?>> iterator() {
        return this.keys.iterator();
    }

}
