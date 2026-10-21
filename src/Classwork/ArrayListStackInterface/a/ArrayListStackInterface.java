package Classwork.ArrayListStackInterface.a;
import java.util.ArrayList;

interface StackInterface {

    void push(int value);

    int pop();

    int peek();

    boolean isEmpty();

    int size();

    void traverse();
}

public class ArrayListStackInterface implements StackInterface {

    private ArrayList<Integer> stack;

    public ArrayListStackInterface() {
        stack = new ArrayList<>();
    }

    @Override
    public void push(int value) {
        stack.add(value);
    }

    @Override
    public int pop() {
        if(isEmpty()) {
            throw new RuntimeException("Stack Underflow");
        }
        return stack.removeLast();
    }

    @Override
    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack is Empty");

        }
         return stack.getLast();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public int size() {
        return stack.size();
    }
    @Override
    public void traverse() {
        if(isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }
        System.out.println();
        System.out.print("Stack elements (top to bottom): ");
        for (int i = stack.size() -1; i >=0 ; i--) {
           System.out.print(stack.get(i) +" ");

        }
        System.out.println();
    }
}
