package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DataConnection {

    private DataConnection(){}

    public static DataConnection SINGLETON = new DataConnection();

    public Connection connectJDBCToAWSEC2() {

        try {
            Class.forName("com.mysql.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            System.out.println("Missing MySQL JDBC Driver");
        }

        Connection connection = null;

        try {
            connection = DriverManager.
                    getConnection("jdbc:mysql://" + "ec2-18-188-40-6.us-east-2.compute.amazonaws.com" +
                            ":3306/ticketToRide", "remoteu", "password");
        } catch (SQLException e) {
            System.out.println("Connection Failed!:\n" + e.getMessage());
        }

        return connection;
    }
}
