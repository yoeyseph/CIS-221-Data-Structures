package Classwork.Tree.BSTFreq;

public class WordFreq implements Comparable<WordFreq>{
    private String word;
    private int freq;

    public WordFreq(String word) {
        this.word = word;
        this.freq = 0;

        }

        @Override
    public int compareTo(WordFreq other) {
        return this.word.compareTo(other.word);
    }
    public void inc() { //increment
        freq++;
    }

    public String getWord() {
        return word;
    }
    public int getFreq() {
        return freq;
    }

    @Override
    public String toString() {
        return word + ":" + freq;
    }
}
