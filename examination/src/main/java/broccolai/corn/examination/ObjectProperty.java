package broccolai.corn.examination;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

record ObjectProperty(@NonNull String name, @Nullable Object object) implements Property {

}
