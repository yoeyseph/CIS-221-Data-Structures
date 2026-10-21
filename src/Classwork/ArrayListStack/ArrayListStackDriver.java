package Classwork.ArrayListStack;

public class ArrayListStackDriver {
    public static void main(String[] args) {
        ArrayListStack stack = new ArrayListStack();
        
        stack.push(40);
        stack.push(50);
        stack.push(60);

        stack.traverse();

        System.out.println("Top is: " + stack.peek());
        System.out.println("Popped: " + stack.pop());
        System.out.println("Now top: " + stack.peek());
        System.out.println("Size: " + stack.size());
    }
}
