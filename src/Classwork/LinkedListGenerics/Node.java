package Classwork.LinkedListGenerics;

public class Node<T> {
    T data;
    Node<T> next;

    //constructor:
    Node(T data) {
        this.data = data;
        this.next = null;
    }
}
