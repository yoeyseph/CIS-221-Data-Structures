package Classwork.Arrays.ex1;

public class ArrayInsertionDriver {
    public static void main(String[] args) {
        ArrayInsertion array1 = new ArrayInsertion(10);

        //insert at end
        array1.insertAtEnd(50);
        array1.insertAtEnd(30);
        array1.insertAtEnd(60);

        //print:
        array1.printArray();
        //insert at front:
        array1.insertAtFront(25);
        //print:
        array1.printArray();

        //insert at index:
        array1.insertAtIndex(2, 45);

        //print
        array1.printArray();
        array1.insertAtEnd(85);
        array1.printArray();

    }
}
