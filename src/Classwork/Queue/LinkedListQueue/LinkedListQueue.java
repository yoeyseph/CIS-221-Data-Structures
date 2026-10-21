package Classwork.Queue.LinkedListQueue;

public class LinkedListQueue<T> {
    private Node<T> front;
    private Node<T> rear;
    private int size;

    public LinkedListQueue() {
        this.front = null;
        this.rear = null;
        this.size = 0;
    }

    public void enqueue( T item) {
        Node<T> newNode = new Node<>(item);
        if (rear == null) {

            front = rear = newNode;

        } else {
            rear.next = newNode;
            rear = newNode;
        }
        size++;
        System.out.println("Enqueued: " + item);
        System.out.println("Queue: ");
        display();
    }

    public T dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        }
        T data = front.data;
        front = front.next;
        if (front == null) { //queue becomes empty
            rear = null;
        }
        size--;
        return data;
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public int getSize() {
        return size;
    }

    public T peek() {
        if(isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        }
        return front.data;
    }

    public void display() {
        if(isEmpty()) {
            System.out.println("Queue is Empty");
            return;
        }
        Node<T> current = front;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();

    }

    public static void main(String[] args) {
        LinkedListQueue<Integer> intQueue = new LinkedListQueue<>();

        intQueue.enqueue(10);
        intQueue.enqueue(20);
        intQueue.enqueue(30);

        intQueue.display();

        System.out.println("Dequeued: " + intQueue.dequeue());

        intQueue.enqueue(40);
        intQueue.display();

        System.out.println("Dequeued: " + intQueue.dequeue());

        intQueue.display();

        System.out.println("Peek: " + intQueue.peek());

        System.out.println("Dequeued: " + intQueue.dequeue());

        intQueue.display();
    }
}
