import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ClientHandler implements Runnable {
 private Socket client;
 // private String name;
 private BufferedReader in;
 private PrintWriter out;
 // private boolean isRunning;

 public ClientHandler(Socket clientSocket) throws IOException {
  this.client = clientSocket;
  in = new BufferedReader(new InputStreamReader(client.getInputStream()));
  out = new PrintWriter(client.getOutputStream(), true);

  // this.isRunning = true;
 }

 @Override
 public void run() {
  try {
   while (true) {
    String request = in.readLine();
    if (request.equals("name")) {
     out.println(Server.getRandomName());
    } else {
     out.println("Type your name");
    }

   }
  } catch (IOException e) {
   e.printStackTrace();
   System.out.println("Client disconnected");

  } finally {
   out.close();
   try {
    in.close();
   } catch (IOException e) {
    e.printStackTrace();
   }
  }

 }

}
