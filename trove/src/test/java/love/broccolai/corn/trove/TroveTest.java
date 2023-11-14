package love.broccolai.corn.trove;

import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

import static com.google.common.truth.Truth.assertThat;

final class TroveTest {
    
    @Test
    void testMap() {
        List<Integer> result = Trove.of(Arrays.asList("1", "5", "22", "41"))
            .map(Integer::parseInt)
            .toList();

        assertThat(result).containsExactly(1, 5, 22, 41);
    }

}
