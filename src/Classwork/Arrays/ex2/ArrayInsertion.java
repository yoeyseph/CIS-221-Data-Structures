package Classwork.Arrays.ex2;

public class ArrayInsertion {
    private int[] array;
    private int size;
    private int capacity;

    //Constructor
    public ArrayInsertion(int capacity) {
        this.capacity = capacity;
        array = new int[capacity];
        size = 0;
    }

    //insert helper (to build array for testing
    public void insertAtEnd(int value) {
        if (size == capacity) {
            System.out.println("array is full");
            return;
        }
        array[size] = value;
        size++;
    }

    //Delete from front (index 0):
    public void deleteFront() {
        if (size == 0) {
            System.out.println("Array is empty");
            return;
        }
        //shift left
        for (int i = 0; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
    }

    //delete from end:
    public void deleteEnd() {
        if (size == 0) {
            System.out.println("Array is empty");
            return;
        }
        size--; // just reduce size, no shifting required.
    }

    //delete from index
    public void deleteAtIndex(int index) {
        if (size == 0) {
            System.out.println("Array is empty");
            return;
        }
        if (index < 0 || index >= size) {
            System.out.println("Invalid Index");
            return;
        }
        //shift left from index
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1];
        }
        size--;
    }

    /*delete from index without shifting
    public void deleteAtIndexNoShift(int index) {
        if (size == 0) {
            System.out.println("Array is empty");
            return;
        }
        if (index < 0 || index >= size) {
            System.out.println("Invalid Index");
            return;
        }
        array[index] = -1; // sentinel value marking "empty" slot
    }
*/
    //print
    public void printArray() {
        if (size == 0) {
            System.out.println("Array is empty");
            return;
        }
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}