package Classwork.Tree.BSTFreq;


public class BinarySearchTree<T extends Comparable<T>> {
    private Node<T> root;
    private int size = 0;

    public boolean add(T element) {
        root = addRecursive(root, element);
        return true;
    }
    private Node<T> addRecursive(Node<T> node, T element) {
        if(node == null) {
            size++;
            return new Node<>(element);
        }
        int cmp = element.compareTo(node.data);
        if(cmp < 0) {
            node.left = addRecursive(node.left, element);
        }
        else if ( cmp > 0) {
            node.right = addRecursive(node.right, element);
        }
        return node; //duplicates are ignored
    }


    public T get(T target) {
        Node<T> node = root;
        while(node != null) {
            int cmp = target.compareTo(node.data);
            if(cmp == 0) {
                return node.data; //found
            }
            else if(cmp < 0) {
                node = node.left;
            }
            else {
                node = node.right;
            }
        }
        return null;
    }

    public int size() {
        return size;
    }

    //in order traversal
    public void inorderTraversal() {
        inorder(root);
    }

    private void inorder(Node<T> node) {
        if( node == null) {return;}
        inorder(node.left);
        System.out.print(node.data);
        inorder(node.right);

    }

    public boolean contains(T target) {
        Node<T> node = root;
        while(node != null) {
            int cmp = target.compareTo(node.data);
            if(cmp == 0) {
                return true;
            }
        }
        return false;
    }
}
