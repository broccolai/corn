package broccolai.corn.properties;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public final class DirtyPropertiesMapTest {

    @Test
    void testHashmap() {
        DirtyPropertiesMap<Integer, PropertyHolder> dirtyMap = DirtyPropertiesMap.hashmap();

        SomePropertyHolder entryOne = new SomePropertyHolder(20, "word");
        SomeComplexPropertyHolder entryTwo = new SomeComplexPropertyHolder(
                20,
                new SomePropertyHolder(10, "test")
        );

        dirtyMap.put(1, entryOne);
        dirtyMap.put(5, entryTwo);

        assertThat(dirtyMap.dirty()).isEmpty();

        entryOne.number(25);
        assertThat(dirtyMap.dirty()).containsExactly(entryOne);

        dirtyMap.clean();
        assertThat(dirtyMap.dirty()).isEmpty();

        entryTwo.number(20);
        assertThat(dirtyMap.dirty()).isEmpty();

        entryTwo.someHolder.number(14);
        assertThat(dirtyMap.dirty()).containsExactly(entryTwo);
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
