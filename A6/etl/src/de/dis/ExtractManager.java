package de.dis;

import de.dis.entities.Geography;
import de.dis.entities.Product;
import de.dis.entities.Time;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ExtractManager {

    private Connection dbConnection;

    private List<Product> productList;
    private List<Geography> geographyList;
    private List<Time> timeList;

    public ExtractManager(Connection dbConnection) {
        this.dbConnection = dbConnection;
        this.productList = new ArrayList<>();
        this.geographyList = new ArrayList<>();
        this.timeList = new ArrayList<>();
    }

    public void extractProducts() {

        //TODO use only one join sql for all the merging as in geography extraction
        try {
            String selectSQL = "SELECT * FROM article";

            PreparedStatement pstmt = dbConnection.prepareStatement(selectSQL);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Product product = new Product();
                product.setArticleId(rs.getInt("articleid"));
                product.setArticleName(rs.getString("name"));
                product.setProductGroupId(rs.getInt("productgroupid"));
                product.setPrice(rs.getDouble("price"));
                productList.add(product);
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // get product group details
        productList.forEach(product -> {
            try {
                String selectSQL = "SELECT * FROM productgroup WHERE productgroupid = ?";
                PreparedStatement pstmt = dbConnection.prepareStatement(selectSQL);
                pstmt.setInt(1, product.getProductGroupId());
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    product.setProductGroupName(rs.getString("name"));
                    product.setProductFamilyId(rs.getInt("productfamilyid"));
                }

                rs.close();
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        // get product family details
        productList.forEach(product -> {
            try {
                String selectSQL = "SELECT * FROM productfamily WHERE productfamilyid = ?";
                PreparedStatement pstmt = dbConnection.prepareStatement(selectSQL);
                pstmt.setInt(1, product.getProductFamilyId());
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    product.setProductFamilyName(rs.getString("name"));
                    product.setProductCategoryId(rs.getInt("productcategoryid"));
                }

                rs.close();
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });

        // get product category details
        productList.forEach(product -> {
            try {
                String selectSQL = "SELECT * FROM productcategory WHERE productcategoryid = ?";
                PreparedStatement pstmt = dbConnection.prepareStatement(selectSQL);
                pstmt.setInt(1, product.getProductCategoryId());
                ResultSet rs = pstmt.executeQuery();

                if (rs.next()) {
                    product.setProductCategoryName(rs.getString("name"));
                }

                rs.close();
                pstmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        });
    }

    public void showProducts() {
        System.out.println("\n========== Products ==========\n");
        productList.forEach(product -> System.out.println(product.toString()));
    }

    public void extractGeographies() {
        try {
            String selectSQL = "SELECT shop.shopid, shop.cityid, shop.name AS shopname, " +
                    "city.regionid, city.name AS cityname, " +
                    "region.countryid, region.name AS regionname, " +
                    "country.name AS countryname" +
                    " FROM shop" +
                    " INNER JOIN city ON shop.cityid = city.cityid" +
                    " INNER JOIN region ON city.regionid = region.regionid" +
                    " INNER JOIN country ON region.countryid = country.countryid";

            PreparedStatement pstmt = dbConnection.prepareStatement(selectSQL);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Geography geography = new Geography();
                geography.setShopId(rs.getInt("shopid"));
                geography.setShopName(rs.getString("shopname"));
                geography.setCityId(rs.getInt("cityid"));
                geography.setCityName(rs.getString("cityname"));
                geography.setRegionId(rs.getInt("regionid"));
                geography.setRegionName(rs.getString("regionname"));
                geography.setCountryId(rs.getInt("countryid"));
                geography.setCountryName(rs.getString("countryname"));
                geographyList.add(geography);
            }

            rs.close();
            pstmt.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void showGeographies() {
        System.out.println("\n========== Geographies ==========\n");
        geographyList.forEach(geography -> System.out.println(geography.toString()));
    }


    public void extractTimes() {
    }
}
