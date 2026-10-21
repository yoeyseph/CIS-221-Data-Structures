package Classwork.Queue.BoundedArray.FixedArrayQueue;

public class FixedArrayQueueDriver {
    public static void main( String[] args) {

        FixedArrayQueue<String> q1 = new FixedArrayQueue<>(5);

        q1.dequeue();

        q1.enqueue("A");
        q1.enqueue("B");
        q1.enqueue("C");
        q1.enqueue("D");
        q1.enqueue("E");

        q1.display();

        q1.enqueue("F");

        q1.dequeue();
        q1.display();
        q1.enqueue("F");
        q1.display();
        System.out.print(q1.peek());

        FixedArrayQueue<Integer> q2 = new FixedArrayQueue<>(3);

        q2.enqueue(10);
        q2.enqueue(20);
        q2.enqueue(30);
        q2.enqueue(40);
        q2.dequeue();
        q2.display();
        q2.enqueue(40);
        q2.display();
        System.out.print(q2.peek());

    }


}
