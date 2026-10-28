package Classwork.CollectionADT.VocabDensity;


public class ArrayCollection<T extends Comparable<T>> implements CollectionInterface<T> {
    private Object[] array;
    private int size;
    private int capacity;

    //constructor
    public ArrayCollection(int capacity) {
        this.capacity = capacity;
        array = new Object[capacity];
        size = 0;
    }

    @Override
    public boolean add(T element) {
        if (element == null || isFull()) {
            return false;
        }
        array[size] = element;
        size++;
        return true;
    }

    @Override
    public boolean remove(T target) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(target)) {
                array[i] = array[size - 1]; // not shifting, don't care about order
                array[size - 1] = null;
                size--;
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean contains(T target) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(target)) {
                return true;
            }
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public T get(T target) {
        for (int i = 0; i < size; i++) {
            if (array[i].equals(target)) { // .equals compares values, not references
                return (T) array[i];
            }
        }
        return null;
    }

    @Override
    public boolean isFull() {
        return size == capacity;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void display() {
        if (isEmpty()) {
            System.out.println("Collection is empty.");
            return;
        }
        for (int i = 0; i < size; i++) {
            System.out.println(array[i]);
        }
    }
}