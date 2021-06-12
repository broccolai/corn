package broccolai.corn.examination;

import net.kyori.examination.Examinable;
import net.kyori.examination.ExaminableProperty;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static com.google.common.truth.Truth.assertThat;

public final class DirtyExaminableMapTest {

    @Test
    void testEmptyDirty() {
        DirtyExaminableMap<Integer, SomeExaminable> dirtyMap = DirtyExaminableMap.hashmap();
        dirtyMap.put(1, new SomeExaminable(20, "word"));
        dirtyMap.put(5, new SomeExaminable(15, "wording"));

        assertThat(dirtyMap.dirty()).isEmpty();
    }

    static final class SomeExaminable implements Examinable {

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
        public @NonNull Stream<? extends ExaminableProperty> examinableProperties() {
            return Stream.of(
                    ExaminableProperty.of("number", this.number),
                    ExaminableProperty.of("word", this.word)
            );
        }

    }

}
