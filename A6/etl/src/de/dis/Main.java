package de.dis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        System.out.println("Hello");

        Connection con = DbConnectionManager.getInstance().getConnection();

    }
}
