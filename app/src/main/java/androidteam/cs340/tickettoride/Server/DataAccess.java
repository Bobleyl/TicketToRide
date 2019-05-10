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
        Statement statement = null;
        if (connection != null) {
            try {
                statement = connection.createStatement();

                //String sql_drop_table = "DROP TABLE Users;";
                //String sql_create_table = "CREATE TABLE Users(USERNAME varchar(255) key, PASSWORD varchar(255));";
                //statement.execute(sql_drop_table);
                //statement.execute(sql_create_table);

                String sql_insert = "INSERT INTO Users Values('" + username + "', '" + password + "');";
                statement.execute(sql_insert);

                statement.close();
                connection.close();
                return true;

            } catch (SQLException e) {
                System.out.println("Error: " + e.toString());
                return false;
            }
        }
        return false;
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public boolean checkUser(String username, String password) {

        Connection connection = DataAccess.SINGLETON.connectJDBCToAWSEC2();

        if (connection != null) {
            Statement statement = null;
            try {

                System.out.println("Creating statement...");
                statement = connection.createStatement();

                ResultSet rs = null;
                String sql = "SELECT * FROM Users WHERE USERNAME = ? AND PASSWORD = ?;";
                String userName = null;

                try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                    stmt.setString(1, username);
                    stmt.setString(2, password);
                    rs = stmt.executeQuery();
                    if (rs.next()) {
                        userName = rs.getString("USERNAME");
                        System.out.println(userName);
                    }

                } catch (SQLException e) {
                    System.out.println(e.toString());
                }

                //STEP 5: Extract data from result set

                assert rs != null;
                rs.close();
                statement.close();
                connection.close();

                return userName != null;

            } catch (SQLException e) {
                System.out.println("Error: " + e.toString());
            } finally {
                try {
                    if (statement != null)
                        statement.close();
                } catch (SQLException ignored) {
                }
                try {
                    connection.close();
                } catch (SQLException se) {
                    se.printStackTrace();
                }
            }

        } else {
            System.out.println("FAILURE! Failed to make connection!");
            return false;
        }
        return false;
    }
}

