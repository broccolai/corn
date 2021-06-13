package broccolai.corn.properties;

import org.checkerframework.checker.nullness.qual.NonNull;

public interface PropertyHolder {

    /**
     * Create a snapshot of the holders current properties
     *
     * @return A snapshot of the current properties
     */
    @NonNull PropertySnapshot properties();

}
