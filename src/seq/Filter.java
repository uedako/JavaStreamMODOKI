package seq;

public interface Filter<T> {
    boolean doFilter(T value);
}
