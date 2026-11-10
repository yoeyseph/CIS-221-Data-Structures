package Classwork.Tree.BST;

public class BinarySearchTreeDriver {
    public static void main(String[] args) {

        BinarySearchTree<Integer> bst = new BinarySearchTree<>();

        bst.add(15);
        bst.add(21);
        bst.add(21);
        bst.add(23);
        bst.add(21);
        bst.add(1);
        bst.add(21);
        bst.add(231);
        bst.add(91);
        bst.add(21);
        bst.add(561);
        bst.remove(3999);
        bst.display();


    }
}
