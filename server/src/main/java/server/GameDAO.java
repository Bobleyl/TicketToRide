package server;

import Shared.*;
import com.google.gson.Gson;

import java.lang.reflect.Constructor;
import java.sql.*;
import java.util.*;

public class GameDAO {

    private GameDAO(){}

    public static GameDAO SINGLETON = new GameDAO();

    public static void main(String[] args) {
        //List<String> testRun = new ArrayList<>();
        //testRun.add("ok1");
        //testRun.add("ok2");
        //GameDAO.SINGLETON.storeDelta("test", testRun);
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

    public void storeGame(String gameID) {

        GameModel gameModel = null;

        for(GameModel game : GameList.SINGLETON.getGames()) {
            if (game.getGameID().equals(gameID)) {
                gameModel = game;
            }
        }


        if (gameModel != null) {

            boolean gameExists = true;
            Connection connection = DataConnection.SINGLETON.connectJDBCToAWSEC2();

            if (connection != null) {
                try {

                    String querySearchGame = "SELECT * FROM GAME WHERE GAMEID = ?;";
                    try (PreparedStatement stmt1 = connection.prepareStatement(querySearchGame)) {
                        stmt1.setString(1, gameID);
                        try (ResultSet resultSet = stmt1.executeQuery()) {
                            gameExists = resultSet.next();
                        }
                    } catch (SQLException se) {
                        se.printStackTrace();
                        gameExists = false;
                    }

                    //If userExists = True then return false
                    System.out.println(gameExists);
                    Statement statement;
                    if (gameExists) {

                        try {

                            Gson gson = new Gson();
                            String games = gson.toJson(gameModel);


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
                    } else {

                        try {

                            //If it exists create it, if not update it..

                            Gson gson = new Gson();
                            String gameModelString = gson.toJson(gameModel);

                            statement = connection.createStatement();
                            String query = "INSERT INTO GAME (GAMEID, SNAPSHOT) VALUES (?, ?);";

                            try (PreparedStatement stmt = connection.prepareStatement(query)) {
                                stmt.setString(1, gameID);
                                stmt.setString(2, gameModelString);
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

    }

    public void retrieveGames() {

        Connection connection = DataConnection.SINGLETON.connectJDBCToAWSEC2();

        String query = "SELECT * FROM GAME";

        try {
            Statement statement = connection.createStatement();

            ResultSet rs = statement.executeQuery(query);

            Gson gson = new Gson();

            while (rs.next()) {
                System.out.println(rs.getString(1));
                List<String> deltas = gson.fromJson(rs.getString(2), List.class);
                GameModel gameModel = gson.fromJson(rs.getString(3), GameModel.class);
                GameList.SINGLETON.addGame(gameModel);

                for (String s : deltas) {
                    HashMap<String, Object> dto = gson.fromJson(s, HashMap.class);
                    String commandType = (String)dto.get("command");
                    commandType = commandType.substring(0, 1).toUpperCase() + commandType.substring(1);
                    HashMap<String, Object> values = (HashMap)dto.get("values");

                    Class<?> cl = Class.forName("server." + commandType + "Command");

                    Constructor<?> constructor = cl.getConstructor(HashMap.class);

                    CommandInterface command = (CommandInterface)constructor.newInstance(values);

                    command.execute();
                }

                System.out.println();
            }

            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

}

