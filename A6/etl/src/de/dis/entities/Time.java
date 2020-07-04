package de.dis.entities;

public class Time {

    private int day;
    private int month;
    private int quarter;
    private int year;

    public Time() {

    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getQuarter() {
        return quarter;
    }

    public void setQuarter(int quarter) {
        this.quarter = quarter;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTimeAsDate() {
        return this.getDay() + "." + this.getMonth() + "." + this.getYear();
    }

    @Override
    public String toString() {
        String string = "Day: " + this.getDay() + "\n"
                + "Month: " + this.getMonth() + "\n"
                + "Quarter: " + this.getQuarter() + "\n"
                + "Year: " + this.getYear() + "\n";
        return string;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Time) {
            Time time = (Time) obj;
            return (time.getDay() == this.getDay() &&
                    time.getMonth() == this.getMonth() &&
                    time.getQuarter() == this.getQuarter() &&
                    time.getYear() == this.getYear());
        } else {
            return false;
        }
    }
}
