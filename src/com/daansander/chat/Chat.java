package com.daansander.chat;

import java.util.Scanner;

import com.daansander.chat.net.Server;
import com.daansander.chat.net.ServerClient;

public class Chat extends Thread {

	public static Chat chat;
	
	private Server server;
	private ServerClient socketClient;
	
	public Chat() {
		chat = this;
//		server = new Server(this);
//		server.start();
		socketClient = new ServerClient(this, "localhost");
		socketClient.start();
		
		start();
	}
	
	@Override
	public void run() {
		Scanner scanner = new Scanner(System.in);
		while (true) {
			String message = scanner.nextLine();
			
			socketClient.sendData(message.getBytes());
		}
	}
	
	public static void main(String[] args) {
		new Chat();
	}
	
}