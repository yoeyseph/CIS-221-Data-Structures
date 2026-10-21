package Classwork.Queue.BoundedArray.CircularArrayQueue;
public class CircularArrayQueue<T> {
    private Object[] queue;
    private int capacity;
    private int rear;
    private int size;
    private int front;

    public CircularArrayQueue(int capacity) {
        this.capacity = capacity;

        this.queue = new Object[capacity];

        this.front = 0;
        this.rear = -1;
        this.size = 0;
    }

    public void enqueue(T item) {
        if(isFull()) {
            System.out.println("Queue is full");
            return;
        }
        rear = (rear + 1) % capacity;
        queue[rear] = item;
        size ++;
    }
@SuppressWarnings("unchecked")
    public T dequeue() {
        if(isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        }

        T item = (T) queue[front];
        front = (front+1) % capacity;
        size--;
        return item;
    }
@SuppressWarnings("unchecked")
    public T peek() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        }
        return (T) queue[front];
    }

    public boolean isEmpty() {
        return size == 0;
    }

    public boolean isFull() {
        return size == capacity;
    }

    public int size() {
        return size;
    }

    public void display() {
        System.out.println("Queue: ");
        if (isEmpty()) {
            System.out.println("Empty");
            return;
        }
        for (int i = 0; i<size; i++) {
            int index = (front +i) % capacity;
            System.out.print(queue[index] + " ");
        }
        System.out.println();
    }
}
