
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Client {

 private static final String SERVER_IP = "127.0.0.1";
 private static final int SERVER_PORT = 5555;

 public static void main(String[] args) throws IOException {
  Socket socket = new Socket(SERVER_IP, SERVER_PORT);
  BufferedReader input = new BufferedReader(new InputStreamReader(socket.getInputStream()));
  BufferedReader console = new BufferedReader(new InputStreamReader(System.in));
  PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

  while (true) {
   System.out.println(">");
   String command = console.readLine();
   if (command.equals("exit"))
    break;
   out.println(command);

   String serverResponse = input.readLine();
   System.out.println("Server Says: " + serverResponse);
  }
  socket.close();
  System.exit(0);
 }

}
