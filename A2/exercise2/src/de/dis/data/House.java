package de.dis.data;

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
}
