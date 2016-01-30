package com.daansander.chat.net;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;

import com.daansander.chat.Chat;

public class ServerClient extends Thread {

	private InetAddress ipAddress;
	private DatagramSocket socket;
	private Chat chat;
	
	public ServerClient(Chat chat, String ipAddress) {
		this.chat = chat;
		try {
			socket = new DatagramSocket();
			this.ipAddress = InetAddress.getByName(ipAddress);
		} catch(UnknownHostException ex) {
			ex.printStackTrace();
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		sendData("ping".getBytes());
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
			
			System.out.println("SERVER >  " + message);
		}
	}
	
	public void sendData(byte[] data) {
		DatagramPacket packet = new DatagramPacket(data, data.length, ipAddress, 25567);
		try {
			socket.send(packet);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}