package de.dis.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.text.ParseException;

public class PurchaseContract extends Contract {
    private String noInstallments;
    private String interestRate;

    public PurchaseContract(int estateID, int personID, String noInstallments, String interestRate, String contractDate, String contractPlace) throws ParseException {
        super(estateID,personID,contractDate,contractPlace);
        this.noInstallments = noInstallments;
        this.interestRate = interestRate;
    }


    public void save() {
        try {
            Connection con = DbConnectionManager.getInstance().getConnection();
            String insertSQL = "INSERT INTO purchase_contract(contract_date, place, installment_amount, intrest_rate, person_id, apartment_id)" +
                    "VALUES (?, ?, ?, ?, ?, ?)";

            PreparedStatement pstmt = con.prepareStatement(insertSQL,
                    Statement.RETURN_GENERATED_KEYS);

            pstmt.setDate(1, new java.sql.Date(super.date.getTime()));
            pstmt.setString(2, super.place);
            pstmt.setString(3, noInstallments);
            pstmt.setString(4, interestRate);
            pstmt.setInt(5, super.personID);
            pstmt.setInt(6, super.estateID);

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
