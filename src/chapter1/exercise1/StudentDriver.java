package chapter1.exercise1;

public class StudentDriver {
    public static void main(String[] args) {
        StudentInfo student1 = new StudentInfo();
        student1.name = "Alice";
        student1.rollNo = 34;
        student1.age = 17;
        student1.display();
        StudentInfo.displayCollege();
    }
}
