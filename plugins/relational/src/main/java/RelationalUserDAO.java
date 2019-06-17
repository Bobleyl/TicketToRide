import Shared.Colors;
import Shared.IUserDAO;

import java.sql.*;

public class RelationalUserDAO implements IUserDAO {

    private RelationalUserDAO(){}

    //private static final String CREATE_TABLE_SQL = "CREATE TABLE IF NOT EXISTS GAME (GAMEID TEXT, DELTA TEXT, SNAPSHOT TEXT)";

    public static RelationalUserDAO SINGLETON = new RelationalUserDAO();

    public boolean delete() {

        Connection connection = Colors.DataConnection.SINGLETON.connectJDBCToAWSEC2();
        Statement statement;
        boolean result = false;
        if (connection != null) {
            try {
                statement = connection.createStatement();
                String query = "DELETE FROM User;";
                statement.execute(query);

                statement.close();
                connection.close();
                result = true;
            } catch(SQLException e){
                System.out.println("Error: " + e.toString());
                e.printStackTrace();
            }
        }
        return result;
    }

    public boolean registerUser(String username, String password) {

        Connection connection = Colors.DataConnection.SINGLETON.connectJDBCToAWSEC2();
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

        Connection connection = Colors.DataConnection.SINGLETON.connectJDBCToAWSEC2();
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
}