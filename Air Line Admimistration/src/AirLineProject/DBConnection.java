//package AirLineProject;
//
//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.Statement;
//import java.sql.SQLException;
//
//public class DBConnection {
//    Connection con;
//    public Statement stm;
//
//    public DBConnection() {
//        try {
//            // Load MySQL JDBC Driver
//            Class.forName("com.mysql.cj.jdbc.Driver");
//
//            // Establish the connection
//            String url = "jdbc:mysql://127.0.0.1:3306/airlinemng";
//            String username = "root";
//            String password = "MySQL@786";
//            con = DriverManager.getConnection(url, username, password);
//
//            // Create a statement object
//            stm = con.createStatement();
//
//        } catch (ClassNotFoundException | SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    // Method to close the connection and statement
//    public void close() {
//        try {
//            if (stm != null) stm.close();
//            if (con != null) con.close();
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//    }
//}

package AirLineProject;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DBConnection {
    Connection con;
    Statement stm;

    public DBConnection() throws SQLException, ClassNotFoundException {
        try {
            // Load the MySQL JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Database connection details
            String url = "jdbc:mysql://127.0.0.1:3306/airlinemng";
            String username = "root";
            String password = "MySQL@786";

            // Establish the connection
            con = DriverManager.getConnection(url, username, password);

            // Create a statement object for executing SQL queries
            stm = con.createStatement();
        } catch (ClassNotFoundException | SQLException exp) {
            exp.printStackTrace();
            // Re-throwing the exception so it can be handled by the caller
            throw exp;
        }
    }

    // Method to close the connection and statement
    public void close() {
        try {
            if (stm != null) {
                stm.close();
            }
            if (con != null) {
                con.close();
            }
        } catch (SQLException exp) {
            exp.printStackTrace();
        }
    }
}

