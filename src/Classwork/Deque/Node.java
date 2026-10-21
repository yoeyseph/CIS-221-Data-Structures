package Classwork.Deque;

public class Node<T> {
    T data;
    Node<T> next;
    Node<T> prev;

    Node(T data) {
        this.data = data;
        this.next = null;
        this.prev = null;
    }
}

    class LinkedDeque<T> {
        private Node<T> front;
        private Node<T> rear;
        private int size;

       public LinkedDeque() {
           this.rear = null;
           this.front = null;
    }
    public void addFront(T item) {
       Node<T> newNode = new Node<>(item) ;

       if(isEmpty()) {
       front = rear = newNode;
       }else {
           newNode.next = front;
           front.prev = newNode;
           front = newNode;
        }
        size++;
    }

    public void addRear(T item) {
        Node<T> newNode = new Node<>(item);

        if (isEmpty()) {
            front = rear = newNode;
        } else {
            rear.next = newNode;
            newNode.prev = rear;
            rear = newNode;
        }
        size++;
    }

    public T removeFront() {
           if(isEmpty()) {
               System.out.println("Deque is empty");
               return null;
           }

           T data = front.data;
           front = front.next;

           if(front == null) {
               rear = null;
           } else {
               front.prev = null;
           }
           size--;
           return data;
    }

    public T removeRear() {
        if(isEmpty()) {
            System.out.println("Deque is empty");
            return null;
        }

        T data = rear.data;
        rear = rear.prev;

        if(rear == null) {
            front = null;
        } else {
            rear.next = null;
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

    public void display() {
        if(isEmpty()) {
            System.out.println("Deque is empty");
            return;
        }
        Node<T> current = front;
        System.out.print("Front-> ");
        while ( current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }


    public static void main(String[] args) {
           LinkedDeque<Integer> deque = new LinkedDeque<>();

           deque.addRear(10);
           deque.addRear(20);
           deque.addRear(30);

           deque.display();

           deque.addFront(5);
           deque.addFront(2);

           deque.display();

           System.out.println("deque: " + deque.removeFront());
           deque.display();
           System.out.println("deque: " + deque.removeRear());
           deque.display();

           System.out.println("Size: " + deque.getSize());







    }
}
