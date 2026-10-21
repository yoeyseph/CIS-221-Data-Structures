package HW.HW3.q11a;

public class Tracker1 implements NumTrackerInterface {
    private int sum;
    private int count;

    public Tracker1() {
        sum = 0;
        count = 0;
    }

    public void add(int num) {
        sum += num;
        count ++;
    }

    public int getSum() {
        return sum;
    }

    public int getCount() {
        return count;
    }

    public double getAverage() {
        return (double) sum / count;
    }
}
