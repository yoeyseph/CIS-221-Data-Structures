package Classwork.CollectionADT.WordCount;

import java.util.Scanner;

public class WordCountDriver {
    public static void main(String[] args) {
        try (Scanner input = new Scanner(System.in)) {
            System.out.print("Enter text: ");
            String line = input.nextLine();
            int wordCount = 0;

            for (String word : line.split("[^a-zA-Z']+")) {
                if (!word.isEmpty()) {
                    wordCount++;
                }
            }

            System.out.println("Word count: " + wordCount);
        }
    }
}