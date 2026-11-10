package Classwork.Tree.BST;

interface BSTInterface<T extends Comparable<T>> {
    boolean add(T element);
    boolean contains(T target);
    boolean remove(T target);
    boolean isEmpty();
    int size();
}

class BSTNode<T> {
    T data;
    BSTNode<T> left;
    BSTNode<T> right;

    BSTNode(T data) {
        this.data = data;
        left = right = null;
    }
}

public class BinarySearchTree<T extends Comparable<T>> implements BSTInterface<T> {

    private BSTNode<T> root;
    private boolean found;

    public boolean add(T element) {
        if (element == null) {
            throw new IllegalArgumentException("Element cannot be null");
        }
        if (contains(element)) {
            return false;
        }

        root = add(element, root);
        return true;
    }

    private BSTNode<T> add(T element, BSTNode<T> node) {
        if (node == null) {
            return new BSTNode<T>(element);
        }
        if (element.compareTo(node.data) < 0) {
            node.left = add(element, node.left);
        } else if (element.compareTo(node.data) > 0) {
            node.right = add(element, node.right);
        }
        return node;
    }

    public boolean contains(T target) {
        return contains(target, root);
    }

    private boolean contains(T target, BSTNode<T> node) {
        if (node == null) {
            return false;
        }

        int cmp = target.compareTo(node.data);
        if (cmp < 0) {
            return contains(target, node.left);
        } else if (cmp > 0) {
            return contains(target, node.right);
        } else {
            return true;
        }
    }

    public boolean remove(T target) {
        root = remove(target, root);
        return found;
    }

    private BSTNode<T> remove(T target, BSTNode<T> node) {
        if (node == null) {
            found = false;
            return null;
        }
        int cmp = target.compareTo(node.data);
        if (cmp < 0) {
            node.left = remove(target, node.left);
        } else if (cmp > 0) {
            node.right = remove(target, node.right);
        } else {
            found = true;
            node = removeNode(node);
        }
        return node;
    }

    private BSTNode<T> removeNode(BSTNode<T> node) {
        if (node.left == null) {
            return node.right;
        } else if (node.right == null) {
            return node.left;
        } else {
            T predecessorData = getPredecessor(node.left);
            node.data = predecessorData;
            node.left = remove(predecessorData, node.left);
            return node;
        }
    }

    private T getPredecessor(BSTNode<T> subtree) {
        BSTNode<T> temp = subtree;
        while (temp.right != null) {
            temp = temp.right;
        }
        return temp.data;
    }

    public int size() {
        return size(root);
    }

    private int size(BSTNode<T> node) {
        if (node == null) {
            return 0;
        }
        return 1 + size(node.left) + size(node.right);
    }

    public boolean isEmpty() {
        return root == null;
    }

    // Display
    public void display() {
        if (root == null) {
            System.out.println("Tree is empty");
        } else {
            System.out.print("BST elements (in sorted order): ");
            inorderDisplay(root);
            System.out.println();
        }
    }

    private void inorderDisplay(BSTNode<T> node) {
        if (node == null) {
            return;
        }
        inorderDisplay(node.left);
        System.out.print(node.data + " ");
        inorderDisplay(node.right);
    }
}