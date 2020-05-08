package de.dis.data;

import de.dis.FormUtil;

import java.sql.*;
import java.text.Normalizer;
import java.text.ParseException;

public class ContractManager {

    public static void ShowInsertPersonMenu() {
        String firstname = FormUtil.readString("First Name");
        String lastname = FormUtil.readString("Last Name");
        String address = FormUtil.readString("Address");

        new Person(firstname,lastname,address).save();
    }

    public static void ShowCreateTenancyContractMenu() {
        int estateID = FormUtil.readInt("Estate ID");
        int personID = FormUtil.readInt("Tenant ID");
        String startDate = FormUtil.readString("Start Date (Format DD.MM.YYYY)");
        String duration = FormUtil.readString("Duration");
        String addCosts = FormUtil.readString("Additional Costs");
        String contractDate = FormUtil.readString("Contract Date (Format DD.MM.YYYY)");
        String contractPlace = FormUtil.readString("Settlement Place");

        try {
            TenancyContract contract = new TenancyContract(estateID, personID, startDate, duration, addCosts, contractDate, contractPlace);
            contract.save();
        } catch (ParseException e) {
            System.out.println("Bad date format.");
        }
    }

    public static void ShowCreatePurchaseContractMenu() {
        int estateID = FormUtil.readInt("Estate ID");
        int personID = FormUtil.readInt("Buyer ID");
        String noInstallments = FormUtil.readString("Number of Installments");
        String interestRate = FormUtil.readString("Interest Rate");
        String contractDate = FormUtil.readString("Contract Date (Format DD.MM.YYYY)");
        String contractPlace = FormUtil.readString("Settlement Place");

        try {
            PurchaseContract contract = new PurchaseContract(estateID, personID, noInstallments, interestRate, contractDate, contractPlace);
            contract.save();
        } catch (ParseException e) {
            System.out.println("Bad date format.");
        }
    }

    public static void ShowListContractsMenu()
    {
        Connection con = DbConnectionManager.getInstance().getConnection();

        String selectSQL = "SELECT * from contract";
        try {
            PreparedStatement pstmt = con.prepareStatement(selectSQL);
            ResultSet rs = pstmt.executeQuery();

            ResultSetMetaData rsmd = rs.getMetaData();
            int columnsNumber = rsmd.getColumnCount();
            System.out.println("=====================================================");
            while (rs.next()) {
                for (int i = 1; i <= columnsNumber; i++) {
                    if (i > 1) System.out.print(",  ");
                    String columnValue = rs.getString(i);
                    System.out.print(columnValue + " " + rsmd.getColumnName(i));
                }
                System.out.println("");
            }
            System.out.println("=====================================================");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
