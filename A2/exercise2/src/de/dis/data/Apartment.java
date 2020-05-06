package de.dis.data;

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
}
