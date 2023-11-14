package love.broccolai.corn.trove;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import org.jspecify.annotations.NullMarked;

@NullMarked
public interface Trove<T> {

    static <T> Trove<T> of(Collection<T> collection) {
        return new ListBackedTrove<>(new ArrayList<>(collection));
    }

    <R> Trove<R> map(Function<T, R> mapper);

    <R> Trove<R> mapIfPresent(Function<T, Optional<R>> mapper);

    <R> Trove<R> flatMap(Function<T, Collection<R>> mapper);

    T reduce(T identity, BinaryOperator<T> accumulator);

    <R> Map<R, Collection<T>> group(Function<T, R> grouper);

    Trove<T> filter(Predicate<T> predicate);

    <I> Trove<I> filterIsInstance(Class<I> type);

    T average(T identity, BinaryOperator<T> accumulator, BiFunction<T, Integer, T> divider);

    default Optional<T> first() {
        return this.first(Predicates.alwaysTrue());
    }

    Optional<T> first(Predicate<T> predicate);

    default Optional<T> last() {
        return this.last(Predicates.alwaysTrue());
    }

    Optional<T> last(Predicate<T> predicate);

    void forEach(Consumer<T> consumer);

    default List<T> toList() {
        return this.collect(Collectors.toList());
    }

    <R, A> R collect(Collector<? super T, A, R> collector);

}
