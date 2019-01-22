/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package movies;

import com.microsoft.sqlserver.jdbc.SQLServerDataSource;
import com.microsoft.sqlserver.jdbc.SQLServerException;
import java.sql.Connection;

/**
 *
 * @author Lukas
 */
public class ConnectionManager {

    private SQLServerDataSource ds = new SQLServerDataSource();

    public ConnectionManager() {
        ds.setDatabaseName("MyMovies");
        ds.setUser("CS2018B_12");
        ds.setPassword("CS2018B_12");
        ds.setPortNumber(1433);
        ds.setServerName("10.176.111.31");
    }

    public Connection getConnection() throws SQLServerException {
        return ds.getConnection();
    }
}
