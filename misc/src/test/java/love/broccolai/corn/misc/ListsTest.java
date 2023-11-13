package love.broccolai.corn.misc;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

final class ListsTest {

    @Test
    void testGroup() {
        TestPair p1 = new TestPair(1, 'a');
        TestPair p2 = new TestPair(3, 'b');
        TestPair p3 = new TestPair(1, 'c');

        List<TestPair> input = Arrays.asList(p1, p2, p3);
        var output = Lists.group(input, TestPair::key);

        assertThat(output.get(1)).containsExactly(p1, p3);
        assertThat(output.get(3)).containsExactly(p2);
    }

    @Test
    void testMap() {
        List<String> unparsed = Arrays.asList("1", "5", "22", "41");
        List<Integer> parsed = Lists.map(unparsed, Integer::parseInt);

        assertThat(parsed).containsExactly(1, 5, 22, 41);
    }

    record TestPair(int key, char value) {

    }

}
