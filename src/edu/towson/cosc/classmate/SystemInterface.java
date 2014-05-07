package edu.towson.cosc.classmate;

import java.text.Format;
import java.util.ArrayList;
import java.util.Queue;

import android.content.Context;
import edu.towson.cosc.classmate.aggregator.DatabaseAdapter;
import edu.towson.cosc.classmate.invoker.Invoker;
import edu.towson.cosc.classmate.scheduler.Scheduler;

public class SystemInterface {
	
	private static Format returnFormat;
	
	public static String[] delete( HomeActivity home, long id ) {
		ArrayList<String> list = new ArrayList<String>();
		
		if( Scheduler.delete( home, id ) ) {
			list.add( "Deleting Message" );
		}
		
		return list.toArray( new String[list.size()] );
	}
	
	public static String[] delete( HomeActivity home, Queue<Message> msgs ) {
		ArrayList<String> list = new ArrayList<String>();
		int count = 0;
		
		for( Message msg : msgs ) {
			if( Scheduler.delete( home, msg.getId() ) ) {
				count++;
			}
		}
		
		list.add( "Deleting " + count + " Message(s)" );
		
		return list.toArray( new String[list.size()] );
	}
	
	public static String[] deleteAll( HomeActivity home ) {
		ArrayList<String> list = new ArrayList<String>();
		
		if( Scheduler.deleteAll( home ) ) {
			list.add( "Deleting All Messages" );
		}
		
		return list.toArray( new String[list.size()] );
	}
	
	public static String[] displayMessage( HomeActivity home, long id ) {
		ArrayList<String> list = new ArrayList<String>();
		
		if( Scheduler.display( home, id ) ) {
			list.add( "Loading Message" );
		}
		
		return list.toArray( new String[list.size()] );
	}
	
	public static String[] displayAllMessages( HomeActivity home ) {
		ArrayList<String> list = new ArrayList<String>();
		
		if( Scheduler.displayAllMessages( home ) ) {
			list.add( "Loading Messages" );
		}
		
		return list.toArray( new String[list.size()] );
	}
	
	public static String[] receive( HomeActivity home, Message msg ) {
		ArrayList<String> list = new ArrayList<String>();
		
		if( Scheduler.receive( home, msg ) ) {
			list.add( "Message Incoming" );
		}
		
		return list.toArray( new String[list.size()] );
	}
	
	public static String[] send( HomeActivity home, Message draft ) {
		ArrayList<String> list = new ArrayList<String>();
		
		if( Scheduler.send( home, draft ) ) {
			list.add( "Sending Message" );
		}
		
		return list.toArray( new String[list.size()] );
	}
	
	public static Format getFormat() {
		return returnFormat;
	}
	
	// Database Method(s)
	public static DatabaseAdapter openDatabase( Context ctx ) {
		return Invoker.openDatabase( ctx );
	}
	
	public static void closeDatabase() {
		Invoker.closeDatabase();
	}
	
	// TODO: Fully implement
	public static String[] setFormat( Format pattern ) {
		ArrayList<String> list = new ArrayList<String>();
		
		returnFormat = pattern;
		
		return list.toArray( new String[list.size()] );
	}
}
