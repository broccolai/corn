package broccolai.corn.context;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public final class DelegatingContextKeyRegistry implements ContextKeyRegistry {

    private final Set<ContextKey<?>> keys = new HashSet<>();

    protected void register(final @NonNull ContextKey<?> key) {
        this.keys.add(key);
    }

    @Override
    public Iterator<ContextKey<?>> iterator() {
        return this.keys.iterator();
    }

}
