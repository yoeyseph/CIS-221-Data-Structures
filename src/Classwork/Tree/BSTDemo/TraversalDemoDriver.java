package Classwork.Tree.BSTDemo;

public class TraversalDemoDriver {
    public static void main(String[] args) {
        TraversalDemo tree = new TraversalDemo();
        System.out.println("Tree Traversal Demonstration ");
        tree.preorderTraversal(); // A B D E C
        tree.inorderTraversal(); // D B E A C
        tree.postorderTraversal(); // D E B C A
        tree.breadthFirstTraversal(); // A B C D E
    }
}
