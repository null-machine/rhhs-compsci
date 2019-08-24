/* [SillyServer.java]
 * Description: This is an example of a chat server.
 * The program  waits for a client and accepts a message.
 * It then responds to the message and quits
 * @author Mangat
 * @version 1.0a
 */

//imports for network communication
import java.io.*;
import java.net.*;

class SillyServer {

  ServerSocket serverSock;// server socket for connection
  PrintWriter output; //printwriter for network output
  BufferedReader input; //reader for network stream
  boolean running = true;
  /** Main
    * @param args parameters from command line
    */
  public static void main(String[] args) {
    new SillyServer().go(); //start the server
  }

  /** Go
    * Starts the server
    */
  public void go() {
        System.out.println("Waiting for a client connection..");

    try {
      serverSock = new ServerSocket(5530);  //assigns an port to the server
      Socket client = serverSock.accept();  //wait for connection
      output = new PrintWriter(client.getOutputStream());  //assign printwriter to network stream
      InputStreamReader stream = new InputStreamReader(client.getInputStream());
      input = new BufferedReader(stream); //Stream for network input
    }
    catch(Exception e) {
      System.out.println("Error accepting connection");
      e.printStackTrace();
    }

    System.out.println("Client connected");


    //Get a message from the client
    String msg;

    while(running) {
      try {
        if (input.ready()) { //check for an incoming messge
          msg = input.readLine();  //read the message
          System.out.println("msg from client: " + msg);
           running=false; //stop receiving messages
        }
        }catch (IOException e) {
          System.out.println("Failed to receive msg from the server");
          e.printStackTrace();
        }
      }

    //Send a message to the client
    output.println("I received your message. Goodbye!");
    output.flush();  //flush (very important)


    //close the socket
    try {
      input.close();
      output.close();
      serverSock.close();
    }catch (Exception e) {
      System.out.println("Failed to close socket");
    }
  }

}
