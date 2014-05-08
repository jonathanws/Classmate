package edu.towson.cosc.classmate;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Date;

import android.util.Log;

public class NetworkThread implements Runnable {
	
	private DatagramSocket connection;
	private HomeActivity home;
	private Thread runner = new Thread( this, "NetworkThread" );
	
	private ArrayList<String> clients = new ArrayList<String>();
	private int port = 52952;
	
	NetworkThread( HomeActivity home ) {
		this.home = home;
	}
	
	public void run() {
		try {
			this.connection = new DatagramSocket( this.port, InetAddress.getLocalHost() );
		} catch( SocketException error ) {
			Log.d( "NETWORK", "CANNOT CREATE SOCKET" );
		} catch( UnknownHostException error ) {
			Log.d( "NETWORK", "UNKOWN HOST" );
		}
		
		DatagramPacket incoming;
		byte[] bytes;
		
		while( this.connection != null ) {
			try {
				bytes = new byte[185];
				incoming = new DatagramPacket( bytes, bytes.length );
				this.connection.receive( incoming );
				
				// Data should be in the following order, separated by pipes
				// 0: Priority (1 character/byte) + Pipe (1 byte)
				// 2: User Name (20 characters/bytes) + Pipe (1 byte)
				// 3: Message (160 characters/bytes)
				
				// TOTAL: 183 bytes
				String messageStr = new String( incoming.getData() ).trim();
				
				String[] str = messageStr.split( "&|" );
				
				Message message = new Message( false, str[2], str[1], Integer.parseInt( str[0] ) );
				message.setTimestamp( DateFormat.getTimeInstance( DateFormat.SHORT ).format( new Date() ) );
				
				SystemInterface.receive( this.home, message );
			} catch( IOException error ) {
				continue;
			}
		}
	}
	
	public boolean send( Message msg ) {
		String str = msg.getPriority() + "&|" + msg.getName() + "&|" + msg.getMessage();
		byte[] bytes = str.getBytes();
		
		// Sends a max of 183 bytes, currently
		DatagramPacket outgoing = new DatagramPacket( bytes, bytes.length );
		
		try {
			DatagramSocket socket = null;
			
			for( String IP : this.clients ) {
				socket = new DatagramSocket( this.port + 1, InetAddress.getByName( IP ) );
				
				this.connection.send( outgoing );
				
				this.connection.disconnect();
				
			}
			
			if( socket != null ) {
				socket.close();
			}
			
			return true;
		} catch( IOException error ) {
			Log.d( "BROADCAST", "FAILED" );
		} catch( NullPointerException error ) {
			Log.d( "BROADCAST", "NULL POINTER" );
		}
		
		return false;
	}
	
	void start() {
		this.runner.start();
	}
	
}
