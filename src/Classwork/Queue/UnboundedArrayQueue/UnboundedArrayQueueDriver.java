package Classwork.Queue.UnboundedArrayQueue;

public class UnboundedArrayQueueDriver {
    public static void main(String[] args) {
        UnboundedArrayQueue<Integer> q = new UnboundedArrayQueue<>(3);

        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        q.enqueue(40);
        q.enqueue(50);

        System.out.println(q.dequeue());
        System.out.println(q.dequeue());

        q.enqueue(60);
        q.enqueue(70);

        System.out.println(q.peek());
        q.display();


        UnboundedArrayQueue<String> s = new UnboundedArrayQueue<>(2);

        s.enqueue("Collin");
        s.enqueue("Trevor");
        s.enqueue("Seth");

        s.enqueue("Valentino");
        s.enqueue("Joseph");
        s.enqueue("Terrance");

        s.dequeue();
        s.enqueue("Collin");
        System.out.println("peek: " + s.peek());
        System.out.println("size = " + s.size());

    }
}
