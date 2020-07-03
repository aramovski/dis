package de.dis;

import java.sql.*;

public class SchemaManager {

    private Connection dbConnection;

    public SchemaManager(Connection dbConnection) {
        this.dbConnection = dbConnection;
    }

    public void createDimensions() {
        System.out.println("Creating Product Dimension Table");
        createProductDimension();
        System.out.println("Creating Time Dimension Table");
        createTimeDimension();
        System.out.println("Creating Geography Dimension Table");
        createGeographyDimension();
    }

    private void createProductDimension() {
        String createSQL = "CREATE TABLE PRODUCT_DIMENSION"
                + "("
                + "id serial NOT NULL,"
                + "article_id integer NOT NULL,"
                + "article_name character varying(255) NOT NULL,"
                + "productgroup_id integer NOT NULL,"
                + "productgroup_name character varying(255) NOT NULL,"
                + "productfamily_id integer NOT NULL,"
                + "productfamily_name character varying(255) NOT NULL,"
                + "productcategory_id integer NOT NULL,"
                + "productcategory_name character varying(255) NOT NULL,"
                + "price double precision NOT NULL,"
                + "PRIMARY KEY (id)"
                + ")";

        executeCreateQuery(createSQL);
    }

    private void createTimeDimension() {
        String createSQL = "CREATE TABLE TIME_DIMENSION"
                + "("
                + "id serial NOT NULL,"
                + "day integer NOT NULL,"
                + "month integer NOT NULL,"
                + "year integer NOT NULL,"
                + "quarter integer NOT NULL,"
                + "PRIMARY KEY (id)"
                + ")";

        executeCreateQuery(createSQL);
    }

    private void createGeographyDimension() {
        String createSQL = "CREATE TABLE GEOGRAPHY_DIMENSION"
                + "("
                + "id serial NOT NULL,"
                + "shop_id integer NOT NULL,"
                + "shop_name character varying(255) NOT NULL,"
                + "city_id integer NOT NULL,"
                + "city_name character varying(255) NOT NULL,"
                + "region_id integer NOT NULL,"
                + "region_name character varying(255) NOT NULL,"
                + "country_id integer NOT NULL,"
                + "country_name character varying(255) NOT NULL,"
                + "PRIMARY KEY (id)"
                + ")";

        executeCreateQuery(createSQL);
    }

    public void createFactTable() {
        System.out.println("Creating Fact Table");

        String createSQL = "CREATE TABLE FACT_TABLE"
                + "("
                + "product_id integer NOT NULL,"
                + "time_id integer NOT NULL,"
                + "geography_id integer NOT NULL,"
                + "sold integer NOT NULL,"
                + "revenue numeric(10, 2) NOT NULL,"
                + "PRIMARY KEY (product_id, time_id, geography_id),"
                + "FOREIGN KEY (product_id) REFERENCES PRODUCT_DIMENSION(id),"
                + "FOREIGN KEY (time_id) REFERENCES TIME_DIMENSION(id),"
                + "FOREIGN KEY (geography_id) REFERENCES GEOGRAPHY_DIMENSION(id)"
                + ")";

        executeCreateQuery(createSQL);
    }

    private void executeCreateQuery(String query) {
        try {
            PreparedStatement preparedStatement = dbConnection.prepareStatement(query);
            preparedStatement.execute();
            preparedStatement.close();
            System.out.println("Executed Query [" + query + "]");
        } catch (SQLException e) {
            // e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }
}
