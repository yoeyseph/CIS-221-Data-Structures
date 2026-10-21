package Classwork.Arrays.ex1;

public class ArrayInsertion {
    private int[] array;
    private int size; //num of actual elements
    private int capacity; //max size = length

    //constructor
    public ArrayInsertion(int capacity) {
        this.capacity = capacity;
        array = new int[capacity];
        size = 0;
    }

    //Insert at the end (inserting at the front reverses the order)
    public void insertAtEnd(int value) {
        if(size ==capacity){
            System.out.println("array is full, cannot insert at end");
            return;
        }
        array[size] = value;
        size++;     // increases the size so the next insert will be at the next index
    }
    //Insert at the front    SHIFT
    public void insertAtFront(int value) {
        if (size == capacity) {
            System.out.println("array is full, cannot insert at end");
            return;
        }
        //Shift all elements to the right
        for (int i = size -1; i>= 0; i--) {
            array[i+1] = array[i]; // 1 becomes 2, 2 becomes 3 ect.
        }
        array[0] = value;
        size++;
    }

    //Inserting at an index
    public void insertAtIndex(int index, int value) {
        if (size == capacity) {
            System.out.println("array is full, cannot insert at end");
            return;
        }
        if(index < 0 || index > size) {
            System.out.println("Invalid Index");
            return;
        }
        //shift elements to the right from index
        for (int i = size -1; i >= index; i--) {
            array[i + 1] = array[i];
        }
        array[index] = value;
        size++;
    }
    //Print Array contents:
    public void printArray() {
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();

    }


    //DELETION
    //delete at an index:
    public void deleteAtIndex(int index) {
        if(index < 0 || index >= size) {
            System.out.println("Invalid Index");
        }
        for (int i = 0; i < size; size++) {
            array[i] = array[i+1]; //Shift left
        }
        size --;
    }
}


