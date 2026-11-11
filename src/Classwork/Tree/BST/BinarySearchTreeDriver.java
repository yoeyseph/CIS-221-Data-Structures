package Classwork.Tree.BST;

public class BinarySearchTreeDriver {
    public static void main(String[] args) {

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        System.out.println("Adding elements...");
        bst.add(50);
        bst.add(30);
        bst.add(70);
        bst.add(20);
        bst.add(40);
        bst.add(60);
        bst.add(80);


        // Attempt to add duplicate
        System.out.println("Adding duplicate 30: " + bst.add(30)); //false
        bst.display();
        System.out.println("Tree size: " + bst.size());
        System.out.println("Contains 40? " + bst.contains(40));
        System.out.println("Contains 90? " + bst.contains(90));
        System.out.println("\nRemoving 20, 30, 50...");
        bst.remove(20);
        bst.remove(30);
        bst.remove(50);
        bst.display();

        System.out.println("Tree size after removals: " + bst.size());
        System.out.println("Contains 30? " + bst.contains(30));
        System.out.println("Contains 60? " + bst.contains(60));
        System.out.println("Tree is empty? " + bst.isEmpty());


    }
}
