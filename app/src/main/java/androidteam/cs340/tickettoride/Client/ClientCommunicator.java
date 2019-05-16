package androidteam.cs340.tickettoride.Client;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import androidteam.cs340.tickettoride.Client.ServerPoller.ParseResults;
import androidteam.cs340.tickettoride.Shared.Result;

public class ClientCommunicator {

    public static ClientCommunicator SINGLETON = new ClientCommunicator();
    private ClientCommunicator() {}

    public Result send(String command) {

        String url_ = "http://ec2-18-224-234-208.us-east-2.compute.amazonaws.com:7000/execute";

        System.out.println(command);
        System.out.println(url_);

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
            System.out.println(result.getResponseCode());

            //Get the response body
            InputStream in = result.getInputStream();
            String encoding = result.getContentEncoding();
            encoding = encoding == null ? "UTF-8" : encoding;
            String body = IOUtils.toString(in, encoding);
            System.out.println(body);

            if (result.getResponseCode() == HttpURLConnection.HTTP_OK) {
                return new Result(result.getResponseCode(), body, "");
            } else {
                return new Result(result.getResponseCode(), "", "Received a " + result.getResponseCode()+ " response");
            }


        }
        catch (Exception e) {
            e.printStackTrace();
            return new Result(400, "error", "Error in ClientCommunicator");
        }
    }

    public static void main(String[] args) {
        JsonObject root = new JsonObject();
        root.addProperty("command", "lobby");
        Result result = ClientCommunicator.SINGLETON.send(root.toString());
        JsonElement jsonElement = new JsonParser().parse(result.getData());
        JsonArray jsonArray = jsonElement.getAsJsonArray();
        for(JsonElement jsonElement1 : jsonArray) {
            JsonObject userObj = jsonElement1.getAsJsonObject();
            System.out.println(userObj.get("numPlayersToStart"));
            System.out.println(userObj.get("gameID"));
            JsonArray jsonArray1 = userObj.getAsJsonArray("playerIDs");
            for (JsonElement jsonElement2 : jsonArray1) {
                System.out.println(jsonElement2.getAsString());
            }
            //userObj.getAsString();
        }
    }

}
