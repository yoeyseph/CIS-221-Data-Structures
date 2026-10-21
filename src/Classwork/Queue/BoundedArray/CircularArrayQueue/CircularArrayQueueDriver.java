package Classwork.Queue.BoundedArray.CircularArrayQueue;

public class CircularArrayQueueDriver {
    public static void main(String[] args) {
        CircularArrayQueue<Integer> q = new CircularArrayQueue<>(5);

        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        q.enqueue(40);
        q.enqueue(50);

        q.display();

        q.enqueue(60);

        System.out.println(q.dequeue());
        System.out.println(q.dequeue());

        q.display();
        System.out.println(q.size());

        q.enqueue(60);
        q.enqueue(70);

        q.display();

        q.enqueue(80);

        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());

        q.display();

        System.out.println(q.peek());

        System.out.println(q.isEmpty());

        q.display();

    }
}
