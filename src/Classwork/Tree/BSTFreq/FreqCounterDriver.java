package Classwork.Tree.BSTFreq;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;


public class FreqCounterDriver {
    public static void main(String[] args) throws IOException {
        String word;
        WordFreq wordToTry;
        WordFreq wordInTree;

        BinarySearchTree<WordFreq> tree = new BinarySearchTree<>();
        int numWords = 0;
        String fname = "C:\\Users\\Joseph\\OneDrive\\School\\IWU\\Fall2026\\Data Structures\\test.txt"; //FILE LOCATION==============================================

        FileReader fin = new FileReader(fname);
        //STEP 2: Create Scanner to read the file
        Scanner wordsIn = new Scanner(fin);

        //Split non-letters and apostrophes
        wordsIn.useDelimiter("[^a-zA-Z']");

        while(wordsIn.hasNext()) {
            word = wordsIn.next().toLowerCase();

            //skip empty tokens
            if (word.isEmpty()) {
                continue;
            }

            numWords++;
            wordToTry = new WordFreq(word);

            //check id the word alr exists in the BST
            wordInTree = tree.get(wordToTry);

            if (wordInTree != null) {
                wordInTree.inc();
            } else {
                wordToTry.inc();
                tree.add(wordToTry);
            }
        }
        wordsIn.close();


        // --- Output Results ---
        System.out.println("\n=== Word Frequency Counter ===");
        System.out.println("Total words processed: " + numWords);
        System.out.println("Unique words: " + tree.size());
        System.out.println("\nWords and their frequencies alphabetical order): ");
        tree.inorderTraversal(); // prints sorted word:freq pairs


    }
}
