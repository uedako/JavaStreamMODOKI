package seq;

import java.util.Iterator;

public class IntRangeSeq extends Seq<Integer> {

    private final int _initial;
    private final int _size;

    public IntRangeSeq(int initial,
                       int size) {
        this._initial = initial;
        this._size = size;
    }

    @Override
    public Iterator<Integer> iterator() {
        return new Iterator<Integer>() {

            private int _current = IntRangeSeq.this._initial;
            private int _max = IntRangeSeq.this._initial + IntRangeSeq.this._size;

            @Override
            public boolean hasNext() {
                return this._current < this._max;
            }

            @Override
            public Integer next() {
                int result = this._current;
                this._current++;
                return result;
            }

            @Override
            public void remove() {
            }
        };
    }
}
