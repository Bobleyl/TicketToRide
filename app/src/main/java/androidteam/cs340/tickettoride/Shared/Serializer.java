package androidteam.cs340.tickettoride.Shared;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class Serializer {

    private static Gson gson = new Gson();

    private Serializer() {}

    public static Serializer SINGLETON = new Serializer();

    public String commandToString(Command command) {
        String result = gson.toJson(command);
        return result;
    }

    public Command stringToCommand(String conversionString) {
        Command command = gson.fromJson(conversionString, GenericCommand.class);
        return command;
    }

    public String readBody(InputStream inputStream) throws IOException {

        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        BufferedReader br = new BufferedReader(inputStreamReader);
        int b;
        StringBuilder sb = new StringBuilder();
        while ((b = br.read()) != -1) {
            sb.append((char) b);
        }
        br.close();
        inputStreamReader.close();
        return sb.toString();
    }

}
