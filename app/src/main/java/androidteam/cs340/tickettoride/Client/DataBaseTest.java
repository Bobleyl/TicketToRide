package androidteam.cs340.tickettoride.Client;
import java.sql.*;

import javax.sql.RowSet;

public class DataBaseTest {

    private DataBaseTest() {
    }

    public static DataBaseTest SINGLETON = new DataBaseTest();

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

    public boolean delete() {

        Connection connection = DataBaseTest.SINGLETON.connectJDBCToAWSEC2();
        Statement statement;
        boolean result = false;
        if (connection != null) {
            try {
                statement = connection.createStatement();
                String query = "DELETE FROM User;";
                result = statement.execute(query);

                statement.close();
                connection.close();
            } catch (SQLException e) {
                System.out.println("Error: " + e.toString());
                e.printStackTrace();
                result = false;
            }
        }
        return result;
    }

    public boolean registerUser(String username, String password) {

        Connection connection = DataBaseTest.SINGLETON.connectJDBCToAWSEC2();
        Statement statement;
        Statement statementFindUser;
        boolean result = false;
        boolean userExists = false;
        if (connection != null) {
            try {

                String upperUsername = username.toUpperCase();

                statementFindUser = connection.createStatement();
                String querySearchUser = "SELECT * FROM User WHERE USERNAME = ? AND PASSWORD = ?;";
                ResultSet rs = null;

                try (PreparedStatement stmt1 = connection.prepareStatement(querySearchUser)) {
                    stmt1.setString(1, upperUsername);
                    stmt1.setString(2, password);
                    rs = stmt1.executeQuery();
                    userExists = rs.next();
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                    userExists = true;
                }

                //If userExists = True then return false
                System.out.println(userExists);
                if (!userExists) {

                    statement = connection.createStatement();
                    String query = "INSERT INTO User Values(?, ?);";

                    try (PreparedStatement stmt = connection.prepareStatement(query)) {
                        stmt.setString(1, upperUsername);
                        stmt.setString(2, password);
                        stmt.execute();
                        result = true;
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }

                    statement.close();
                    connection.close();
                } else {
                    result = false;
                }

                statementFindUser.close();

            } catch (SQLException e) {
                System.out.println("Error: " + e.toString());
                result = false;
            }
        }
        return result;
    }

    public boolean checkUser(String username, String password) {

        Connection connection = DataBaseTest.SINGLETON.connectJDBCToAWSEC2();
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
                    rs.close();

                } catch(SQLException e) {
                    e.printStackTrace();
                }

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

    public static void main(String[] args) {
        System.out.println(DataBaseTest.SINGLETON.registerUser("brent123456", "kleinman"));
    }

}
