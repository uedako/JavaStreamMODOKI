package seq;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class FilterSeq<T> extends Seq<T> {

    private Seq<T> _innterSeq;

    private Filter<T> _filter;

    public FilterSeq(Seq<T> innterSeq,
                     Filter<T> filter) {
        this._innterSeq = innterSeq;
        this._filter = filter;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private Iterator<T> _it = FilterSeq.this._innterSeq.iterator();
            private T _current = null;

            @Override
            public boolean hasNext() {
                if (this._current != null && FilterSeq.this._filter.doFilter(this._current)) {
                    return true;
                }
                while (this._it.hasNext()) {
                    T value = this._it.next();
                    if (FilterSeq.this._filter.doFilter(value)) {
                        this._current = value;
                        return true;
                    }
                }
                this._current = null;
                return false;
            }

            @Override
            public T next() {
                T result = this._current;
                if (this._current != null) {
                    if (this._it.hasNext()) {
                        this._current = this._it.next();
                    } else {
                        this._current = null;
                    }
                    return result;
                }

                while (this._it.hasNext()) {
                    T value = this._it.next();
                    if (FilterSeq.this._filter.doFilter(value)) {
                        return value;
                    }
                }
                throw new NoSuchElementException();
            }

            @Override
            public void remove() {
            }
        };
    }

}
