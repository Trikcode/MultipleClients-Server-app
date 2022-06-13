
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
  private static final int PORT = 5555;
  private static String[] names = { "Alice", "Bob", "Charlie", "Dave", "Eve", "Frank", "George", "Harry" };
  private static String[] messages = { "Hi", "Hello", "How are you?", "I am fine", "Thank you", "Bye", "See you",
      "Good bye" };
  private static ArrayList<ClientHandler> clients = new ArrayList<ClientHandler>();
  private static ExecutorService executor = Executors.newFixedThreadPool(10);

  public static void main(String[] args) throws IOException {
    try (ServerSocket listener = new ServerSocket(PORT)) {
      while (true) {
        System.out.println("Server waiting for client connection on port " + PORT);
        Socket client = listener.accept();
        System.out.println("Server connected to client ");
        ClientHandler handler = new ClientHandler(client);
        clients.add(handler);
        executor.execute(handler);
      }
    }

  }

  public static String getRandomName() {
    String name = names[(int) (Math.random() * names.length)];
    String message = messages[(int) (Math.random() * messages.length)];
    return name + ": " + message;
  }
}