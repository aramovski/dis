package de.dis.data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Contract {
    protected int estateID;
    protected int personID;

    protected int contractNumber;
    protected Date date;
    protected String place;

    public Contract(int estateID, int personID, String contractDate, String contractPlace) throws ParseException {
        this.estateID = estateID;
        this.personID = personID;
        this.date = new SimpleDateFormat("dd.MM.yyyy").parse(contractDate);
        this.place = contractPlace;
    }
}
