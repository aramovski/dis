package de.dis;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {

        // Precondition: SQL script which creates tables articles, etc. has to be executed first

        Connection dbConnection = DbConnectionManager.getInstance().getConnection();

        // Create empty Star Schema
        SchemaManager schemaManager = new SchemaManager(dbConnection);
        schemaManager.createDimensions();
        schemaManager.createFactTable();

        // Extract Data from DB and csv
        System.out.println("\n############# EXTRACTION #############\n");
        ExtractManager extractManager = new ExtractManager(dbConnection);
        extractManager.extractProducts();
        extractManager.showProducts();
        extractManager.extractGeographies();
        extractManager.showGeographies();
        extractManager.extractTimes();
        extractManager.showTimes();

        // TODO: extract sold and revenue from csv

        // Data is now extracted and stored in extract manager
        // Load this data into database
        System.out.println("\n############# LOADING #############\n");
        LoadManager loadManager = new LoadManager(dbConnection, extractManager);
        loadManager.loadProducts();
        loadManager.loadGeographies();
        loadManager.loadTimes();

        //TODO create fact table.  revenue and sold has to be connected with productId, geography id and time id
    }
}
