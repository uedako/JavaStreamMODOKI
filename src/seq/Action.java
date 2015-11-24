package seq;

public interface Action<T> {
    void invoke(T value);
}
