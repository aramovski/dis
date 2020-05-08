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

    public static Apartment read(int id) {
        try {
            Connection con = DbConnectionManager.getInstance().getConnection();

            String selectSQL = "SELECT * FROM apartment WHERE estate_id = ?";
            PreparedStatement pstmt = con.prepareStatement(selectSQL);
            pstmt.setInt(1, id);

            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                Apartment estate = new Apartment();
                estate.setId(rs.getInt("estate_id"));
                estate.setCity(rs.getString("city"));
                estate.setPostalcode(rs.getInt("postal_code"));
                estate.setStreet(rs.getString("street"));
                estate.setStreetNumber(rs.getString("street_number"));
                estate.setSquareArea(rs.getInt("square_area"));
                estate.setManager(rs.getString("manager"));
                estate.setFloor(rs.getInt("floor"));
                estate.setRent(rs.getString("rent"));
                estate.setRooms(rs.getString("rooms"));
                estate.setBalcony(rs.getBoolean("balcony"));
                estate.setKitchen(rs.getBoolean("kitchen"));

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

    public void update(int estateId, Apartment newEstate, String manager) {
        try {
            Connection con = DbConnectionManager.getInstance().getConnection();

            // Check if estate already exists
            if (read(estateId) == null) {
                System.out.println("\nApartment [" + estateId + "] does not exist. Update not possible.\n");
                return;
            }
            Apartment oldEstate = read(estateId);

            // Check if manager manages estate
            if (!oldEstate.getManager().equals(manager)) {
                System.out.println("\nManager [" + manager + "] not allowed to update estate.\n");
                return;
            }

            String updateSQL = "UPDATE apartment SET city = ?, postal_code = ?, street = ?, street_number = ?," +
                    " square_area = ?, manager = ?, floor = ?, rent = ?, rooms = ?, " +
                    "balcony = ?, kitchen = ? WHERE estate_id = ?";


            PreparedStatement pstmt = con.prepareStatement(updateSQL);

            pstmt.setString(1, newEstate.getCity());
            pstmt.setInt(2, newEstate.getPostalcode());
            pstmt.setString(3, newEstate.getStreet());
            pstmt.setString(4, newEstate.getStreetNumber());
            pstmt.setInt(5, newEstate.getSquareArea());
            pstmt.setString(6, newEstate.getManager());
            pstmt.setInt(7, newEstate.getFloor());
            pstmt.setString(8, newEstate.getRent());
            pstmt.setString(9, newEstate.getRooms());
            pstmt.setBoolean(10, newEstate.isBalcony());
            pstmt.setBoolean(11, newEstate.isKitchen());
            pstmt.setInt(12, estateId);
            pstmt.executeUpdate();
            pstmt.close();

            newEstate.setId(estateId);

            System.out.println("\nApartment [" + oldEstate.toString() + "] updated to [" + newEstate.toString() + "].\n");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
