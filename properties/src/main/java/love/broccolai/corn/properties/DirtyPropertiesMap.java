package love.broccolai.corn.properties;

import org.jspecify.annotations.NullMarked;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@NullMarked
public interface DirtyPropertiesMap<K, V extends PropertyHolder> extends Map<K, V> {

    /**
     * Create a DirtyPropertiesMap from an existing Map instance
     *
     * @param base Map instance to generate with
     * @param <K>  the type of keys maintained by this map
     * @param <V>  the type of mapped values
     * @return Map created with base
     */
    static <K, V extends PropertyHolder> DirtyPropertiesMap<K, V> from(final Map<K, V> base) {
        return new DirtyPropertiesMapImpl<>(base);
    }

    /**
     * Create a DirtyPropertiesMap with a HashMap
     *
     * @param <K> the type of keys maintained by this map
     * @param <V> the type of mapped values
     * @return Map created with a HashMap
     */
    static <K, V extends PropertyHolder> DirtyPropertiesMap<K, V> hashmap() {
        return new DirtyPropertiesMapImpl<>(new HashMap<>());
    }

    /**
     * Reset all dirty trackers to the current map state
     */
    void clean();

    /**
     * Retrieve all values from the map that are currently dirty
     *
     * @return Collection containing dirty values
     */
    Collection<V> dirty();

    /**
     * Check if a key currently has a dirty value
     *
     * @param key Key to lookup with
     * @return true if the key has a dirty value
     */
    boolean isDirty(K key);

    /**
     * Set a keys value to be dirty
     *
     * @param key Key to set with
     */
    void setDirty(K key);

}
