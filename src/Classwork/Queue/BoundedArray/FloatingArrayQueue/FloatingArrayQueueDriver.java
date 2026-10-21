package Classwork.Queue.BoundedArray.FloatingArrayQueue;
//PROBLEM OF FLOATING DESIGN:
// items removed from front make the slot useless: null values still take those slots and effects capacity
public class FloatingArrayQueueDriver {
    public static void main(String[] args) {
        FloatingArrayQueue<Integer> q = new FloatingArrayQueue<>(5);

        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);
        q.enqueue(40);
        q.enqueue(50);

        q.display();
        System.out.print(q.size());
        System.out.println();

        System.out.println(q.dequeue());
        System.out.println(q.dequeue());

        q.display();
        System.out.print(q.size());
        System.out.println();

        q.enqueue(60);

        System.out.println(q.dequeue());
        System.out.println(q.dequeue());
        System.out.println(q.dequeue());

        q.display();

        q.enqueue(70);
        q.enqueue(80);

        q.display();
        System.out.print(q.size());
        System.out.println();
    }
}
