package love.broccolai.corn.trove;

import java.util.function.Predicate;

final class Predicates {
    private Predicates() {
    }

    static <T> Predicate<T> alwaysTrue() {
        return $ -> true;
    }

}
