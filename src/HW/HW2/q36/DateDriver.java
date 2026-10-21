package HW.HW2.q36;

public class DateDriver {
    public static void main(String[] args) {
        Date[] dates = new Date[10];

        // Fill Array
        for (int i = 0; i < dates.length; i++) {
            dates[i] = new Date(12, i + 1, 2005);
        }

        // Print with formaat
        for (int i = 0; i < dates.length; i++) {
            System.out.println(dates[i].getMonth() + "/" + dates[i].getDay() + "/" + dates[i].getYear());
        }
    }
}