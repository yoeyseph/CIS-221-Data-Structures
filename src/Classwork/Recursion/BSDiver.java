package Classwork.Recursion;
import java.util.Arrays;

public class BSDiver {
    public static void main(String[] args) {
        // example array (must be sorted)
        int[] arr = {2, 5, 8, 12, 16, 23, 38, 56, 72};
        int target = 23;

        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Target: " + target);

        //perfrom earch:
        int index = BinarySearchRecursion.binarySearchRecursive(arr, 0, arr.length -1, target);

        if (index != -1) {
            System.out.println("Element " + target + " found at index: " + index);
        } else {
            System.out.println("Element " + target + " not found");

        }

    }
}
