package chapter1.exercise2;

public class StudentInfo {
    String name;
    int score;
    public void displayResult() {
        System.out.println("Student: " + name);
        System.out.println("Score: " + score);
        if (score >= 50) {
            System.out.println("Result: PASS");
        } else {
            System.out.println("Result: FAIL");
        }
    }
}
