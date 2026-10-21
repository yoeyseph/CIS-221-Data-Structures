package Classwork.ArrayListStackInterface.a;
import java.lang.annotation.Documented;
import java.util.LinkedList;
public class LinkedListStack implements StackInterface {

    private LinkedList<Integer> stack;

    public LinkedListStack() {
        stack = new LinkedList<>();
    }

    @Override
    public void push(int value) {
        stack.addFirst(value);
    }

    @Override
    public int pop() {
        if(isEmpty()) {
            throw new RuntimeException("Stack underflow");
        }
        return stack.removeFirst();
    }

    @Override
    public int peek() {
        if (isEmpty()) {
            throw new RuntimeException("Stack underflow");
        }
        return stack.getFirst();
    }

    @Override
    public int size(){
        return stack.size();
    }

    @Override
    public boolean isEmpty() {
        return stack.isEmpty();
    }

    @Override
    public void traverse() {
        if(isEmpty()) {
            System.out.println("Stack is empty");
            return;
        }
        System.out.print("Stack elements (top to bottom: ");
        for (int i = stack.size() -1; i >=0 ; i--) {
            System.out.print(stack.get(i) +1);

        }
        System.out.println();
    }
}
