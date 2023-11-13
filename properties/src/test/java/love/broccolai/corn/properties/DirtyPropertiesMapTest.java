package love.broccolai.corn.properties;

import love.broccolai.corn.properties.property.Property;
import love.broccolai.corn.properties.snapshot.PropertySnapshot;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static com.google.common.truth.Truth.assertThat;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public final class DirtyPropertiesMapTest {

    private final DirtyPropertiesMap<Integer, PropertyHolder> dirtyMap = DirtyPropertiesMap.hashmap();

    private final SomePropertyHolder entryOne = new SomePropertyHolder(20, "word");
    private final SomeComplexPropertyHolder entryTwo = new SomeComplexPropertyHolder(
            20,
            new SomePropertyHolder(10, "test")
    );

    @BeforeAll
    void create() {
        this.dirtyMap.put(1, entryOne);
        this.dirtyMap.put(5, entryTwo);
    }

    @BeforeEach
    void cleanup() {
        this.dirtyMap.clean();
    }

    @Test
    void isEmpty() {
        assertThat(dirtyMap.dirty()).isEmpty();
    }

    @Test
    void simpleChange() {
        entryOne.number(25);
        assertThat(dirtyMap.dirty()).containsExactly(entryOne);
    }

    @Test
    void simpleChangeAndClean() {
        entryOne.number(25);
        dirtyMap.clean();
        assertThat(dirtyMap.dirty()).isEmpty();
    }

    @Test
    void changeBackToNormal() {
        entryTwo.number(25);
        dirtyMap.clean();

        entryTwo.number(20);
        entryTwo.number(25);
        assertThat(dirtyMap.dirty()).isEmpty();
    }

    @Test
    void changeNestedField() {
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
        public PropertySnapshot properties() {
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
        public PropertySnapshot properties() {
            return PropertySnapshot.of(
                    Property.of("number", this.number),
                    Property.of("someHolder", this.someHolder)
            );
        }

    }


}
