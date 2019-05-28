package androidteam.cs340.tickettoride.Client;

import android.util.Log;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.ArrayType;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import androidteam.cs340.tickettoride.Shared.DestinationCard;
import androidteam.cs340.tickettoride.Shared.Game;
import androidteam.cs340.tickettoride.Shared.GameModel;
import androidteam.cs340.tickettoride.Shared.LobbyGameModel;
import androidteam.cs340.tickettoride.Shared.LobbyModel;
import androidteam.cs340.tickettoride.Shared.Message;
import androidteam.cs340.tickettoride.Shared.Player;
import androidteam.cs340.tickettoride.Shared.Result;
import androidteam.cs340.tickettoride.Shared.Route;

public class ClientCommunicator {

    public static ClientCommunicator SINGLETON = new ClientCommunicator();
    private ClientCommunicator() {}

    public Result send(String command) {

        String url_ = "http://ec2-18-224-234-208.us-east-2.compute.amazonaws.com:7000/execute";
        //String url_ = "http://192.168.1.198:7001/execute";

        Log.d("CLIENT_COMMUNICATOR", command);
        Log.d("CLIENT_COMMUNICATOR", url_);

        try {

            //Open connection
            HttpURLConnection result = null;
            try {
                URL url = new URL(url_);
                result = (HttpURLConnection)url.openConnection();
                result.setRequestMethod("POST");
                result.setDoOutput(true);

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            //Write out to body
            byte[] outByte = command.getBytes("UTF-8");
            assert result != null;
            OutputStream os = result.getOutputStream();
            os.write(outByte);
            os.close();

            //Send connection
            result.connect();
            Log.d("CLIENT_COMMUNICATOR", Integer.toString(result.getResponseCode()));

            if (result.getResponseCode() == HttpURLConnection.HTTP_OK) {

                //Get the response body
                InputStream in = result.getInputStream();
                String encoding = result.getContentEncoding();
                encoding = encoding == null ? "UTF-8" : encoding;
                String body = IOUtils.toString(in, encoding);
                System.out.println(body);

                return new Result(result.getResponseCode(), body, "");
            } else {
                return new Result(result.getResponseCode(), "", "Received a " + result.getResponseCode()+ " response");
            }


        }
        catch (Exception e) {
            Log.d("CLIENT_COMMUNICATOR", e.toString());
            e.printStackTrace();
            return new Result(400, "error", "Error in ClientCommunicator");
        }
    }

    //Test Cases:
    //Delete data for fresh start
    //add players to game until game is full and kick off start game inside server
    //check to make sure game is deleted from lobby
    //Add game into a gson serializer
    //Draw face down cards for a player
    //Send a message
    //Draw destination cards
    //Take some destination cards and return them to the deck
    /*public static void main(String[] args) {

        JsonObject root = new JsonObject();
        root.addProperty("command", "delete");
        Result deleteResult = ClientCommunicator.SINGLETON.send(root.toString());
        System.out.println("Status code of delete: " + deleteResult.getStatusCode());

        String gameID = "";
        String player1 = "test1";
        String player2 = "test2";
        String player3 = "test3";
        String player4 = "test4";
        Result resultCreateGame = ServerProxy.SINGLETON.createGame(player1, 4);
        System.out.println("Result body of createGame: " + resultCreateGame.getData());
        System.out.println("Status code of createGame: " + resultCreateGame.getStatusCode());

        gameID = resultCreateGame.getData();
        gameID = gameID.replace("\"", "");

        Result joinGamePlayer2 = ServerProxy.SINGLETON.joinGame(player2, gameID);
        System.out.println("Result body of joinGamePlayer2: " + joinGamePlayer2.getData());
        System.out.println("Status code of joinGamePlayer2: " + joinGamePlayer2.getStatusCode());

        Result joinGamePlayer3 = ServerProxy.SINGLETON.joinGame(player3, gameID);
        System.out.println("Result body of joinGamePlayer3: " + joinGamePlayer3.getData());
        System.out.println("Status code of joinGamePlayer3: " + joinGamePlayer3.getStatusCode());

        Result joinGamePlayer4 = ServerProxy.SINGLETON.joinGame(player4, gameID);
        System.out.println("Result body of joinGamePlayer4: " + joinGamePlayer4.getData());
        System.out.println("Status code of joinGamePlayer4: " + joinGamePlayer4.getStatusCode());

        Result lobby = ServerProxy.SINGLETON.lobby();
        System.out.println("Result body of Lobby: " + lobby.getData());
        System.out.println("Status code of Lobby: " + lobby.getStatusCode());

        Result game = null;
        game = ServerProxy.SINGLETON.game(gameID);
        System.out.println("Result body of Game: " + game.getData());
        System.out.println("Status code of Game: " + game.getStatusCode());

        Gson gson = new Gson();
        GameModel gameModel = null;
        gameModel = gson.fromJson(game.getData(), GameModel.class);

        List<Route> routes = gameModel.getAvailableRoutes();

        System.out.println("Size of down deck before draw: " + gameModel.getTrainCardDeck().getDownDeck().size());

        Result drawCard = null;
        drawCard = ServerProxy.SINGLETON.drawTrainCardFaceDown(gameID, player1);
        System.out.println("Result body of Draw1: " + drawCard.getData());
        System.out.println("Status code of Draw1: " + drawCard.getStatusCode());

        drawCard = ServerProxy.SINGLETON.drawTrainCardFaceDown(gameID, player1);
        System.out.println("Result body of Draw2: " + drawCard.getData());
        System.out.println("Status code of Draw2: " + drawCard.getStatusCode());

        drawCard = ServerProxy.SINGLETON.drawTrainCardFaceDown(gameID, player1);
        System.out.println("Result body of Draw3: " + drawCard.getData());
        System.out.println("Status code of Draw3: " + drawCard.getStatusCode());

        drawCard = ServerProxy.SINGLETON.drawTrainCardFaceDown(gameID, player1);
        System.out.println("Result body of Draw4: " + drawCard.getData());
        System.out.println("Status code of Draw4: " + drawCard.getStatusCode());

        drawCard = ServerProxy.SINGLETON.drawTrainCardFaceDown(gameID, player1);
        System.out.println("Result body of Draw5: " + drawCard.getData());
        System.out.println("Status code of Draw5: " + drawCard.getStatusCode());

        drawCard = ServerProxy.SINGLETON.drawTrainCardFaceDown(gameID, player1);
        drawCard = ServerProxy.SINGLETON.drawTrainCardFaceDown(gameID, player1);
        drawCard = ServerProxy.SINGLETON.drawTrainCardFaceDown(gameID, player1);
        drawCard = ServerProxy.SINGLETON.drawTrainCardFaceDown(gameID, player1);
        drawCard = ServerProxy.SINGLETON.drawTrainCardFaceDown(gameID, player1);
        drawCard = ServerProxy.SINGLETON.drawTrainCardFaceDown(gameID, player1);

        Message message = new Message(player1, "Test Message");

        Result sendMessage = ServerProxy.SINGLETON.sendMessage(gameID, player1, message);
        System.out.println("Result body of Send Message: " + sendMessage.getData());
        System.out.println("Status code of Send Message: " + sendMessage.getStatusCode());

        Result claimRoute = ServerProxy.SINGLETON.claimRoute(gameID, player1, routes.get(0));
        System.out.println("Result body of Claim Route:" + claimRoute.getData());
        System.out.println("Status Code of Claim Route:" + claimRoute.getStatusCode());

        Result drawDestinationCard = null;
        drawDestinationCard = ServerProxy.SINGLETON.drawDestinationCard(gameID, player1);
        System.out.println("Result body of draw destination card1:" + drawDestinationCard.getData());
        System.out.println("Status Code of draw destination card1:" + drawDestinationCard.getStatusCode());

        drawDestinationCard = ServerProxy.SINGLETON.drawDestinationCard(gameID, player1);
        System.out.println("Result body of draw destination card2:" + drawDestinationCard.getData());
        System.out.println("Status Code of draw destination card2:" + drawDestinationCard.getStatusCode());

        drawDestinationCard = ServerProxy.SINGLETON.drawDestinationCard(gameID, player1);
        System.out.println("Result body of draw destination card3:" + drawDestinationCard.getData());
        System.out.println("Status Code of draw destination card3:" + drawDestinationCard.getStatusCode());

        drawDestinationCard = ServerProxy.SINGLETON.drawDestinationCard(gameID, player1);
        System.out.println("Result body of draw destination card4:" + drawDestinationCard.getData());
        System.out.println("Status Code of draw destination card4:" + drawDestinationCard.getStatusCode());

        drawDestinationCard = ServerProxy.SINGLETON.drawDestinationCard(gameID, player1);
        System.out.println("Result body of draw destination card5:" + drawDestinationCard.getData());
        System.out.println("Status Code of draw destination card5:" + drawDestinationCard.getStatusCode());

        drawDestinationCard = ServerProxy.SINGLETON.drawDestinationCard(gameID, player1);
        System.out.println("Result body of draw destination card6:" + drawDestinationCard.getData());
        System.out.println("Status Code of draw destination card6:" + drawDestinationCard.getStatusCode());

        game = ServerProxy.SINGLETON.game(gameID);
        System.out.println("Result body of Game: " + game.getData());
        System.out.println("Status code of Game: " + game.getStatusCode());

        gameModel = gson.fromJson(game.getData(), GameModel.class);

        List<DestinationCard> playerDestinationHand = new ArrayList<>();

        for (Player player : gameModel.getPlayersList()) {
            if (player.getUID().equals(player1)) {
                playerDestinationHand.addAll(player.getDestinationHand());
            }
        }

        ArrayList<DestinationCard> testReturn = new ArrayList<>();
        int allowTwo = 0;
        for (DestinationCard destinationCard : playerDestinationHand) {
            System.out.println("Returning: " + destinationCard);
            testReturn.add(destinationCard);
            allowTwo++;
            if(allowTwo > 1) {
                break;
            }
        }

        Result returnDestinationCard = ServerProxy.SINGLETON.returnDestinationCard(gameID, player1, testReturn);
        System.out.println("Result body of return destination card: " + returnDestinationCard.getData());
        System.out.println("Status code of return destination card: " + returnDestinationCard.getStatusCode());

    }*/

}
