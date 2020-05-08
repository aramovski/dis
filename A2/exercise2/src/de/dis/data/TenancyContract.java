package de.dis.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TenancyContract extends Contract {
    private Date startDate;
    private String duration;
    private String additionalCosts;

    public TenancyContract(int estateID, int personID, String startDate, String duration, String addCosts, String contractDate, String contractPlace) throws ParseException {
        super(estateID, personID, contractDate, contractPlace);
        this.startDate = new SimpleDateFormat("dd.MM.yyyy").parse(startDate);
        this.duration = duration;
        this.additionalCosts = addCosts;
    }

    public void save() {
        try {
            Connection con = DbConnectionManager.getInstance().getConnection();
            String insertSQL = "INSERT INTO tenancy_contract(contract_date, place, start_date, duration, additional_costs, person_id, apartment_id)" +
                    "VALUES (?, ?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = con.prepareStatement(insertSQL,
                    Statement.RETURN_GENERATED_KEYS);

            pstmt.setDate(1, new java.sql.Date(super.date.getTime()));
            pstmt.setString(2, super.place);
            pstmt.setDate(3, new java.sql.Date(startDate.getTime()));
            pstmt.setString(4, duration);
            pstmt.setString(5, additionalCosts);
            pstmt.setInt(6, super.personID);
            pstmt.setInt(7, super.estateID);

            pstmt.execute();
            pstmt.close();

            System.out.println("Contract has been saved to database.");
        }
        catch (Exception e)
        {
            System.out.println("Could not save to database. Most likely the estate or person IDs are not valid.");
        }
    }
}
