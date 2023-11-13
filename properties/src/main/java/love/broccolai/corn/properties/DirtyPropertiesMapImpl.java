package love.broccolai.corn.properties;

import love.broccolai.corn.properties.snapshot.PropertySnapshot;
import org.jspecify.annotations.NullMarked;

import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@NullMarked
final class DirtyPropertiesMapImpl<K, V extends PropertyHolder> extends AbstractMap<K, V> implements DirtyPropertiesMap<K, V> {

    private final Map<K, V> base;
    private final Map<K, PropertySnapshot> previousProperties = new HashMap<>();
    private final Set<K> knownDirty = new HashSet<>();

    DirtyPropertiesMapImpl(final Map<K, V> base) {
        this.base = base;
        this.fillPreviousProperties();
    }

    @Override
    public void clean() {
        this.knownDirty.clear();
        this.fillPreviousProperties();
    }

    @Override
    public Collection<V> dirty() {
        Collection<V> dirtyValues = new ArrayList<>();

        for (final K key : this.base.keySet()) {
            if (this.isDirty(key)) {
                dirtyValues.add(this.base.get(key));
            }
        }

        return dirtyValues;
    }

    @Override
    public boolean isDirty(final K key) {
        if (this.knownDirty.contains(key)) {
            return true;
        }

        V propertyHolder = this.base.get(key);
        PropertySnapshot previousSnapshot = previousProperties.get(key);

        if (!previousSnapshot.equals(propertyHolder.properties())) {
            this.knownDirty.add(key);
            return true;
        }

        return false;
    }

    @Override
    public void setDirty(final K key) {
        this.knownDirty.add(key);
    }

    private void fillPreviousProperties() {
        this.base.forEach((key, value) -> this.previousProperties.put(key, value.properties()));
    }

    @Override
    public V put(final K key, final V value) {
        return this.base.put(key, value);
    }

    @Override
    public void clear() {
        super.clear();

        this.previousProperties.clear();
        this.knownDirty.clear();
    }

    @Override
    public Set<Entry<K, V>> entrySet() {
        return this.base.entrySet();
    }

}
