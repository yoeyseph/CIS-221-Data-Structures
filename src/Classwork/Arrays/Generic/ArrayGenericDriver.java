package Classwork.Arrays.Generic;

public class ArrayGenericDriver {
    public static void main(String[] args) {
        ArrayDeletionGeneric<String> stringArray = new ArrayDeletionGeneric<String>(10);
        ArrayDeletionGeneric<Integer> intArray = new ArrayDeletionGeneric<Integer>(10);

        stringArray.insertAtEnd("A");
        stringArray.insertAtEnd("B");
        stringArray.insertAtEnd("C");
        stringArray.insertAtEnd("D");
        stringArray.insertAtEnd("E");
        stringArray.printArray();

        stringArray.deleteFront();
        stringArray.printArray();

        stringArray.deleteEnd();
        stringArray.printArray();

        stringArray.deleteAtIndex(1);
        stringArray.printArray();


        intArray.insertAtEnd(10);
        intArray.insertAtEnd(20);
        intArray.insertAtEnd(30);
        intArray.insertAtEnd(40);
        intArray.insertAtEnd(50);
        intArray.deleteFront();
        intArray.printArray();

        intArray.deleteEnd();
        intArray.printArray();

        intArray.deleteAtIndex(1);
        intArray.printArray();
        }
    }
