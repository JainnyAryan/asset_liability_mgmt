package org.ofss.alm.crm.utils;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class OracleDBConnection {

    public static Connection getConnection(){
        Connection conn = null;

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");       //loading the driver
            //creating the connection
            conn = DriverManager.getConnection
                    ("jdbc:oracle:thin:@0.0.0.0:1521:free","system","root");

        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        //returning the connection
        return conn;
    }
}
