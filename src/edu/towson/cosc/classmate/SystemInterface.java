package edu.towson.cosc.classmate;

import java.text.Format;
import java.util.ArrayList;
import java.util.Queue;

import edu.towson.cosc.classmate.scheduler.Scheduler;

public class SystemInterface {
	
	private static Format returnFormat;
	
	public static String[] delete( Runnable thread, int index ) {
		ArrayList<String> list = new ArrayList<String>();
		
		if( Scheduler.delete( thread, index ) ) {
			list.add( "Deleting Message" );
		}
		
		return list.toArray( new String[list.size()] );
	}
	
	public static String[] delete( Runnable thread, Queue<Integer> msgs ) {
		ArrayList<String> list = new ArrayList<String>();
		int count = 0;
		
		for( int index : msgs ) {
			if( Scheduler.delete( thread, index ) ) {
				count++;
			}
		}
		
		list.add( "Deleting " + count + " Message(s)" );
		
		return list.toArray( new String[list.size()] );
	}
	
	public static String[] displayMessage( Runnable thread, int index ) {
		ArrayList<String> list = new ArrayList<String>();
		
		if( Scheduler.display( thread, index ) ) {
			list.add( "Loading Message" );
		}
		
		return list.toArray( new String[list.size()] );
	}
	
	public static String[] displayAllMessages( Runnable thread ) {
		ArrayList<String> list = new ArrayList<String>();
		
		if( Scheduler.displayAllMessages( thread ) ) {
			list.add( "Loading Messages" );
		}
		
		return list.toArray( new String[list.size()] );
	}
	
	public static String[] receive( Runnable thread, Message msg ) {
		ArrayList<String> list = new ArrayList<String>();
		
		if( Scheduler.receive( thread, msg ) ) {
			list.add( "Message Incoming" );
		}
		
		return list.toArray( new String[list.size()] );
	}
	
	public static String[] send( Runnable thread, Message draft ) {
		ArrayList<String> list = new ArrayList<String>();
		
		if( Scheduler.send( thread, draft ) ) {
			list.add( "Sending Message" );
		}
		
		return list.toArray( new String[list.size()] );
	}
	
	public static Format getFormat() {
		return returnFormat;
	}
	
	// TODO: Fully implement
	public static String[] setFormat( Format pattern ) {
		ArrayList<String> list = new ArrayList<String>();
		
		returnFormat = pattern;
		
		return list.toArray( new String[list.size()] );
	}
}
