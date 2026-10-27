package Classwork.SortedArray;

@SuppressWarnings("unchecked")

public class SortedArrayC<T extends Comparable<T>> implements CollectionInterface<T> {
    private Object[] array;
    private int capacity;
    private int size = 0;

    private boolean found;
    private int location;


    public SortedArrayC(int capacity) {
        this.capacity = capacity;
        this.array = new Object[capacity];
    }
    private void find(T target) {
        int low = 0, high = size - 1;
        found = false;
        while (low <= high) {
            int mid = (low + high) / 2;
            int cmp = target.compareTo((T) array[mid]);
            if (cmp == 0) {
                found = true;
                location = mid;
                return;
            } else if  (cmp < 0 ) {
                high = mid - 1; }  // when target < mid -> left side
            else {
                low = mid + 1; } // when target > mid -> right side


            location = low; //insertion point
        }
    }

    @Override
    public boolean add(T element) {
        if(isFull()) return false;

        find(element);
        if (found)  return false;

        for (int i = size; i > location; i--) {
            array[i] = array[i -1]; //shift right from end to make room for new element
        }
        array[location] = element; //inserting new element
        size++;
        return true; // successful
    }

    @Override
    public boolean remove(T target) {
        if(isEmpty()) return false;
        find(target);
        if(!found) return false; // not found

        for(int i = location; i < size - 1; i++) {
            array[i] = array[i + 1]; //shifts left to fill in place
        }
        array[--size] = null;
        return true;
    }

    @Override
    public boolean contains(T target) {
        find(target);
        return found;
    }

    @Override
    public T get(T target) {
        find(target);
        return found ? (T) array[location] : null; // ? = if      : = else
        /*
                     ^
        # in one line you can do:
        if (found) {
            return (T) array[location]
            } else { return null; }
         */
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
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
