package server;

import androidteam.cs340.tickettoride.Shared.GameModel;
import com.google.gson.Gson;

import java.sql.*;
import java.util.List;

public class GameDAO {

    private GameDAO(){}

    public static GameDAO SINGLETON = new GameDAO();

    public void storeDelta(String gameID, List<String> delta) {

        try {

            //If it exists create it, if not update it..

            Gson gson = new Gson();
            String deltas = gson.toJson(delta);

            Connection connection = DataConnection.SINGLETON.connectJDBCToAWSEC2();
            Statement statement;
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

    }

    public void storeGame(String gameID, GameModel gameModel) {

        try {

            //If it exists create it, if not update it..

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
                //Do some logic here to rebuild the game
            }

            statement.close();
            connection.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }

    }

}
