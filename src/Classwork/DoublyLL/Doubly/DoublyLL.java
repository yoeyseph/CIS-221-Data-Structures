package Classwork.DoublyLL.Doubly;

public class DoublyLL<T> {
    Node<T> head;

    //constructor:
    public DoublyLL() {
        head = null;
    }

    //Insert at end:
    public void insertAtEnd(T data) {

        Node<T> newNode = new Node<>(data);

        if(head == null) {
            head = newNode;
            return;

        }

        Node<T> current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.next = newNode;
        newNode.prev = current;
    }

    public void deleteAtBeginning() {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        head = head.next;

        //if list is not empty after deletion:
        if (head != null) {
            head.prev = null;
        }
    }

    public void deleteAtEnd() {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        //Check if there is only one node:
        if (head.next == null) {
            head = null;
            return;
        }

        Node<T> current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.prev.next = null;
    }

    //delete at a specific value;
    public void deleteValue(T target) {
        //check if empty
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        //search for target:
        Node<T> current = head;
        while (current!= null && current.data.equals(target)) {
            current = current.next;
        }

        //target not found:
        if (current == null) {
            System.out.println("Node with Value: " +target + " not found");
            return;
        }
        //Target if fisrt node:
        if (current == head) {
            head = current.next;

            if(head != null) {
                head.prev = null;
            }
            return;
        }
        //connect prev node to next node:
        current.prev.next = current.next;

        //if current is not the last node, connect next node back to previous:
        current.next.prev = current.prev;

    }
    //traverse:
    public void traverse() {

        Node<T> current = head;

        System.out.print("Forward: ");

        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }
}
