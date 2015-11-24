package seq;

import java.util.Iterator;

public class IterableSeq<T> extends Seq<T> {

    private Iterable<T> _iterable;

    public IterableSeq(Iterable<T> iterable) {
        this._iterable = iterable;
    }

    @Override
    public Iterator<T> iterator() {
        return this._iterable.iterator();
    }

}
