package server;

import com.google.gson.Gson;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GameDAO {

    private GameDAO(){}

    public static GameDAO SINGLETON = new GameDAO();

    public static void main(String[] args) {
        List<String> testRun = new ArrayList<>();
        //testRun.add("ok1");
        GameDAO.SINGLETON.storeDelta("test", testRun);
        GameDAO.SINGLETON.retrieveGames();
        //GameDAO.SINGLETON.deleteGames();
    }

    public void deleteGame(String gameID) {

        Connection connection = DataConnection.SINGLETON.connectJDBCToAWSEC2();
        Statement statement;

        try {

            statement = connection.createStatement();
            String query = "DELETE FROM GAME WHERE GAMEID = ?";

            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, gameID);
                stmt.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public void deleteGames() {

        Connection connection = DataConnection.SINGLETON.connectJDBCToAWSEC2();
        Statement statement;

        try {

            statement = connection.createStatement();
            String query = "DELETE FROM GAME";

            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public void storeDelta(String gameID, List<String> delta) {

        Connection connection = DataConnection.SINGLETON.connectJDBCToAWSEC2();
        Statement statement;

        boolean gameExists = true;
        if (connection != null) {
            try {

                String querySearchGame = "SELECT * FROM GAME WHERE GAMEID = ?;";
                try (PreparedStatement stmt1 = connection.prepareStatement(querySearchGame)) {
                    stmt1.setString(1, gameID);
                    try(ResultSet resultSet = stmt1.executeQuery()) {
                        gameExists = resultSet.next();
                    }
                } catch(SQLException se) {
                    se.printStackTrace();
                    gameExists = false;
                }

                //If userExists = True then return false
                System.out.println(gameExists);
                if (gameExists) {

                    try {

                        //If it exists create it, if not update it..

                        Gson gson = new Gson();
                        String deltas = gson.toJson(delta);

                        statement = connection.createStatement();
                        String query = "UPDATE GAME SET DELTA = ? WHERE GAMEID = ?";

                        try (PreparedStatement stmt = connection.prepareStatement(query)) {
                            stmt.setString(1, deltas);
                            stmt.setString(2, gameID);
                            stmt.execute();
                        } catch (SQLException e) {
                            e.printStackTrace();
                        }

                        statement.close();
                        connection.close();
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }
                } else {

                    try {

                        //If it exists create it, if not update it..

                        Gson gson = new Gson();
                        String deltas = gson.toJson(delta);

                        statement = connection.createStatement();
                        String query = "INSERT INTO GAME (GAMEID, DELTA) VALUES (?, ?);";

                        try (PreparedStatement stmt = connection.prepareStatement(query)) {
                            stmt.setString(1, gameID);
                            stmt.setString(2, gson.toJson(delta));
                            stmt.execute();
                        } catch (SQLException e) {
                            System.out.println(e.toString());
                        }

                        statement.close();
                        connection.close();
                    } catch (Exception e) {
                        System.out.println(e.toString());
                    }

                }
            } catch (Exception e) {
                System.out.println(e.toString());
            }
        }

    }

    public void storeGame(String gameID, GameModel gameModel) {

        try {

            Gson gson = new Gson();
            String games = gson.toJson(gameModel);

            Connection connection = DataConnection.SINGLETON.connectJDBCToAWSEC2();
            Statement statement;
            statement = connection.createStatement();
            String query = "UPDATE GAME SET SNAPSHOT = ? WHERE GAMEID = ?";

            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                stmt.setString(1, games);
                stmt.setString(2, gameID);
                stmt.execute();
            } catch (SQLException e) {
                e.printStackTrace();
            }

            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

    public void retrieveGames() {

        Connection connection = DataConnection.SINGLETON.connectJDBCToAWSEC2();

        String query = "SELECT * FROM GAME";

        try {
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(query);

            while (rs.next()) {
                System.out.println(rs.getString(1));
                System.out.println(rs.getString(2));
                System.out.println(rs.getString(3));
            }

            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

}
