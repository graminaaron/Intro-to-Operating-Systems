/**
 * An echo server listening on port 6007. This server reads from the client
 * and echoes back the result. 
 */

import java.net.*;
import java.io.*;

//The EchoClient.java and the EchoServer.java are written by
//Aaron Garcia Student ID: 030556771. 

//This class communicates with the EchoClient class via a socket. It receives a message over
//a connection on port 6017 and echoes it back to the client

public class EchoServer
{
	public static void main(String[] args) 
	{
		try 
		{
			//establish socket
			ServerSocket server = new ServerSocket(6017);
			
			//now listen for connections
			while(!(server.isClosed())) //repeat is server is still open
			{
				//wait for connection
				Socket client = server.accept();
				//connection successful? proceed forward
				
				//receive msg
				InputStream input = client.getInputStream();
			    //converts byte code from InputStreamReader to ASCII values 
			    BufferedReader in = new BufferedReader(new InputStreamReader(input));
			    
			    //return msg
			    PrintWriter pout = new PrintWriter(client.getOutputStream(), true);
			    
			    //client input
			    String echo;
			    
			    //reads the quote from the socket while the string isn't null and
				//prints it
			    while ( (echo = in.readLine()) != null)
				{
			    	//write the quote to the socket
				    pout.println("Server: " + echo);
				    //System.out.println(echo); //Debug
				}
			    //clean up by closing io and socket
			    in.close();
			    input.close();
			    pout.close();
	            client.close();
		        //If you want the server to remain open, comment out the below line
	            server.close();
			}
		}
		//exception handling:
		catch (IOException ioe) 
		{
			System.err.println(ioe);
		}
		
	}
}
