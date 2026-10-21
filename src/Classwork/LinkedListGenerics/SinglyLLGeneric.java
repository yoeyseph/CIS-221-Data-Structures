package Classwork.LinkedListGenerics;

public class SinglyLLGeneric<T> {
    Node<T> head;

    //constructor:
    public SinglyLLGeneric() {
        head = null;
    }

    //Insert at end
    public void insertAtEnd(T data) {

        Node<T> newNode = new Node<>(data);

        if (head == null) {
            head = newNode;
            return;
        }

        Node<T> current = head;

        while (current.next != null) { //move to end
            current = current.next;
        }

        current.next = newNode; //add node after last node
    }

    //traversal Print all content:
    public void traverse() {
        Node<T> current = head;
        while (current != null) {
            System.out.print(current.data + " -> ");
            current = current.next;
        }
        System.out.println("null");
    }

    //Insert at beginning:
    public void insertAtBeginning(T data) {

        Node<T> newNode = new Node<>(data);

        //new node points tp the current head
        newNode.next = head;

        //head now points to newNode:
        head = newNode;
    }

    //Insertion BEFORE target index:


    public void insertBeforeIndex(int index, T data) {
        if (index < 0) {
            System.out.println("Index out of range");
            return;
        }

        if (index == 0) {
            insertAtBeginning(data);
            return;
        }

        // walk to the node just before the target index
        Node<T> current = head;
        for (int i = 0; i < index - 1 && current != null; i++) {
            current = current.next;
        }

        if (current == null) {
            System.out.println("Index out of range");
            return;
        }

        Node<T> newNode = new Node<>(data);
        newNode.next = current.next;
        current.next = newNode;
    }

    public void insertAfterTarget(T target, T data) {
        Node<T> current = head;
        while( current != null && !current.data.equals(target)) {
            current = current.next;
        }
        if (current != null) {
            Node<T> newNode = new Node<>(data);
            newNode.next = current.next;
            current.next = newNode;
        }
        else {
            System.out.println("Node with value: " + target + " not found");
        }
    }
}
