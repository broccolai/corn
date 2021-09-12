package broccolai.corn.core;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;


import org.junit.jupiter.api.Test;

class NumbersTest {

    @Test
    void testValueOrNullWithNumericalString() {
        Long longValue = Numbers.valueOrNull("5", Long::valueOf);
        assertTrue(longValue != null && longValue == 5);
    }

    @Test
    void testValueOrNullWithNonNumericalString() {
        Integer intValue = Numbers.valueOrNull("something", Integer::valueOf);
        assertNull(intValue);
    }

}
