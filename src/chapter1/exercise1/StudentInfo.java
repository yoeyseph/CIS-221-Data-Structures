package chapter1.exercise1;

public class StudentInfo {
    // Instance variables
    String name;
    int rollNo;
    int age;
    // Static variable
    static String collegeName = "ABC College";

    // Instance method
    public void display() {
        System.out.println(name + " is " + age + " years old.");
    }

    // Static method
    public static void displayCollege() {
        System.out.println("College: " + collegeName);
    }
}