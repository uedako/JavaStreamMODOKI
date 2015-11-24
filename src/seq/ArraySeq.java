package seq;

import java.util.Iterator;

public class ArraySeq<T> extends Seq<T> {

    private T[] _array;

    public ArraySeq(T[] array) {
        this._array = array;

    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int current = 0;

            @Override
            public boolean hasNext() {
                return this.current < ArraySeq.this._array.length;
            }

            @Override
            public T next() {
                return ArraySeq.this._array[this.current++];
            }

            @Override
            public void remove() {
            }
        };
    }

}
