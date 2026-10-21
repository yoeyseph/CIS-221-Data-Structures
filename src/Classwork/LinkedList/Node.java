package Classwork.LinkedList;

public class Node {
    int data;
    Node next;  //Node is a data type, not related to the class simply called "Node"
    Node head;
    //constructor
    public Node(int data) {
        this.data = data;
        next = null;  // [ 40 | null ]   when first created, the is no next node, therefore "next = null"
        head = null;
    }


}
