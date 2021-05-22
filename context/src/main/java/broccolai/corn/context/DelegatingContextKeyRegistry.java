package broccolai.corn.context;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class DelegatingContextKeyRegistry implements ContextKeyRegistry {

    private final Set<ContextKey<?>> keys = new HashSet<>();

    protected final void register(final @NonNull ContextKey<?> key) {
        this.keys.add(key);
    }

    @Override
    public final @NonNull Iterator<ContextKey<?>> iterator() {
        return this.keys.iterator();
    }

}
