package Classwork.Queue.UnboundedArrayQueue;

public class UnboundedArrayQueue<T> {

    private Object[] queue;
    private int front;
    private int rear;
    private int size;

    public UnboundedArrayQueue(int capacity) {
        queue = new Object[capacity];
        front = 0;
        rear = -1;
        size = 0;
    }

    public void enqueue(T item) {
        if (size == queue.length) {
            resize(); //grow array if full

        }
        rear = (rear + 1) % queue.length;
        queue[rear] = item;
        size++;
        System.out.println(item + " enqueued");
        display();
    }

    @SuppressWarnings("unchecked")
    public T dequeue() {
        if(isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        }
        T item = (T) queue[front];
        queue[front] = null;
        front = (front +1) % queue.length;
        size--;
        System.out.println(item + " dequeued");
        display();
        return item;
    }

    @SuppressWarnings("unchecked")
    public T peek() {
        if(isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        }
        return (T) queue[front];
    }

    public void display() {
        if(isEmpty()) {
            System.out.println("Queue is empty");
            return;
        }
        System.out.println("Queue: ");
        for(int i = 0; i < size; i++) {
            System.out.print(queue[(front + i) % queue.length] + " ");
        }
        System.out.println();
    }

    //Internal functions:
    public boolean isEmpty() {
        return size == 0;
    }

    public int size() {
        return size;
    }

    public void resize() {

        int newCapacity = queue.length * 2;
        Object[] newQueue = new Object[newCapacity];

        //copy elements in correct FIFO order
        for (int i = 0; i < size; i++) {
            newQueue[i] = queue[(front +i) % queue.length];
        }
        queue = newQueue;
        front = 0;
        rear = size - 1;
        System.out.println("Resized queue to capacity: " + newCapacity);
    }
}