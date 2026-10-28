package Classwork.CollectionADT.VocabDensity;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class VocabDensityDriver {
    public static void main(String[] args) throws IOException {
        int CAPACITY = 1000;
        String fname = "input.txt";
        String word;
        int numWords = 0;
        double density;

        CollectionInterface<String> words = new ArrayCollection<String>(CAPACITY);

        FileReader fin = new FileReader(fname);
        Scanner wordsIn = new Scanner(fin);
        wordsIn.useDelimiter("[^a-zA-Z']+");

        while (wordsIn.hasNext()) {
            word = wordsIn.next();
            word = word.toLowerCase();

            if (!words.contains(word)) {
                words.add(word);
            }
            numWords++;
        }
        wordsIn.close();


        if (!words.isEmpty()) {
            density = (double) numWords / words.size();
        } else {
            density = 0.0;
        }
        System.out.println("Analyzed file: " + fname);
        System.out.println("Total words: " + numWords);

        if (words.isFull()) {
            System.out.println("Unique words: at least " + words.size());

        } else {
            System.out.println("unique words: " + words.size());

            System.out.println("Vocabulary density: " + String.format("%.2f", density));
        }
        System.out.println(density);
    }
}
