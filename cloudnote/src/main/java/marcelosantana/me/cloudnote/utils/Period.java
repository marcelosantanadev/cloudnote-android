package marcelosantana.me.cloudnote.utils;
import java.util.Date;

public class Period {
    public static long years(int count) {
        return count * days(365);
    }

    public static long months(int count) {
        return count * days(30);
    }

    public static long days(int count) {
        return count * hours(24);
    }

    public static long hours(int count) {
        return count * minutes(60);
    }

    public static long minutes(int count) {
        return count * seconds(60);
    }

    public static long seconds(int count) {
        return count * 1000;
    }

    public static long daysBetween(Date readByCustomer, Date date) {
        return Math.abs(date.getTime() - readByCustomer.getTime())/Period.days(1);
    }
}
