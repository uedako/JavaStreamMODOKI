package seq;

import java.util.Iterator;

public class PeekSeq<T> extends Seq<T> {

    private Seq<T> _innerSeq;

    private Action<T> _action;

    public PeekSeq(Seq<T> seq,
                   Action<T> action) {
        this._innerSeq = seq;
        this._action = action;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private Iterator<T> _it = PeekSeq.this._innerSeq.iterator();

            @Override
            public boolean hasNext() {
                return this._it.hasNext();
            }

            @Override
            public T next() {
                T value = this._it.next();
                PeekSeq.this._action.invoke(value);
                return value;
            }

            @Override
            public void remove() {
            }
        };
    }

}
