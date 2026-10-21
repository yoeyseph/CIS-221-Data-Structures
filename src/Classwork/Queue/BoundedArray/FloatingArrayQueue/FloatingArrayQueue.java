package Classwork.Queue.BoundedArray.FloatingArrayQueue;

public class FloatingArrayQueue<T> {

    private Object[] queue;
    private int capacity;
    private int rear;
    private int size;
    private int front;

    protected FloatingArrayQueue(int capacity) {
        this.capacity = capacity;

        this.queue = new Object[capacity];

        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    protected void enqueue(T item) {
        if (isFull()) {
            System.out.println("Queue is full.");
            return;
        }
        rear++;
        queue[rear] = item;
        size++;
    }
    @SuppressWarnings("unchecked")
    protected T dequeue() {
        if(isEmpty()) {
            System.out.println("Queue is  empty");

            return null;
        }

        T item = (T) queue[front];

        queue[front] = null;

        front++;
        size--;

        if(size == 0) {
            front = 0;
            rear = -1;
        }

        return item;
    }

    @SuppressWarnings("unchecked")
    protected T peek() {
        if( isEmpty()) {
            return null;
        }

        return (T) queue[front];
    }
    protected boolean isFull() {

        return rear == capacity - 1;
    }
    protected boolean isEmpty() {
        return size == 0;
    }
    protected int size() {
        return size;
    }
    protected void display() {
        if(isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        System.out.print("Queue: ");
        for(int i = 0; i <= rear; i++) {
            System.out.print(queue[i] + " ");
        }
        System.out.println();
    }
}
