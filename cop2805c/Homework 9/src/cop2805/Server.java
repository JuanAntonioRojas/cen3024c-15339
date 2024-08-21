package cop2805;
import static cop2805.Values.*;

import java.io.*;
import java.net.*;
import org.json.JSONObject;

public class Server {
    public static void main(String[] args) {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is up, and listening on port " + PORT);

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("A client is connected!");

                new ClientHandler(socket).start();
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}

class ClientHandler extends Thread {
    private Socket socket;

    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try (BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             PrintWriter output = new PrintWriter(new OutputStreamWriter(socket.getOutputStream()), true)) {

            String jsonData = input.readLine();
            JSONObject json = new JSONObject(jsonData);

            String name = json.getString("name");
            String email = json.getString("email");
            String promo = json.getString("promo");
            String ageGroup = json.getString("ageGroup");
            int rating = json.getInt("rating");
            String feedback = json.getString("feedback");

            String response = String.format("Name: %s\nEmail: %s\nPromo: %s\nAge Group: %s\nRating: %d\nFeedback: %s",
                    name, email, promo, ageGroup, rating, feedback);

            output.println(response);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
