package Classwork.ArrayListStack;
import java.util.ArrayList;
@SuppressWarnings("FieldMayBeFinal")
public class ArrayListStack {
    private ArrayList<Integer> stack;

    public ArrayListStack() {
        stack = new ArrayList<>();
    }
    //push
    public void push(int value) {
        stack.add(value);
    }
    //POP
    public int pop() {
        if (stack.isEmpty()) {
            throw new RuntimeException("Stack underflow");
        }
        return stack.removeLast();
    }
    //peek
    public int peek() {
        if (stack.isEmpty()) {
            throw new RuntimeException("Stack is empty");

        }
        return stack.getLast();
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }
    public int size() {
        return stack.size();
    }

    public void traverse() {
        if (isEmpty()) {
            System.out.println("Stack is Empty");
            return;
        }

        System.out.print("Stack elements (top to Bottom): ");
        for (int i = stack.size()-1; i> 0; i --) {
            System.out.print(stack.get(i) + " ");
        }
        System.out.println();
    }
}


