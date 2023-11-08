package love.broccolai.corn.properties;

import org.jspecify.annotations.NullMarked;

@NullMarked
public interface PropertyHolder {

    /**
     * Create a snapshot of the holders current properties
     *
     * @return A snapshot of the current properties
     */
    PropertySnapshot properties();

}
