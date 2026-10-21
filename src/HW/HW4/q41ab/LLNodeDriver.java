package HW.HW4.q41ab;

public class LLNodeDriver {

    //sum
    public static void printSum(LLNode<Integer> numbers) {

            int sum = 0;
            LLNode<Integer> current = numbers;

            while (current != null) {
                sum += current.data();
                current = current.next();
            }

            System.out.println("Sum: " + sum);
        }

        //count
    public static void printCount(LLNode<Integer> numbers) {
        int count = 0;
        LLNode<Integer> current = numbers;

        while (current != null) {
            count++;
            current = current.next();
        }
        System.out.println("Count: " + count);
    }


    public static void main(String[] args) {
        //creating nodes
        LLNode<Integer> node1 = new LLNode<>(5);
        LLNode<Integer> node2 = new LLNode<>(15);
        LLNode<Integer> node3 = new LLNode<>(9);

       // setting links
        node1.setLink(node2);
        node2.setLink(node3);
        LLNode<Integer>  numbers = node1;
        //printing
        System.out.println("Non-empty list:");
        printSum(numbers);
        printCount(numbers);
        System.out.println();

        //empty list:
        LLNode<Integer> emptyList = null;

        System.out.println("Empty list:");
        printSum(emptyList);
        printCount(emptyList);
    }
}