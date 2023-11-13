package love.broccolai.corn.properties;

import love.broccolai.corn.properties.snapshot.PropertySnapshot;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface PropertyHolder {

    /**
     * Create a snapshot of the holders current properties.
     *
     * @return A snapshot of the current properties
     */
    PropertySnapshot properties();

}
