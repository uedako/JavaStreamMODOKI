package seq;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class Seq<T> implements Iterable<T> {

    public static <T> Seq<T> of(Iterable<T> iterable) {
        return new IterableSeq<T>(iterable);
    }

    public static <T> Seq<T> of(T[] array) {
        return new ArraySeq<T>(array);
    }

    public static Seq<Integer> range(int initial,
                                     int size) {
        return new IntRangeSeq(initial, size);
    }

    public Seq<T> filter(Filter<T> filter) {
        return new FilterSeq<T>(this, filter);
    }

    public <TResult> Seq<TResult> map(Converter<T, TResult> converter) {
        return new MapSeq<T, TResult>(this, converter);
    }

    public Opt<T> first() {
        Iterator<T> iterator = this.iterator();
        if (iterator.hasNext()) {
            return new Opt<T>(iterator.next());
        } else {
            return Opt.<T> createNothing();
        }
    }

    public Seq<T> peek(Action<T> action) {
        return new PeekSeq<T>(this, action);
    }

    public Seq<T> skip(int skipCount) {
        return new SkipSeq<T>(this, skipCount);
    }

    public List<T> toList() {
        List<T> result = new ArrayList<>();
        for (T t : this) {
            result.add(t);
        }
        return result;
    }

    public <TResult> TResult collect(CollectFunc<TResult> collectFunc) {
        return collectFunc.invoke(this);
    }

    public void foreach(Action<T> action) {
        for (T item : this) {
            action.invoke(item);
        }
    }

}
