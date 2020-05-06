package de.dis.data;

import java.sql.*;

public class House extends Estate {

    private int floors;
    private String price;
    private boolean garden;

    public int getFloors() {
        return floors;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public boolean isGarden() {
        return garden;
    }

    public void setGarden(boolean garden) {
        this.garden = garden;
    }

    @Override
    public String toString() {
        String houseString = ", " + this.getFloors() + ", " + this.getPrice() + ", " + this.isGarden();
        return super.toString() + houseString;
    }

    public void save() {
        try {
            Connection con = DbConnectionManager.getInstance().getConnection();

            String insertSQL = "INSERT INTO house(city, postal_code, street, street_number, square_area," +
                    "manager, floors, price, garden)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = con.prepareStatement(insertSQL,
                    Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, getCity());
            pstmt.setInt(2, getPostalcode());
            pstmt.setString(3, getStreet());
            pstmt.setString(4, getStreetNumber());
            pstmt.setInt(5, getSquareArea());
            pstmt.setString(6, getManager());
            pstmt.setInt(7, getFloors());
            pstmt.setString(8, getPrice());
            pstmt.setBoolean(9, isGarden());

            pstmt.executeUpdate();

            ResultSet rs = pstmt.getGeneratedKeys();
            if (rs.next()) {
                setId(rs.getInt(1));
            }

            rs.close();
            pstmt.close();

            System.out.println("\nHouse [" + toString() + "] created.\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
