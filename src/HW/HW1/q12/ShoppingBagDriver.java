package HW.HW1.q12;

public class ShoppingBagDriver {
    public static void main(String[] args) {

        ShoppingBag bag = new ShoppingBag(0.07);

        bag.place(3, 1.00);

        bag.place(7, 400);

        System.out.println(bag.toString());
    }
}
