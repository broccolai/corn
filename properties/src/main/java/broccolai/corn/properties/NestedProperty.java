package broccolai.corn.properties;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

record NestedProperty(@NonNull String name, @Nullable PropertyHolder propertyHolder) implements Property {

}
