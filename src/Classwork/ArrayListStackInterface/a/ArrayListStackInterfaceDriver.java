package Classwork.ArrayListStackInterface.a;

public class ArrayListStackInterfaceDriver {
    public static void main(String[]args ) {
        ArrayListStackInterface stack = new ArrayListStackInterface();

        stack.push(40);
        stack.push(50);
        stack.push(60);

        stack.traverse();

        stack.pop();

        System.out.println(stack.peek());

        System.out.println(stack.isEmpty());

        stack.traverse();

    } // main

}// class
