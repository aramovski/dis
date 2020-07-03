package de.dis;

import de.dis.entities.Time;

public class TransformManager {

    public TransformManager() {

    }

    public Time getTimeFromDate(String date) {
        if (dateInvalid(date)) return null;

        Time time = new Time();
        time.setDay(getDayFromDate(date));
        time.setMonth(getMonthFromDate(date));
        time.setQuarter(getQuarterFromDate(date));
        time.setYear(getYearFromDate(date));
        return time;
    }

    private int getDayFromDate(String date) {
        return Integer.parseInt(date.split("\\.")[0]);
    }

    private int getMonthFromDate(String date) {
        return Integer.parseInt(date.split("\\.")[1]);
    }

    private int getQuarterFromDate(String date) {
        int month = getMonthFromDate(date);
        if (month >= 1 && month <= 3) return 1;
        if (month >= 4 && month <= 6) return 2;
        if (month >= 7 && month <= 9) return 3;
        if (month >= 10 && month <= 12) return 4;
        else return 0;
    }

    private int getYearFromDate(String date) {
        return Integer.parseInt(date.split("\\.")[2]);
    }

    private boolean dateInvalid(String date) {
        // TODO check for correct date format. this only prevents column title "date" from parsing
        if (date.equals("Date")) {
            System.out.println("Date [" + date + "] invalid");
            return true;
        }
        return false;
    }
}
