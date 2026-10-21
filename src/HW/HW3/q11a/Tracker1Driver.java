package HW.HW3.q11a;

public class Tracker1Driver {
    public static void main( String[] args) {
        NumTrackerInterface nt = new Tracker1();
        nt.add(5);
        nt.add(15);
        nt.add(9);
        System.out.print(nt.getCount()+  " ");
        System.out.print(nt.getSum() + " ");
        System.out.println(nt.getAverage());
    }
}
