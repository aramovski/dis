package de.dis.data;

import de.dis.FormUtil;

import java.sql.*;

public class Apartment extends Estate {

    private int floor;
    private String rent;
    private String rooms;
    private boolean balcony;
    private boolean kitchen;

    public int getFloor() {
        return floor;
    }

    public void setFloor(int floor) {
        this.floor = floor;
    }

    public String getRent() {
        return rent;
    }

    public void setRent(String rent) {
        this.rent = rent;
    }

    public String getRooms() {
        return rooms;
    }

    public void setRooms(String rooms) {
        this.rooms = rooms;
    }

    public boolean isBalcony() {
        return balcony;
    }

    public void setBalcony(boolean balcony) {
        this.balcony = balcony;
    }

    public boolean isKitchen() {
        return kitchen;
    }

    public void setKitchen(boolean kitchen) {
        this.kitchen = kitchen;
    }

    @Override
    public String toString() {
        String apartmentString = ", " + this.getFloor() + ", " + this.getRent() + ", " + this.getRooms() + ", "
                + this.isBalcony() + ", " + this.isKitchen();
        return super.toString() + apartmentString;
    }

    public void save() {
        try {
            Connection con = DbConnectionManager.getInstance().getConnection();

            String insertSQL = "INSERT INTO apartment(city, postal_code, street, street_number, square_area," +
                    "manager, floor, rent, rooms, balcony, kitchen)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = con.prepareStatement(insertSQL,
                    Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, getCity());
            pstmt.setInt(2, getPostalcode());
            pstmt.setString(3, getStreet());
            pstmt.setString(4, getStreetNumber());
            pstmt.setInt(5, getSquareArea());
            pstmt.setString(6, getManager());
            pstmt.setInt(7, getFloor());
            pstmt.setString(8, getRent());
            pstmt.setString(9, getRooms());
            pstmt.setBoolean(10, isBalcony());
            pstmt.setBoolean(11, isKitchen());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                setId(rs.getInt(1));
            }

            rs.close();
            pstmt.close();

            System.out.println("\nApartment [" + toString() + "] created.\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
