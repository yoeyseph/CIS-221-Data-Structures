package Classwork.LinkedList;
/*
public class SinglyList {
    Node head;  //1st node

    //constructor;
    public SinglyList() {
        head = null; //initialized as empty
    }

    //Traversal (printing with format)
    public void traverse() {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + "->");
            current = current.next;
        }
        System.out.println("null");
    }

    // Insert at beginning:
    public void insertAtBeginning(int data) {
        Node newNode = new Node(data);
        newNode.next = head;
        head = newNode;
    }

    //Insert at end:
    public void insertAtEnd(int data) {
        Node newNode = new Node(data);
        if (head == null) {
            head = newNode;
            return;
        }
        Node current = head;  // set current = to head so head will not be manipulated
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    //Insert after a specific value
    public void insertAfter(int target, int data) {
        Node current = head;
        while (current != null && current.data != target) {
            current = current.next;
        }

        if (current != null) {
            Node newNode = new Node(data);
            newNode.next = current.next;
            current.next = newNode;
        } else {
            System.out.println("Node with value " + target + " not found");
        }
    }


    /// //////////////DELETION////////////////////
    public void deleteAtFront() {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        head = head.next;

    }

    public void deleteAtEnd() {
        //if list is empty:
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }
        //if head is also the end:
        if (head.next == null) {
            head = null;
            return;
        }
        //if head isn't the only node:
        Node current = head;
        while (current.next.next != null) {
            current = current.next;
        }
        current.next = null;
    }
    public void deleteAfterTarget(int target) {
        if (head == null) {
            System.out.println("List is empty.");
            return;
        }

        Node current = head;
        while (current != null && current.data != target) {
            current = current.next;
        }

        if (current == null) {
            System.out.println("Node with value " + target + " not found");
        } else if (current.next == null) {
            System.out.println("No node exists after target");
        } else {
            current.next = current.next.next;
        }
    }
    }
}
*/
