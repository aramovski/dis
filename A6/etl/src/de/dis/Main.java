package de.dis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        Connection dbConnection = DbConnectionManager.getInstance().getConnection();

        SchemaManager schemaManager = new SchemaManager(dbConnection);
        schemaManager.createDimensions();
        schemaManager.createFactTable();

        ExtractManager extractManager = new ExtractManager(dbConnection);
        extractManager.extractProducts();
        extractManager.showProducts();

        extractManager.extractGeographies();
        extractManager.showGeographies();

        extractManager.extractTimes();
    }
}
