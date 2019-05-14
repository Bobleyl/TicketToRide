package androidteam.cs340.tickettoride.Client;

import java.io.IOException;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

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

            if (result.getResponseCode() == HttpURLConnection.HTTP_NO_CONTENT) {
                return new Result(result.getResponseCode(), "success", "");
            } else {
                return new Result(result.getResponseCode(), "error", "Received a " + result.getResponseCode()+ " response");
            }


        }
        catch (Exception e) {
            e.printStackTrace();
            return new Result(400, "error", "Error in ClientCommunicator");
        }
    }

}
