package com.daansander.chat.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;

import com.daansander.chat.Chat;

public class Server extends Thread {

	private Chat chat;
	private DatagramSocket socket;

	public Server(Chat chat) {
		this.chat = chat;
		try {
			socket = new DatagramSocket(25567);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		while (true) {
			byte[] data = new byte[1024];
			DatagramPacket packet = new DatagramPacket(data, data.length);
			
			try {
				socket.receive(packet);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			String message = new String(data).trim();
			
			System.out.println("CLIENT [" + packet.getAddress().getHostAddress() + ":" + packet.getPort() + "] > " + message);
			
			if(message.equals("ping")) {
				System.out.println("Returning pong");
				sendData("pong".getBytes(), packet.getAddress(), packet.getPort());
			}
		}
	}

	public void sendData(byte[] data, InetAddress address, int port) {
		DatagramPacket packet = new DatagramPacket(data, data.length, address, port);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}