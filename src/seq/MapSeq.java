package seq;

import java.util.Iterator;

public class MapSeq<T, TResult> extends Seq<TResult> {

    private Seq<T> _innerSeq;
    private Converter<T, TResult> _converter;

    public MapSeq(Seq<T> seq,
                  Converter<T, TResult> converter) {
        this._innerSeq = seq;
        this._converter = converter;
    }

    @Override
    public Iterator<TResult> iterator() {
        return new Iterator<TResult>() {

            private Iterator<T> _it = MapSeq.this._innerSeq.iterator();

            @Override
            public boolean hasNext() {
                return this._it.hasNext();
            }

            @Override
            public TResult next() {
                return MapSeq.this._converter.convert(this._it.next());
            }

            @Override
            public void remove() {
            }
        };
    }

}
