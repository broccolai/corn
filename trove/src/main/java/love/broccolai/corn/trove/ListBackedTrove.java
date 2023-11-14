package love.broccolai.corn.trove;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BinaryOperator;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collector;
import org.jspecify.annotations.NullMarked;

@NullMarked
final class ListBackedTrove<T> implements Trove<T> {

    private final List<T> source;

    ListBackedTrove(final List<T> source) {
        this.source = source;
    }

    @Override
    public <R> Trove<R> map(final Function<T, R> mapper) {
        List<R> result = new ArrayList<>();

        for (T entry : this.source) {
            result.add(mapper.apply(entry));
        }

        return new ListBackedTrove<>(result);
    }

    @Override
    public <R> Trove<R> flatMap(final Function<T, Collection<R>> mapper) {
        List<R> result = new ArrayList<>();

        for (T entry : this.source) {
            result.addAll(mapper.apply(entry));
        }

        return new ListBackedTrove<>(result);
    }

    @Override
    public T reduce(final T identity, final BinaryOperator<T> accumulator) {
        T result = identity;

        for (T entry : this.source) {
            result = accumulator.apply(result, entry);
        }

        return result;
    }

    @Override
    public <R> Map<R, Collection<T>> group(final Function<T, R> grouper) {
        Map<R, Collection<T>> output = new HashMap<>();

        for (T entry : this.source) {
            R type = grouper.apply(entry);
            output.putIfAbsent(type, new ArrayList<>());

            Collection<T> current = output.get(type);
            current.add(entry);

            output.put(type, current);
        }

        return output;
    }

    @Override
    public Trove<T> filter(final Predicate<T> predicate) {
        List<T> result = new ArrayList<>();

        for (T entry : this.source) {
            if (!predicate.test(entry)) {
                continue;
            }

            result.add(entry);
        }

        return new ListBackedTrove<>(result);
    }

    @Override
    public <I> Trove<I> filterIsInstance(final Class<I> type) {
        List<I> result = new ArrayList<>();

        for (T entry : this.source) {
            if (!type.isInstance(entry)) {
                continue;
            }

            result.add(type.cast(entry));
        }

        return new ListBackedTrove<>(result);
    }

    @Override
    public Optional<T> first(final Predicate<T> predicate) {
        for (T entry : this.source) {
            if (!predicate.test(entry)) {
                continue;
            }

            return Optional.of(entry);
        }

        return Optional.empty();
    }

    @Override
    public Optional<T> last(final Predicate<T> predicate) {
        for (int index = this.source.size() - 1; index >= 0; index--) {
            T entry = this.source.get(index);

            if (!predicate.test(entry)) {
                continue;
            }

            return Optional.of(entry);
        }

        return Optional.empty();
    }

    @Override
    public void forEach(final Consumer<T> consumer) {
        for (T entry : this.source) {
            consumer.accept(entry);
        }
    }

    @Override
    public <R, A> R collect(final Collector<? super T, A, R> collector) {
        A container = collector.supplier().get();

        for (T entry : this.source) {
            collector.accumulator().accept(container, entry);
        }

        return collector.finisher().apply(container);
    }
}
