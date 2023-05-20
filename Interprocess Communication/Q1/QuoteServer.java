import java.net.*;
import java.io.*;

//The QuoteClient.java and the QuoteServer.java are written by
//Sara Hamidi Student ID: 028780685. 

//This class communicates with the QuoteClient class via a socket. It listens for 
//connections on port 6017.
public class QuoteServer
{
    public static void main(String[] args) {

        try {

            ServerSocket sock = new ServerSocket(6017);

            // now listen for connections
            while (true) 
            {
                Socket client = sock.accept();
                PrintWriter pout = new PrintWriter(client.getOutputStream(), true);

                // write the quote to the socket
                pout.println("Life is a long lesson in humility.");

                // close the socket or connection w this client and resume
                // listening for a connection from another client single client
                client.close();

            }
        }
        //if a connection can't be made to the port, an exception is thrown.
        catch (IOException ioe) {
            System.err.println(ioe);
        }
    }

 }
