package broccolai.corn.properties;

import org.checkerframework.checker.nullness.qual.NonNull;

import java.util.Map;

public record MapProperty(
        @NonNull String name,
        @NonNull Map<String, PropertyHolder> map
) implements Property {

}
