/**
 * Modified DateClient so that it requests a quote
 * from the quote server.
 */

import java.net.*;
import java.io.*;

//The QuoteClient.java and the QuoteServer.java are written by
//Sara Hamidi Student ID: 028780685. 

//This class listen to the QuoteServer class via a socket. It connects to 
//to the local server and listens on port 6017 the same that the QuoteServer
//is listening to make a connection with it. 
public class QuoteClient
{

  public static void main(String[] args) 
  {
     try {
       //make connection to server socket
       Socket sock = new Socket("localhost",6017);
       InputStream in = sock.getInputStream();

       //converts byte code from InputStreamReader to ASCII values 
       BufferedReader bin = new BufferedReader(new InputStreamReader(in));


       String line;

       //reads the quote from the socket while the string isn't null and
       //prints it
       while ( (line = bin.readLine()) != null)
         System.out.println(line);

       // close the socket connection 
       sock.close();
    }
    //prints the exception if the connection can't be made
    catch (IOException ioe) {
      System.err.println(ioe);
    }
}
}