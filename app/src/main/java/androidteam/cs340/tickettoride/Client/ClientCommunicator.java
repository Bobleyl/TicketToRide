package androidteam.cs340.tickettoride.Client;

import com.google.gson.Gson;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import androidteam.cs340.tickettoride.Shared.GenericCommand;
import androidteam.cs340.tickettoride.Shared.Result;
import androidteam.cs340.tickettoride.Shared.Serializer;
import androidteam.cs340.tickettoride.Shared.Command;

public class ClientCommunicator {

    public static Result send(String url_, Command command) {

        String jsonInString = Serializer.SINGLETON.commandToString(command);

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
            byte[] outByte = jsonInString.getBytes("UTF-8");
            assert result != null;
            OutputStream os = result.getOutputStream();
            os.write(outByte);
            os.close();

            //Send connection
            result.connect();

            if (result.getResponseCode() == HttpURLConnection.HTTP_OK) {
                String json = Serializer.SINGLETON.readBody(result.getInputStream());
                Gson gson = new Gson();
                return(gson.fromJson(json, Result.class));
            } else {
                return new Result(false, "error", null);
            }

        }
        catch (Exception e) {
            e.printStackTrace();
            return new Result(false, "error", "Error in ClientCommunicator");
        }
    }

    /**
     * Uncomment this main method to see how this works...
     * @param args
     */
   /* public static void main(String[] args) {

        Command register = new GenericCommand("androidteam.cs340.tickettoride.Server.ServerFacade",
                "login",
                new String[]{ "java.lang.String","java.lang.String"},
                new Object[] { "brent", "kleinman" });

        Result result = send("http://localhost:8080/execCommand", register);
        System.out.println(result.getData());
    }*/

}
