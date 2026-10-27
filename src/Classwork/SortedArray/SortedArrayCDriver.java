package Classwork.SortedArray;

public class SortedArrayCDriver {
    public static void main(String[] args) {
        SortedArrayC<String> str = new SortedArrayC<>(6);

        str.add("Valentino");
        str.add("Joseph");
        str.add("Trevor");
        str.add("Seth");
        str.add("Terrance");
        str.add("Collin");

        str.display();

        System.out.println(str.contains("Seth"));
        System.out.println(str.get("Collin"));
        str.remove("Joseph");

        str.display();

        System.out.println(str.size());

        System.out.println(str.isFull());
        System.out.println(str.isEmpty());
    }
    SortedArrayC<Integer> inte = new SortedArrayC<>(6);

    inte.add(40);
}
