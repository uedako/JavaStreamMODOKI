package seq;

public interface Converter<T, TResult> {
    TResult convert(T value);
}
