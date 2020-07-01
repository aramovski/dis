package de.dis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        Connection dbConnection = DbConnectionManager.getInstance().getConnection();
        SchemaManager schemaManager = new SchemaManager(dbConnection);

        schemaManager.createProductDimension();
        schemaManager.createGeographyDimension();
        schemaManager.createTimeDimension();
        schemaManager.createFactTable();
    }
}
