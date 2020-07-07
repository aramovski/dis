package de.dis;

import de.dis.entities.Fact;
import de.dis.entities.Geography;
import de.dis.entities.Product;
import de.dis.entities.Time;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;

public class TransformManager {

    private List<Product> productList;
    private List<Geography> geographyList;
    private List<Time> timeList;

    public TransformManager(List<Time> timeList, List<Geography> geographyList, List<Product> productList) {
        this.timeList = timeList;
        this.geographyList = geographyList;
        this.productList = productList;
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

    public Fact getFactFromCsv(String[] fact_entry) {
        String datestring = fact_entry[0];
        String shopstring = fact_entry[1];
        String articlestring = fact_entry[2];
        String soldstring = fact_entry[3];
        String revenuestring = fact_entry[4];

        Fact result = new Fact();
        result.setRevenue(Double.parseDouble(revenuestring.replace(",", ".")));
        try {
            result.setSold(Integer.parseInt(soldstring));
        } catch (NumberFormatException e) {
            System.out.println("Data [" + Arrays.toString(fact_entry) + "] invalid. Will be ignored.");
        }
        result.setTimeId(this.getTimeIdForDate(datestring));
        result.setProductId(this.getProductIdForArticle(articlestring));
        result.setGeographyId(this.getGeoIdForShop(shopstring));

        return result;
    }

    private int getGeoIdForShop(String shopstring) {
        // normal case
        try {
            Geography geo = geographyList.stream().filter(g -> g.getShopName() == shopstring).findFirst().get();
            return geo.getId();
        } catch (NoSuchElementException e) { }
        // ü
        try {
            Geography geo = geographyList.stream().filter(g -> g.getShopName().replaceAll("\\p{Sc}", "ü") == shopstring).findFirst().get();
            return geo.getId();
        } catch (NoSuchElementException e) { }
        return -1;
    }

    private int getProductIdForArticle(String articlestring) {
        String replacement = "\\p{Sc}";
        // normal case
        try {
            Product prod = productList.stream().filter(p -> p.getArticleName().equals(articlestring)).findFirst().get();
            return prod.getId();
        } catch (NoSuchElementException e) { }
        // Ö
        try {
            Product prod = productList.stream().filter(p -> p.getArticleName().replaceAll(replacement, "Ö").equals(articlestring)).findFirst().get();
            return prod.getId();
        } catch (NoSuchElementException e) { }
        // ö
        try {
            Product prod = productList.stream().filter(p -> p.getArticleName().replaceAll(replacement, "ö").equals(articlestring)).findFirst().get();
            return prod.getId();
        } catch (NoSuchElementException e) { }
        // Ü
        try {
            Product prod = productList.stream().filter(p -> p.getArticleName().replaceAll(replacement, "Ü").equals(articlestring)).findFirst().get();
            return prod.getId();
        } catch (NoSuchElementException e) { }
        // ü
        try {
            Product prod = productList.stream().filter(p -> p.getArticleName().replaceAll(replacement, "ü").equals(articlestring)).findFirst().get();
            return prod.getId();
        } catch (NoSuchElementException e) { }
        return -1;
    }

    private int getTimeIdForDate(String datestring) {
        Time dateFromCsv = this.getTimeFromDate(datestring);
        Time time = timeList.stream().filter(t -> t.equals(dateFromCsv)).findFirst().get();
        return time.getId();
    }


}
