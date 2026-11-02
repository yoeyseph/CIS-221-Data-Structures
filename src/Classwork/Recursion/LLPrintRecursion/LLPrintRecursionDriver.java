package Classwork.Recursion.LLPrintRecursion;

public class LLPrintRecursionDriver {
    public static void main(String[] args) {
        //create nodes:
        Node A = new Node('A');
        Node B = new Node('B');
        Node C = new Node('C');
        Node D = new Node('D');
        Node E = new Node('E');

        //link Nodes:
        A.next = B;
        B.next = C;
        C.next = D;
        D.next = E;

        //print in normal order:
        System.out.print("Normal order: ");
        LLPrintRecursion.printNormal(A);
        System.out.println();

        //print in reverse order
        System.out.print("Reverse order: ");
        LLPrintRecursion.printReverse(A);
        System.out.println();


    }
}
