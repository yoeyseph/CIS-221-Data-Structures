package Classwork.Tree.BSTDemo;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;


// --- Node class ---
class BTNode<T> {
    T data;
    BTNode<T> left;
    BTNode<T> right;
    BTNode(T data) {
        this.data = data;
        left = right = null;
    }
}

// --- Traversal Demo Class ---
public class TraversalDemo {
    BTNode<String> root;

    // Constructor creates a sample tree
    public TraversalDemo() {
        root = new BTNode<>("A");
        root.left = new BTNode<>("B");
        root.right = new BTNode<>("C");
        root.left.left = new BTNode<>("D");
        root.left.right = new BTNode<>("E");
    }


    public void preorderTraversal() {
        System.out.print("Preorder (DFS): ");
        if (root == null) return;
        Stack<BTNode<String>> stack = new Stack<>();
        stack.push(root);
        while (!stack.isEmpty()) {
            BTNode<String> node = stack.pop();
            System.out.print(node.data + " "); // Visit node
// Push right first so left is processed next
            if (node.right != null)
                stack.push(node.right);
            if (node.left != null)
                stack.push(node.left);
        }
        System.out.println();
    }

    public void inorderTraversal() {
        System.out.print("Inorder (DFS): ");
        Stack<BTNode<String>> stack = new Stack<>();
        BTNode<String> current = root;
        while (current != null || !stack.isEmpty()) {
// Step 1: Go left as far as possible
            while (current != null) {
                stack.push(current);
                current = current.left;
            }
// Step 2: Pop from stack and visit
            current = stack.pop();
            System.out.print(current.data + " ");
// Step 3: Move to right subtree
            current = current.right;
        }
        System.out.println();
    }

    public void postorderTraversal() {
        System.out.print("Postorder (DFS, 2 stacks): ");
        if (root == null) return;
        Stack<BTNode<String>> s1 = new Stack<>();
        Stack<BTNode<String>> s2 = new Stack<>();
        s1.push(root);
        while (!s1.isEmpty()) {
            BTNode<String> node = s1.pop();
            s2.push(node);
            if (node.left != null)
                s1.push(node.left);
            if (node.right != null)
                s1.push(node.right);
        }
// Reverse order from s2 gives Left → Right → Root
        while (!s2.isEmpty()) {
            System.out.print(s2.pop().data + " ");
        }
        System.out.println();
    }

    public void breadthFirstTraversal() {
        System.out.print("Breadth-First (BFS): ");
        if (root == null) return;
        Queue<BTNode<String>> queue = new LinkedList<>();
        queue.add(root);
        while (!queue.isEmpty()) {
            BTNode<String> node = queue.remove();
            System.out.print(node.data + " "); // Visit node
            if (node.left != null)
                queue.add(node.left);
            if (node.right != null)
                queue.add(node.right);
        }
        System.out.println();
    }
}

