package de.dis;

import de.dis.entities.Geography;
import de.dis.entities.Product;
import de.dis.entities.Time;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public class LoadManager {

    private Connection dbConnection;
    private ExtractManager extractManager;

    public LoadManager(Connection dbConnection, ExtractManager extractManager) {
        this.dbConnection = dbConnection;
        this.extractManager = extractManager;
    }

    // populate product dimension
    public void loadProducts() {

        System.out.println("\n========== Products ==========\n");

        List<Product> productList = extractManager.getProductList();

        productList.forEach(product -> {
            try {
                String insertSQL = "INSERT INTO public.product_dimension(article_id, article_name, productgroup_id, productgroup_name, productfamily_id, productfamily_name, productcategory_id, productcategory_name, price) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?);";

                PreparedStatement pstmt = dbConnection.prepareStatement(insertSQL,
                        Statement.RETURN_GENERATED_KEYS);

                pstmt.setInt(1, product.getArticleId());
                pstmt.setString(2, product.getArticleName());
                pstmt.setInt(3, product.getProductGroupId());
                pstmt.setString(4, product.getProductGroupName());
                pstmt.setInt(5, product.getProductFamilyId());
                pstmt.setString(6, product.getProductFamilyName());
                pstmt.setInt(7, product.getProductCategoryId());
                pstmt.setString(8, product.getProductCategoryName());
                pstmt.setDouble(9, product.getPrice());

                pstmt.executeUpdate();
                pstmt.close();

                System.out.println("Product [" + product.getArticleName() + "] stored in Database\n");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    // populate geography dimension
    public void loadGeographies() {
        System.out.println("\n========== Geographies ==========\n");

        List<Geography> geographyList = extractManager.getGeographyList();

        geographyList.forEach(geography -> {
            try {
                String insertSQL = "INSERT INTO geography_dimension(shop_id, shop_name, city_id, city_name, region_id, region_name, country_id, country_name) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";

                PreparedStatement pstmt = dbConnection.prepareStatement(insertSQL,
                        Statement.RETURN_GENERATED_KEYS);

                pstmt.setInt(1, geography.getShopId());
                pstmt.setString(2, geography.getShopName());
                pstmt.setInt(3, geography.getCityId());
                pstmt.setString(4, geography.getCityName());
                pstmt.setInt(5, geography.getRegionId());
                pstmt.setString(6, geography.getRegionName());
                pstmt.setInt(7, geography.getCountryId());
                pstmt.setString(8, geography.getCountryName());

                pstmt.executeUpdate();
                pstmt.close();

                System.out.println("Geography [" + geography.getShopName() + "] stored in Database\n");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    // populate time timension
    public void loadTimes() {

        System.out.println("\n========== Times ==========\n");

        List<Time> timeList = extractManager.getTimeList();

        timeList.forEach(time -> {
            try {
                String insertSQL = "INSERT INTO public.time_dimension(day, month, year, quarter) VALUES (?, ?, ?, ?);";

                PreparedStatement pstmt = dbConnection.prepareStatement(insertSQL,
                        Statement.RETURN_GENERATED_KEYS);

                pstmt.setInt(1, time.getDay());
                pstmt.setInt(2, time.getMonth());
                pstmt.setInt(3, time.getYear());
                pstmt.setInt(4, time.getQuarter());

                pstmt.executeUpdate();
                pstmt.close();

                System.out.println("Time [" + time.getTimeAsDate() + "] stored in Database\n");
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    // populate facts table
    public void loadFacts() {
        try {
            String insertSQL = "INSERT INTO fact_table(product_id, time_id, geography_id, sold, revenue) VALUES (?, ?, ?, ?, ?);";

            PreparedStatement pstmt = dbConnection.prepareStatement(insertSQL,
                    Statement.RETURN_GENERATED_KEYS);

            pstmt.executeUpdate();
            pstmt.close();

            // System.out.println("Fact [" + fact.toString() + "] stored in Database\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}
