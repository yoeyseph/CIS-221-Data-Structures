package Classwork.Recursion.LLPrintRecursion;

class Node {
    char data;
    Node next;

    Node(char data) {
        this.data = data;
        this.next = null;
    }
}

public class LLPrintRecursion {

    static void printNormal(Node head) {
        if (head == null) {
            return;
        }
        System.out.print(head.data); //print curr node
        printNormal(head.next);     // then recursive call for next node
    }

    static void printReverse(Node head) {
        if (head == null) {
            return;
        }
        printReverse(head.next); //recursive call for next node first
        System.out.print(head.data); // then print curr node
    }

}
