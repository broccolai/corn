package love.broccolai.corn.context;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class DelegatingContextKeyRegistry implements ContextKeyRegistry {

    private final Set<ContextKey<?>> keys = new HashSet<>();

    /**
     * Register an array of context keys to the registry
     *
     * @param keys keys to register
     */
    public final void register(final @NonNull ContextKey<?>... keys) {
        Collections.addAll(this.keys, keys);
    }

    @Override
    public final @NonNull Iterator<ContextKey<?>> iterator() {
        return this.keys.iterator();
    }

}
