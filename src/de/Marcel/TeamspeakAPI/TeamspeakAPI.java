package de.Marcel.TeamspeakAPI;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetSocketAddress;
import java.net.Socket;

public class TeamspeakAPI {
	private PrintWriter writer;
	private BufferedReader reader;
	
	private boolean logInConsole;
	
	private String address;
	int port = 10011;
	
	private TSAdminAccount tsAdminAccount;
	
	private boolean keepRunning;
	
	public TeamspeakAPI (String address, boolean logInConsole, TSAdminAccount tsAdminAccount, boolean keepRunning) {
		this.address = address;
		this.logInConsole = logInConsole;
		this.tsAdminAccount = tsAdminAccount;
		this.keepRunning = keepRunning;
	}
	
	public void start () throws Exception {
		//establish connection
		InetSocketAddress host = new InetSocketAddress(address, port);
		@SuppressWarnings("resource")
		Socket socket = new Socket();
		log("Connection...");
		socket.connect(host, port);
		log("Done!");
		log("Making streams...");
		DataOutputStream output = new DataOutputStream(socket.getOutputStream());
		DataInputStream input = new DataInputStream(socket.getInputStream());
		
		log("Done!");
		
		//create wirter and reader
		writer = new PrintWriter(output);
		reader = new BufferedReader(new InputStreamReader(input));
		
		for(int i = 0; i < 4; i++) {
			reader.readLine();
		}
		
		//send login command
		sendCommand(new TSCommand.LoginCommand(tsAdminAccount.getUsername(), tsAdminAccount.getPassword()));
		sendCommand(new TSCommand.UseCommand(1));
		
		//keep api from closing itself
		new Thread(new Runnable() {
			@Override
			public void run() {
				if(keepRunning) {
					while(keepRunning) {}
				} 
			}
		}).start();
	}
	
	/*
	 * find client id
	 * returns -1 if error
	 * otherwise returns id
	 */
	public int findClientID (String client) {
		sendCommand(new TSCommand.FindClientID(client));
		try {
			for(int i = 0; i < 4; i++) {
				reader.readLine();
			}
			
			String line = reader.readLine();
			log(line);
			if(line.startsWith("error")) {
				return -1;
			} else {
				return Integer.valueOf(line.substring(5, line.indexOf("client_nickname=") - 1));
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		return -1;
	}
	
	/*
	 * send message command
	 */
	public void sendMessage (String message) {
		sendCommand(new TSCommand.MessageCommand(message));
		try {
			log(reader.readLine());
			reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/*
	 * kick client by id
	 */
	public void kickClientByID(int id, String reason) {
		if(Math.signum(id) == 1.0f) {
			sendCommand(new TSCommand.KickClientByID(id, reason));
			try {
				log(reader.readLine());
				reader.readLine();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * send command to teamspeak server
	 */
	private void sendCommand (TSCommand c) {
		writeString(c.getCommandString());
	}
	
	/*
	 * write string to teamespeak server
	 */
	private void writeString (String string) {
		writer.println(string);
		writer.flush();
	}
	
	/*
	 * log
	 */
	private void log (String s) {
		if(logInConsole) {
			System.out.println(s);
		}
 	}
}
