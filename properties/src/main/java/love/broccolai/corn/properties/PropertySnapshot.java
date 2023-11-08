package love.broccolai.corn.properties;

import org.jspecify.annotations.NullMarked;

import java.util.Arrays;

@NullMarked
public interface PropertySnapshot extends Iterable<Property> {

    /**
     * Create a property snapshot with a vararg of properties
     *
     * @param properties Property values to construct with
     * @return Constructed snapshot
     */
    static PropertySnapshot of(final Property... properties) {
        return new PropertySnapshotImpl(Arrays.asList(properties));
    }

}