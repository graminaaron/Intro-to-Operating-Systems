/**
 * An echo client. The client enters data to the server, and the
 * server echoes the data back to the client.
 */
import java.net.*;
import java.io.*;

//The EchoClient.java and the EchoServer.java are written by
//Aaron Garcia Student ID: 030556771. 

//This class listens to the QuoteServer class via a socket. It connects to 
//to the local server via port 6017. This class then communicates with the EchoServer class 
//by sending a message. It then awaits the servers response, 
//which is an echo of the same message
public class EchoClient
{
	public static void main(String[] args) 
	{
		try 
		{
			System.out.println("Beginning Connection to Echo Server: ");
			
			//make connection to server socket
			Socket socket = new Socket("localhost",6017);
			System.out.println("Connected!");
			PrintWriter pout = new PrintWriter(socket.getOutputStream(), true);
			InputStream input = socket.getInputStream();
		    //converts byte code from InputStreamReader to ASCII values 
		    BufferedReader in = new BufferedReader(new InputStreamReader(input));
		    
		    //takes in input from the user
		    BufferedReader sin = new BufferedReader(new InputStreamReader(System.in));
		    //user input variable
		    String qotd;
		    
		    System.out.println("Type anything to echo a response.");
			System.out.println("(Type \"ctrl+z\" to quit at any time..)");
		    //routes the user input to the server, and awaits the echoed response
		    //continues until user presses "ctrl+z"
		    while ( (qotd = sin.readLine()) != null)
		    {
		    	pout.println(qotd);
		    	System.out.println(in.readLine());
			}
		    System.out.println("Quitting...");
			// close the socket connection 
			socket.close();
		}
		//exception handling:
		catch (IOException ioe) 
		{
			System.err.println(ioe);
		}
	}
}

