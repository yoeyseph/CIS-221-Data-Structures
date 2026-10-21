package Classwork.Queue.BoundedArray.FixedArrayQueue;


public class FixedArrayQueue<T> {
    private Object[] queue;
    private int capacity;
    private int rear;
    private int size;

    public FixedArrayQueue(int capacity) {
        this.capacity = capacity;
        this.queue = new Object[capacity];
        this.rear = -1;
        this.size = 0;
    }

    public void enqueue(T item) {
        if (isFull()) {
            System.out.println("Queue is full");
            return;
        }
        rear++;
        queue[rear] = item;
        size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        }
        T item = (T) queue[0];

        //shift items left
        for (int i = 0; i< rear; i++) {
            queue[i] = queue[i+1];
        }

        queue[rear] = null; //clear last spot
        rear--;
        size--;

        return item;
    }

    public T peek() {
        if(isEmpty()) {
            System.out.println("Queue is empty");
            return null;
        }
        return (T) queue[0];
    }

    public boolean isFull() {
        return size == capacity;
    }
    public boolean isEmpty() {
        return size == 0;
    }

    public void display() {
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
