package broccolai.corn.properties;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public final class DirtyPropertiesMapTest {

    @Test
    void testEmptyDirty() {
        DirtyPropertiesMap<Integer, PropertyHolder> dirtyMap = DirtyPropertiesMap.hashmap();
        dirtyMap.put(1, new SomePropertyHolder(20, "word"));
        dirtyMap.put(5, new SomePropertyHolder(15, "wording"));

        assertThat(dirtyMap.dirty()).isEmpty();
    }

    @Test
    void testChangedDirty() {
        DirtyPropertiesMap<Integer, PropertyHolder> dirtyMap = DirtyPropertiesMap.hashmap();
        SomePropertyHolder toChange = new SomePropertyHolder(20, "word");
        dirtyMap.put(1, toChange);
        dirtyMap.put(5, new SomePropertyHolder(15, "wording"));

        toChange.number(25);
        assertThat(dirtyMap.dirty()).containsExactly(toChange);
    }

    @Test
    void testChangedAndResetDirty() {
        DirtyPropertiesMap<Integer, PropertyHolder> dirtyMap = DirtyPropertiesMap.hashmap();
        SomePropertyHolder toChange = new SomePropertyHolder(20, "word");
        dirtyMap.put(1, toChange);
        dirtyMap.put(5, new SomePropertyHolder(15, "wording"));

        toChange.number(20);
        assertThat(dirtyMap.dirty()).isEmpty();
    }

    @Test
    void testComplexEmptyDirty() {
        DirtyPropertiesMap<Integer, PropertyHolder> dirtyMap = DirtyPropertiesMap.hashmap();
        dirtyMap.put(1, new SomeComplexPropertyHolder(
                20,
                new SomePropertyHolder(10, "test")
        ));
        dirtyMap.put(5, new SomePropertyHolder(15, "wording"));

        assertThat(dirtyMap.dirty()).isEmpty();
    }

    @Test
    void testComplexChangedDirty() {
        DirtyPropertiesMap<Integer, PropertyHolder> dirtyMap = DirtyPropertiesMap.hashmap();
        SomeComplexPropertyHolder toChange = new SomeComplexPropertyHolder(
                20,
                new SomePropertyHolder(10, "test")
        );
        dirtyMap.put(1, toChange);
        dirtyMap.put(5, new SomePropertyHolder(15, "wording"));

        toChange.number(25);
        assertThat(dirtyMap.dirty()).containsExactly(toChange);
    }

    @Test
    void testComplexChangedAndResetDirty() {
        DirtyPropertiesMap<Integer, PropertyHolder> dirtyMap = DirtyPropertiesMap.hashmap();
        SomeComplexPropertyHolder toChange = new SomeComplexPropertyHolder(
                20,
                new SomePropertyHolder(10, "test")
        );
        dirtyMap.put(1, toChange);
        dirtyMap.put(5, new SomePropertyHolder(15, "wording"));

        toChange.number(20);
        assertThat(dirtyMap.dirty()).isEmpty();
    }


    static final class SomePropertyHolder implements PropertyHolder {

        private int number;
        private String word;

        SomePropertyHolder(final int number, final String word) {
            this.number = number;
            this.word = word;
        }

        public void number(final int number) {
            this.number = number;
        }

        public void word(final String word) {
            this.word = word;
        }

        @Override
        public @NonNull PropertySnapshot properties() {
            return PropertySnapshot.of(
                    Property.of("number", this.number),
                    Property.of("word", this.word)
            );
        }

    }

    static final class SomeComplexPropertyHolder implements PropertyHolder {

        private int number;
        private SomePropertyHolder someHolder;

        SomeComplexPropertyHolder(final int number, final SomePropertyHolder someHolder) {
            this.number = number;
            this.someHolder = someHolder;
        }

        public void number(final int number) {
            this.number = number;
        }

        public void someHolder(final SomePropertyHolder someHolder) {
            this.someHolder = someHolder;
        }

        @Override
        public @NonNull PropertySnapshot properties() {
            return PropertySnapshot.of(
                    Property.of("number", this.number),
                    Property.of("someHolder", this.someHolder)
            );
        }

    }


}
