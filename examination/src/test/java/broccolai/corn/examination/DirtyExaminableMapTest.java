package broccolai.corn.examination;

import org.checkerframework.checker.nullness.qual.NonNull;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

public final class DirtyExaminableMapTest {

    @Test
    void testEmptyDirty() {
        DirtyExaminableMap<Integer, SomeExaminable> dirtyMap = DirtyExaminableMap.hashmap();
        dirtyMap.put(1, new SomeExaminable(20, "word"));
        dirtyMap.put(5, new SomeExaminable(15, "wording"));

        assertThat(dirtyMap.dirty()).isEmpty();
    }

    @Test
    void testChangedDirty() {
        DirtyExaminableMap<Integer, SomeExaminable> dirtyMap = DirtyExaminableMap.hashmap();
        SomeExaminable toChange = new SomeExaminable(20, "word");
        dirtyMap.put(1, toChange);
        dirtyMap.put(5, new SomeExaminable(15, "wording"));

        toChange.number(25);
        assertThat(dirtyMap.dirty()).containsExactly(toChange);
    }


    static final class SomeExaminable implements PropertyHolder {

        private int number;
        private String word;

        SomeExaminable(final int number, final String word) {
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

}
