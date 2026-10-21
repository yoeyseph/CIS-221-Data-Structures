package HW.HW5.q17ab;

public class BasicPair<T> implements PairInterface<T> {
    private T first;
    private T second;

    public BasicPair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public void setFirst(T first) {
        this.first = first;
    }

    public T getSecond() {
        return second;
    }

    public void setSecond(T second) {
        this.second = second;
    }
}
