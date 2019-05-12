package androidteam.cs340.tickettoride.Server;

import com.google.gson.Gson;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;

import androidteam.cs340.tickettoride.Shared.Command;
import androidteam.cs340.tickettoride.Shared.Result;
import androidteam.cs340.tickettoride.Shared.Serializer;

public class ServerCommunicator {

    private static HttpServer server;

    private void run() {
        try {
            server = HttpServer.create(new InetSocketAddress(8080), 12);
        } catch (IOException e) {
            System.out.println("Could not create HTTP server: " + e.getMessage());
            e.printStackTrace();
        }

        server.setExecutor(null);
        server.createContext("/execCommand", ExecCommandHandler);
        server.start();
        System.out.println("Server Started");
    }

    private HttpHandler ExecCommandHandler = new HttpHandler() {

        @Override
        public void handle(HttpExchange exchange) throws IOException {

            InputStream inputStream = exchange.getRequestBody();

            Command command = Serializer.SINGLETON.stringToCommand(Serializer.SINGLETON.readBody(inputStream));
            Result result = command.execute();

            exchange.sendResponseHeaders(HttpURLConnection.HTTP_OK, 0);

            OutputStream outputStream = exchange.getResponseBody();

            Gson gson = new Gson();
            OutputStreamWriter outputStreamWriter = new OutputStreamWriter(outputStream);
            outputStreamWriter.write(gson.toJson(result));
            outputStreamWriter.flush();
            outputStreamWriter.close();

        }
    };

    public static void main(String[] args) {
        new ServerCommunicator().run();
    }
}
