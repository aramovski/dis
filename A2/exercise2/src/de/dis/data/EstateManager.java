package de.dis.data;

import de.dis.FormUtil;
import de.dis.Menu;

import static de.dis.Main.showEstateMenu;
import static de.dis.Main.showMainMenu;

public class EstateManager {

    public static void showEstateCreateMenu(String manager) {
        final int CREATE_ESTATE = 0;
        final int CREATE_APARTMENT = 1;
        final int CREATE_HOUSE = 2;
        final int BACK = 3;

        Menu estateMenu = new Menu("Create new Estate. Only [" + manager + "] can perform actions");
        estateMenu.addEntry("Create Estate", CREATE_ESTATE);
        estateMenu.addEntry("Create Apartment", CREATE_APARTMENT);
        estateMenu.addEntry("Create House", CREATE_HOUSE);
        estateMenu.addEntry("Back to Estate Menu", BACK);

        while (true) {
            int response = estateMenu.show();

            switch (response) {
                case CREATE_ESTATE:
                    createEstate(manager);
                    break;
                case CREATE_APARTMENT:
                    createApartment(manager);
                    break;
                case CREATE_HOUSE:
                    createHouse(manager);
                    break;
                case BACK:
                    showEstateMenu(manager);
                    return;
                default:
                    throw new IllegalStateException("Unexpected value: " + response);
            }
        }
    }

    public static void createEstate(String manager) {
        Estate estate = new Estate();

        estate.setCity(FormUtil.readString("City"));
        estate.setPostalcode(FormUtil.readInt("Postalcode"));
        estate.setStreet(FormUtil.readString("Street"));
        estate.setStreetNumber(FormUtil.readString("Street Number"));
        estate.setSquareArea(FormUtil.readInt("Square Area"));
        estate.setManager(manager);

        estate.save();
    }

    public static void createApartment(String manager) {
        Apartment apartment = new Apartment();

        apartment.setCity(FormUtil.readString("City"));
        apartment.setPostalcode(FormUtil.readInt("Postalcode"));
        apartment.setStreet(FormUtil.readString("Street"));
        apartment.setStreetNumber(FormUtil.readString("Street Number"));
        apartment.setSquareArea(FormUtil.readInt("Square Area"));
        apartment.setFloor(FormUtil.readInt("Floor"));
        apartment.setRent(FormUtil.readString("Rent"));
        apartment.setRooms(FormUtil.readString("Rooms"));
        apartment.setBalcony((FormUtil.readInt("Balcony (1 = yes)") == 1));
        apartment.setKitchen(FormUtil.readInt("Kitchen (1 = yes)") == 1);
        apartment.setManager(manager);

        apartment.save();
    }

    public static void createHouse(String manager) {
        House house = new House();

        house.setCity(FormUtil.readString("City"));
        house.setPostalcode(FormUtil.readInt("Postalcode"));
        house.setStreet(FormUtil.readString("Street"));
        house.setStreetNumber(FormUtil.readString("Street Number"));
        house.setSquareArea(FormUtil.readInt("Square Area"));
        house.setFloors(FormUtil.readInt("Floors"));
        house.setPrice(FormUtil.readString("Price"));
        house.setGarden(FormUtil.readInt("Garden (1 = yes)") == 1);
        house.setManager(manager);

        house.save();
    }
}
