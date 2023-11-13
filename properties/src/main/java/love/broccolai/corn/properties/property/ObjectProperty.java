package love.broccolai.corn.properties.property;

import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

@NullMarked
record ObjectProperty(String name, @Nullable Object object) implements Property {

}
