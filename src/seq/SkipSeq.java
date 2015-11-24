package seq;

import java.util.Iterator;

public class SkipSeq<T> extends Seq<T> {

    private Seq<T> _innterSeq;
    private int _skipCount;

    public SkipSeq(Seq<T> innterSeq,
                   int skipCount) {
        this._innterSeq = innterSeq;
        this._skipCount = skipCount;

    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {

            private Iterator<T> _it = SkipSeq.this._innterSeq.iterator();
            private boolean _isSkiped;

            @Override
            public boolean hasNext() {
                skip();
                return this._it.hasNext();
            }

            @Override
            public T next() {
                skip();
                return this._it.next();
            }

            @Override
            public void remove() {
            }

            private void skip() {
                if (!this._isSkiped) {
                    for (int i = 0; i < SkipSeq.this._skipCount; i++) {
                        this._it.next();
                    }
                    this._isSkiped = true;
                }
            }
        };
    }
}
