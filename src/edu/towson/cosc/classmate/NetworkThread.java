package edu.towson.cosc.classmate;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

import android.util.Log;

public class NetworkThread {
	
	private DatagramSocket connection;
	private HomeActivity home;
	private Thread runner;
	
	NetworkThread( HomeActivity home ) {
		this.home = home;
	}
	
	public void run() {
		try {
			this.connection = new DatagramSocket( 5000 );
			DatagramPacket incoming;
			byte[] bytes;
			
			while( true ) {
				try {
					bytes = new byte[183];
					incoming = new DatagramPacket( bytes, bytes.length );
					connection.receive( incoming );
					
					// Data should be in the following order, separated by pipes
					// 0: Priority (1 character/byte) + Pipe (1 byte)
					// 2: User Name (20 characters/bytes) + Pipe (1 byte)
					// 3: Message (160 characters/bytes)
					
					// TOTAL: 183 bytes
					String messageStr = new String( incoming.getData() ).trim();
					
					String[] str = messageStr.split( "|" );
					
					Message message = new Message( false, str[2], str[1], Integer.parseInt( str[0] ) );
					
					SystemInterface.receive( home, message );
				} catch( IOException error ) {
					continue;
				}
			}
		} catch( SocketException error ) {
			Log.d( "NETWORK", "SOCKET CRASHED" );
		}
	}
	
	public boolean send( Message msg ) {
		String str = msg.getPriority() + "|" + msg.getName() + "|" + msg.getMessage();
		byte[] bytes = str.getBytes();
		
		DatagramPacket outgoing = new DatagramPacket( bytes, bytes.length );
		
		try {
			connection.send( outgoing );
			return true;
		} catch( IOException error ) {
			Log.d( "BROADCAST", "FAILED" );
		}
		
		return false;
	}
	
	void start() {
		this.runner.start();
	}
	
}
