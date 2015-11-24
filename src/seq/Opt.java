package seq;

public class Opt<T> {

    public static <T> Opt<T> createNothing() {
        return new Opt<T>(null);
    }

    private T _value;

    public Opt(T value) {
        this._value = value;
    }

    public void ifPresent(Action<T> action) {
        if (this._value == null) {
            return;
        }
        action.invoke(this._value);
    }

    public <TResult> Opt<TResult> map(Converter<T, TResult> converter) {
        if (this._value == null) {
            return Opt.<TResult> createNothing();
        }
        return new Opt<TResult>(converter.convert(this._value));
    }

    public Opt<T> filter(Filter<T> filter) {
        if (this._value == null) {
            return this;
        }
        return filter.doFilter(this._value) ? this : Opt.<T> createNothing();

    }

    public T orElse(Else<T> orElse) {
        return this._value == null ? orElse.invoke() : this._value;
    }

}
