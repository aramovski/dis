package de.dis.data;

import de.dis.FormUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Estate {

    private int id = -1;
    private String city;
    private int postalcode;
    private String street;
    private String streetNumber;
    private int squareArea;
    private String manager;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public int getPostalcode() {
        return postalcode;
    }

    public void setPostalcode(int postcalcode) {
        this.postalcode = postcalcode;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(String streetNumber) {
        this.streetNumber = streetNumber;
    }

    public int getSquareArea() {
        return squareArea;
    }

    public void setSquareArea(int squareArea) {
        this.squareArea = squareArea;
    }

    public String getManager() {
        return manager;
    }

    public void setManager(String manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        String estateString = this.getId() + ", " + this.getCity() + ", " + this.getPostalcode() + ", "
                + this.getStreet() + ", " + this.getStreetNumber() + ", " + this.getSquareArea() + ", " + this.getManager();
        return estateString;
    }

    public void save() {
        try {
            Connection con = DbConnectionManager.getInstance().getConnection();

            String insertSQL = "INSERT INTO estate(city, postal_code, street, street_number, square_area, manager)" +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = con.prepareStatement(insertSQL,
                    Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, getCity());
            pstmt.setInt(2, getPostalcode());
            pstmt.setString(3, getStreet());
            pstmt.setString(4, getStreetNumber());
            pstmt.setInt(5, getSquareArea());
            pstmt.setString(6, getManager());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                setId(rs.getInt(1));
            }

            rs.close();
            pstmt.close();

            System.out.println("\nEstate [" + toString() + "] created.\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static Estate read(int id) {
        try {
            Connection con = DbConnectionManager.getInstance().getConnection();

            String selectSQL = "SELECT * FROM estate WHERE estate_id = ?";
            PreparedStatement pstmt = con.prepareStatement(selectSQL);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Estate estate = new Estate();
                estate.setCity(rs.getString("city"));
                estate.setPostalcode(rs.getInt("postal_code"));
                estate.setStreet(rs.getString("street"));
                estate.setStreetNumber(rs.getString("street_number"));
                estate.setSquareArea(rs.getInt("square_area"));
                estate.setManager(rs.getString("manager"));

                rs.close();
                pstmt.close();

                return estate;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void delete() {
        try {
            Connection con = DbConnectionManager.getInstance().getConnection();

            // Check if ID exists
            if (read(this.getId()) == null) {
                System.out.println("\nEstate [" + this.getId() + "] does not exist. Deletion not possible.\n");
                return;
            }
            Estate completeEstate = read(id);
            completeEstate.setId(id);

            String deleteSQL = "DELETE FROM estate WHERE estate_id = ?";
            PreparedStatement pstmt = con.prepareStatement(deleteSQL);

            pstmt.setInt(1, this.getId());
            pstmt.executeUpdate();
            pstmt.close();

            System.out.println("\nEstate [" + completeEstate.toString() + "] deleted.\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
