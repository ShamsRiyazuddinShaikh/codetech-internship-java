import java.io.*;
import java.net.*;

public class ChatClient {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;

    public static void main(String[] args) {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             BufferedReader userInput = new BufferedReader(new InputStreamReader(System.in));
             PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            // Start a thread to listen for messages from the server
            Thread listenerThread = new Thread(new ServerListener(in));
            listenerThread.start();

            String message;
            while ((message = userInput.readLine()) != null) {
                out.println(message);
            }
        } catch (IOException e) {
            System.err.println("Error in client: " + e.getMessage());
        }
    }

    // Listens for messages from the server and prints them to the console
    private static class ServerListener implements Runnable {
        private BufferedReader in;

        public ServerListener(BufferedReader in) {
            this.in = in;
        }

        public void run() {
            try {
                String message;
                while ((message = in.readLine()) != null) {
                    System.out.println("Server: " + message);
                }
            } catch (IOException e) {
                System.err.println("Error reading from server: " + e.getMessage());
            }
        }
    }
}
