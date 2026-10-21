package HW.HW2.q36;

public class Date {
    private int month;
    private int day;
    private int year;

    //constructor
    public Date(int newMonth, int newDay, int newYear) {
        month = newMonth;
        day = newDay;
        year = newYear;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }
}