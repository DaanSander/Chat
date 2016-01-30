package com.daansander.chat;

import java.net.InetAddress;

public class Client {

	public String username;
	public InetAddress ipAddress;
	public int port;
	
	public Client(String username, InetAddress ipAddress, int port) {
		this.username = username;
		this.ipAddress = ipAddress;
		this.port = port;
	}
	
}