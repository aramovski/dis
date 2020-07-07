package de.dis;

import de.dis.entities.Fact;
import de.dis.entities.Geography;
import de.dis.entities.Product;
import de.dis.entities.Time;
import de.dis.entities.Time;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

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
        result.setRevenue(Double.parseDouble(revenuestring));
        result.setSold(Integer.parseInt(soldstring));
        result.setTimeId(this.getTimeIdForDate(datestring));
        result.setProductId(this.getProductIdForArticle(articlestring));
        result.setGeographyId(this.getGeoIdForShop(shopstring));
        return result;
    }

    private int getGeoIdForShop(String shopstring) {
        Geography geo = geographyList.stream().filter(g -> g.getShopName() == shopstring).findFirst().get();
        return geo.getId();
    }

    private int getProductIdForArticle(String articlestring) {
        Product prod = productList.stream().filter(p -> p.getArticleName() == articlestring).findFirst().get();
        return prod.getId();
    }

    private int getTimeIdForDate(String datestring) {
        //TODO
        return 0;
    }


}
