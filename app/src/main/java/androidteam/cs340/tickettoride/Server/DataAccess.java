package androidteam.cs340.tickettoride.Server;

import android.os.Build;
import android.support.annotation.RequiresApi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DataAccess {

    private DataAccess(){}

    public static DataAccess SINGLETON = new DataAccess();

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

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public boolean registerUser(String username, String password) {

        Connection connection = DataAccess.SINGLETON.connectJDBCToAWSEC2();
        Statement statement;
        boolean result = false;
        if (connection != null) {
            try {
                statement = connection.createStatement();

                String upperUsername = username.toUpperCase();

                String query = "INSERT INTO User Values(?, ?);";

                try (PreparedStatement stmt = connection.prepareStatement(query)) {
                    stmt.setString(1, upperUsername);
                    stmt.setString(2, password);
                    stmt.executeUpdate();
                } catch(SQLException e) {
                    e.printStackTrace();
                }

                statement.close();
                connection.close();
                result = true;

            } catch (SQLException e) {
                System.out.println("Error: " + e.toString());
                result = false;
            }
        }
        return result;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public boolean checkUser(String username, String password) {

        Connection connection = DataAccess.SINGLETON.connectJDBCToAWSEC2();
        boolean result = false;

        if (connection != null) {
            Statement statement = null;
            try {

                System.out.println("Creating statement...");
                statement = connection.createStatement();

                ResultSet rs = null;
                String UpperUsername = username.toUpperCase();

                String query = "SELECT * FROM User WHERE USERNAME = ? AND PASSWORD = ?;";
                try (PreparedStatement stmt = connection.prepareStatement(query)) {
                    stmt.setString(1, UpperUsername);
                    stmt.setString(2, password);
                    rs = stmt.executeQuery();
                    result = rs.next();

                } catch(SQLException e) {
                    e.printStackTrace();
                    result = false;
                }

                assert rs != null;
                rs.close();
                statement.close();
                connection.close();

                return result;

            } catch (SQLException e) {
                System.out.println("Error: " + e.toString());
            } finally {
                try {
                    if (statement != null) {
                        statement.close();
                    }
                } catch (SQLException ignored) { }
                try {
                    connection.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }

        } else {
            System.out.println("FAILURE! Failed to make connection!");
            result = false;
        }
        return result;
    }
}

