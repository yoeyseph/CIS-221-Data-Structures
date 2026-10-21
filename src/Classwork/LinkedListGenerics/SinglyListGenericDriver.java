package Classwork.LinkedListGenerics;

public class SinglyListGenericDriver {
    public static void main(String[] args) {
        SinglyLLGeneric<Integer> list1 = new SinglyLLGeneric<>();

        list1.insertAtEnd(10);
        list1.insertAtEnd(20);
        list1.insertAtEnd(30);
        list1.insertAtEnd(40);

        list1.traverse();

        list1.insertAtBeginning(5);

        list1.traverse();

        list1.insertBeforeIndex(3, 25);
        list1.traverse();

        list1.insertAfterTarget(30, 35);
        list1.traverse();

        SinglyLLGeneric<String> list2 = new SinglyLLGeneric<>();

        list2.insertAtEnd("Collin");
        list2.insertAtEnd("Seth");
        list2.insertAtEnd("Joseph");

        list2.traverse();

        list2.insertAtBeginning("Trevor");
        list2.traverse();

        list2.insertBeforeIndex(2, "Terrance");
        list2.traverse();

        list2.insertAfterTarget("Joseph", "Valentino");
        list2.traverse();


        SinglyLLGeneric<Double> list3 = new SinglyLLGeneric<>();

        list3.insertAtEnd(1.0);
        list3.insertAtEnd(2.0);
        list3.insertAtEnd(3.4);
        list3.insertAtEnd(1.0004);
        list3.insertAtEnd(1000.00);

        list3.insertBeforeIndex(4, 8999.0);


        list3.traverse();
    }
}