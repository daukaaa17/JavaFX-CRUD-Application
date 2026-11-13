package org.project.tasks.contactapp.dao;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataSourceFactory {

    /**
     * @return a connection to the Database inferred by the URL
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        Connection cnx = DriverManager.getConnection("jdbc:sqlite:identifier.sqlite");
        // Magically, java static analysis can ensure you that cnx is never null, so no need to check
        DatabaseMetaData meta = cnx.getMetaData();
        System.out.println("Connection to " + meta.getDriverName());
        return cnx;
    }
}
