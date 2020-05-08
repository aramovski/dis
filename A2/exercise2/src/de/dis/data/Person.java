package de.dis.data;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

public class Person {

    private String firstName;
    private String lastName;
    private String address;

    public Person(String firstName, String lastName, String address)
    {
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
    }

    @Override
    public String toString() {
        return firstName + " " + lastName;
    }

    public void save()
    {
        try{
            Connection con = DbConnectionManager.getInstance().getConnection();

            String insertSQL = "INSERT INTO person(first_name, last_name, address)" +
                    "VALUES (?, ?, ?)";

            PreparedStatement pstmt = con.prepareStatement(insertSQL,
                    Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1, firstName);
            pstmt.setString(2, lastName);
            pstmt.setString(3, address);

            pstmt.executeUpdate();
            pstmt.close();

            System.out.println(this.toString() + " has been saved to database.");
        }
        catch (Exception e)
        {
            System.out.println("Error while saving to database. Please check your inputs.");
        }
    }
}
