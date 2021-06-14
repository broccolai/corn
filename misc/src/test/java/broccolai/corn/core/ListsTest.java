package broccolai.corn.core;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.google.common.truth.Truth.assertThat;

final class ListsTest {

    @Test
    void testGroup() {
        class Pair<K, V> {

            private final K key;
            private final V value;

            Pair(final K key, final V value) {
                this.key = key;
                this.value = value;
            }

            K getKey() {
                return key;
            }

            V getValue() {
                return value;
            }

        }

        Pair<Integer, String> p1 = new Pair<>(1, "a");
        Pair<Integer, String> p2 = new Pair<>(3, "b");
        Pair<Integer, String> p3 = new Pair<>(1, "c");

        List<Pair<Integer, String>> input = Arrays.asList(p1, p2, p3);
        Map<Integer, List<Pair<Integer, String>>> output = Lists.group(input, Pair::getKey);

        assertThat(output.get(1)).containsExactly(p1, p3);
        assertThat(output.get(3)).containsExactly(p2);
    }

    @Test
    void testMap() {
        List<String> unparsed = Arrays.asList("1", "5", "22", "41");
        List<Integer> parsed = Lists.map(unparsed, Integer::parseInt);

        assertThat(parsed).containsExactly(1, 5, 22, 41);
    }
}
