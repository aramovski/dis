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

    public static House read(int id) {
        try {
            Connection con = DbConnectionManager.getInstance().getConnection();

            String selectSQL = "SELECT * FROM house WHERE estate_id = ?";
            PreparedStatement pstmt = con.prepareStatement(selectSQL);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                House estate = new House();
                estate.setId(rs.getInt("estate_id"));
                estate.setCity(rs.getString("city"));
                estate.setPostalcode(rs.getInt("postal_code"));
                estate.setStreet(rs.getString("street"));
                estate.setStreetNumber(rs.getString("street_number"));
                estate.setSquareArea(rs.getInt("square_area"));
                estate.setManager(rs.getString("manager"));
                estate.setFloors(rs.getInt("floors"));
                estate.setPrice(rs.getString("price"));
                estate.setGarden(rs.getBoolean("garden"));

                rs.close();
                pstmt.close();

                return estate;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
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

    public void update(int estateId, House newEstate, String manager) {
        try {
            Connection con = DbConnectionManager.getInstance().getConnection();

            // Check if estate already exists
            if (read(estateId) == null) {
                System.out.println("\nHouse [" + estateId + "] does not exist. Update not possible.\n");
                return;
            }
            House oldEstate = read(estateId);

            // Check if manager manages estate
            if (!oldEstate.getManager().equals(manager)) {
                System.out.println("\nManager [" + manager + "] not allowed to update estate.\n");
                return;
            }

            String updateSQL = "UPDATE house SET city = ?, postal_code = ?, street = ?, street_number = ?," +
                    " square_area = ?, manager = ?, floors = ?, price = ?, garden = ? WHERE estate_id = ?";


            PreparedStatement pstmt = con.prepareStatement(updateSQL);

            pstmt.setString(1, newEstate.getCity());
            pstmt.setInt(2, newEstate.getPostalcode());
            pstmt.setString(3, newEstate.getStreet());
            pstmt.setString(4, newEstate.getStreetNumber());
            pstmt.setInt(5, newEstate.getSquareArea());
            pstmt.setString(6, newEstate.getManager());
            pstmt.setInt(7, newEstate.getFloors());
            pstmt.setString(8, newEstate.getPrice());
            pstmt.setBoolean(9, newEstate.isGarden());
            pstmt.setInt(10, estateId);
            pstmt.executeUpdate();
            pstmt.close();

            newEstate.setId(estateId);

            System.out.println("\nHouse [" + oldEstate.toString() + "] updated to [" + newEstate.toString() + "].\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
