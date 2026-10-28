package Classwork.CollectionADT.VocabDensity;

public interface CollectionInterface<T extends Comparable<T>> {
    boolean add(T element);
    boolean remove(T target);
    boolean contains(T target);
    T get(T target);
    boolean isFull();
    boolean isEmpty();
    int size();
    void display();

}
