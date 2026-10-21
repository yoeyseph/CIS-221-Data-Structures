package HW.HW5.q17ab;

public class PairDriver {
    public static void main(String[] args) {
        //sample
        PairInterface<String> myPair = new BasicPair<String>("apple", "peach");

        System.out.print(myPair.getFirst() + " " + myPair.getSecond());
        System.out.println();
        System.out.print(myPair.getFirst() + " ");
        myPair.setSecond("orange");
        System.out.println(myPair.getSecond());
    }
}