package Classwork.Recursion;
import java.util.Arrays;

public class BinarySearchRecursion {

    public static int binarySearchRecursive(int[] arr, int left, int right, int target) {

        if(left > right) { //BASE CASE
            return -1;
        }

        int mid =  left + (right - left) / 2;

        if(arr[mid] == target) {
            return mid;
        }

        if(arr[mid] > target) {
            return  binarySearchRecursive(arr, left, mid -1, target); //right shifts to the left
        } else {                                                                 // all other fields stay the same
            return binarySearchRecursive(arr, mid -1, right, target); // left shifts to the right
        }

    }
}
