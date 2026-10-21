package Classwork.Arrays.ex2;

public class ArrayInsertionDriver {
    public static void main(String[] args) {

        ArrayInsertion ar = new ArrayInsertion(10);

        ar.insertAtEnd(10);
        ar.insertAtEnd(20);
        ar.insertAtEnd(30);
        ar.insertAtEnd(40);
        ar.insertAtEnd(50);

        ar.printArray();

        ar.deleteFront();

        ar.printArray();

        ar.deleteEnd();

        ar.printArray();

        ar.deleteAtIndex(1);

        ar.printArray();
    }
}
