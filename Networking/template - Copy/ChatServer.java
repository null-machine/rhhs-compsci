/* [ChatServer.java]
 * You will need to modify this so that received messages are broadcast to all clients
 * @author Mangat
 * @ version 1.0a
 */

//imports for network communication
import java.io.*;
import java.net.*;
import java.util.ArrayList;

class ChatServer {

  private static ArrayList clients;
  private static ServerSocket serverSock;// server socket for connection
  static Boolean running = true;  // controls if the server is accepting clients
  private int clientCount = 0;

  /** main
    * @param args parameters from command line
    */
  public static void main(String[] args) {
    clients = new ArrayList<ConnectionHandler>();
    new ChatServer().go();
  }

  /** go
    * Starts the server
    */
  public void go() {
    System.out.println("Waiting for client..");
    Socket client = null;

    try {
      serverSock = new ServerSocket(5535);
      //serverSock.setSoTimeout(15000);  //15 second timeout
      while(running) {  //this loops to accept multiple clients
        client = serverSock.accept();  //wait for connection
        System.out.println("Client connected");
        //Note: you might want to keep references to all clients if you plan to broadcast messages
        //Also: Queues are good tools to buffer incoming/outgoing messages
        ConnectionHandler ch = new ConnectionHandler(client, this, clientCount);
        clientCount++;
        clients.add(ch);
        Thread t = new Thread(ch); //create a thread for the new client and pass in the socket
        t.start(); //start the new thread
      }
    } catch(Exception e) {
      //System.out.println("Error accepting connection");
      try {
        client.close();
      } catch (Exception e1) {
        System.out.println("Failed to close socket");
      }
      System.exit(-1);
    }
  }
  public void sendToAll(String msg) {
    for(int i = 0; i < clients.size(); ++i) {
      //ConnectionHandler ch = clients.get(i);
      ((ConnectionHandler)clients.get(i)).sendMsg(msg);
    }
  }

  //***** Inner class - thread for client connection
  class ConnectionHandler implements Runnable {
    private PrintWriter output; //assign printwriter to network stream
    private BufferedReader input; //Stream for network input
    private Socket client;  //keeps track of the client socket
    private boolean running;
    private ChatServer server;

    private int id;

    /* ConnectionHandler
     * Constructor
     * @param the socket belonging to this client connection
     */
    ConnectionHandler(Socket s, ChatServer server, int id) {
      this.server = server;
      this.id = id;
      this.client = s;  //constructor assigns client to this
      try {  //assign all connections to client
        this.output = new PrintWriter(client.getOutputStream());
        InputStreamReader stream = new InputStreamReader(client.getInputStream());
        this.input = new BufferedReader(stream);
      } catch(IOException e) {
        e.printStackTrace();
      }
      running=true;
    } //end of constructor

    public void sendMsg(String msg) {
      output.println(msg); //echo the message back to the client ** This needs changing for multiple clients
      output.flush();
    }

    /* run
     * executed on start of thread
     */
    public void run() {

      // get a message from the client
      String msg = "";

      // get a message from the client
      while(running) {  // loop unit a message is received
        try {
          if (input.ready()) { //check for an incoming messge
            msg = input.readLine();  //get a message from the client
            server.sendToAll("#" + id + ": " + msg);
            System.out.println(msg);
          }
        } catch (IOException e) {
          System.out.println("Failed to receive msg from the client");
          e.printStackTrace();
        }
      }

      System.out.println("euhasntoehusnaoehusn");

      output.println("We got your message! Goodbye.");
      output.flush();

      try {
        input.close();
        output.close();
        client.close();
      } catch (Exception e) {
        System.out.println("Failed to close socket");
      }
    }
  }
}
