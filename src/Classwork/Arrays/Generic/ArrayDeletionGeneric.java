package Classwork.Arrays.Generic;

public class ArrayDeletionGeneric<T> {
    private Object[] array; // Internal storage
    private int size; // Number of elements
    private int capacity; // Max size

    // Constructor
    public ArrayDeletionGeneric(int capacity) {
        this.capacity = capacity;
        array = new Object[capacity];
        size = 0;
    }

    // Insert at the end (needed to populate array for deletion)
    public void insertAtEnd(T value) {
        if (size == capacity) {
            System.out.println("Array is full. Cannot insert at end.");
            return;
        }
        array[size++] = value;
    }

    // Delete element at the front (index 0)
    public void deleteFront() {
        if (size == 0) {
            System.out.println("Array is empty. Cannot delete front.");
            return;
        }
        for (int i = 0; i < size - 1; i++) {
            array[i] = array[i + 1]; // shift left
        }
        size--;
    }

    // Delete element at the end
    public void deleteEnd() {
        if (size == 0) {
            System.out.println("Array is empty. Cannot delete end.");
            return;
        }
        size--; // just reduce logical size
    }

    // Delete element at a specific index
    public void deleteAtIndex(int index) {
        if (index < 0 || index >= size) {
            System.out.println("Invalid index. Cannot delete.");
            return;
        }
        for (int i = index; i < size - 1; i++) {
            array[i] = array[i + 1]; // shift left
        }
        size--;
    }
    // Print array contents
    public void printArray() {
        for (int i = 0; i < size; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println();
    }
}
