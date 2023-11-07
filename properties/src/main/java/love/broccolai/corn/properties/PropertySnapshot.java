package love.broccolai.corn.properties;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Arrays;

public interface PropertySnapshot extends Iterable<Property> {

    /**
     * Create a property snapshot with a vararg of properties
     *
     * @param properties Property values to construct with
     * @return Constructed snapshot
     */
    static PropertySnapshot of(final @NonNull Property... properties) {
        return new PropertySnapshotImpl(Arrays.asList(properties));
    }

}
