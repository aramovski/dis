package de.dis.entities;

public class Geography {

    private int id;
    private int shopId;
    private String shopName;
    private int cityId;
    private String cityName;
    private int regionId;
    private String regionName;
    private int countryId;
    private String countryName;

    public Geography() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getShopId() {
        return shopId;
    }

    public void setShopId(int shopId) {
        this.shopId = shopId;
    }

    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    public int getCityId() {
        return cityId;
    }

    public void setCityId(int cityId) {
        this.cityId = cityId;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public int getRegionId() {
        return regionId;
    }

    public void setRegionId(int regionId) {
        this.regionId = regionId;
    }

    public String getRegionName() {
        return regionName;
    }

    public void setRegionName(String regionName) {
        this.regionName = regionName;
    }

    public int getCountryId() {
        return countryId;
    }

    public void setCountryId(int countryId) {
        this.countryId = countryId;
    }

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    @Override
    public String toString() {
        String string =  "Shop ID: " + this.getShopId() + "\n"
                + "Shop Name: " + this.getShopName() + "\n"
                + "City ID: " + this.getCityId() + "\n"
                + "City Name: " + this.getCityName() + "\n"
                + "Region ID: " + this.getRegionId() + "\n"
                + "Region Name: " + this.getRegionName() + "\n"
                + "Country ID: " + this.getCountryId() + "\n"
                + "Country Name: " + this.getCountryName() + "\n";
        return string;
    }
}
