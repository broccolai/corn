package broccolai.corn.examination;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public interface DirtyExaminableMap<K, V extends PropertyHolder> extends Map<K, V> {

    /**
     * Create a DirtyExaminableMap from an existing Map instance
     *
     * @param base Map instance to generate with
     * @param <K> the type of keys maintained by this map
     * @param <V> the type of mapped values
     * @return Map created with base
     */
    static <K, V extends PropertyHolder> DirtyExaminableMap<K, V> from(final @NonNull Map<K, V> base) {
        return new DirtyExaminableMapImpl<>(base);
    }

    /**
     * Create a DirtyExaminableMap with a HashMap
     *
     * @param <K> the type of keys maintained by this map
     * @param <V> the type of mapped values
     * @return Map created with a HashMap
     */
    static <K, V extends PropertyHolder> DirtyExaminableMap<K, V> hashmap() {
        return new DirtyExaminableMapImpl<>(new HashMap<>());
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
    @NonNull Collection<@NonNull V> dirty();

    /**
     * Check if a key currently has a dirty value
     *
     * @param key Key to lookup with
     * @return true if the key has a dirty value
     */
    boolean isDirty(@NonNull K key);

    /**
     * Set a keys value to be dirty
     *
     * @param key Key to set with
     */
    void setDirty(@NonNull K key);

}
