/**
 * msgClient.java
 *
 * PURPOSE:			A client that sends text messages to a server.
 *					The client can disconnect to the server by typing "logout" 
 *					(without quotations) in the command line.
 *					The server should be running in owl.cs.umanitoba.ca
 *
 * PLATFORM:		Linux (aviary machines)
 * TO COMPILE:		javac msgClient.java
 * TO RUN:			java msgClient
 *
 */

import java.net.*;
import java.io.*;

public class msgClient {

    public static void main(String[] args) {

		Socket sock = null;              // client's socket
		InetAddress addr = null;         // addr of server (local host for now)
		InputStreamReader instrm = null; // terminal input stream
		PrintWriter writer = null;	   // used to write to socket
		BufferedReader stdin = null;     // buffered version of instrm
		String serverHostname = "owl.cs.umanitoba.ca";

		System.out.println("\nClient starting. Messages will be sent to " + serverHostname + "\n");

		// create socket
		try {
			addr = InetAddress.getByName(serverHostname);
			//addr = InetAddress.getLocalHost();	//if server in local machine only
			sock = new Socket(addr,3109); // create client socket
		} catch (Exception e) {
			System.out.println("Creation of client's Socket failed.");
			System.exit(1);
		}

		// set up terminal input and socket output streams
		try {
			instrm = new InputStreamReader(System.in);
			stdin = new BufferedReader(instrm);
			writer = new PrintWriter(sock.getOutputStream(), true);
		} catch (Exception e) {
			System.out.println("Socket output stream failed.");
			System.exit(1);
		}

		String msg;
		boolean isDone = false;
		try{
			while((msg = stdin.readLine()) != null && (msg.equals("logout") == false)){
				if(msg.length() <= 128){
					writer.println(msg);	//write to the socket
				}
				else{
					System.out.println("<Message must be 128 characters or less. Message was not sent.>");
				}
			}
			if(msg != null && msg.equals("logout")){
				writer.println(msg);
			}
			isDone = true;
		}
		catch(IOException e){
			System.out.println("Error in readLine()");
			System.exit(1);
		}

		// close the streams and socket
		if(isDone){
			try {
				instrm.close();
				stdin.close();
				writer.close();
				sock.close();
			} catch (Exception e) {
				System.out.println("Client couldn't close socket.");
				System.exit(1);
			}

			System.out.println("\nClient finished.\n");
		}
    }
}
