package edu.towson.cosc.classmate.invoker;

import android.content.Context;
import edu.towson.cosc.classmate.Message;
import edu.towson.cosc.classmate.aggregator.Aggregator;
import edu.towson.cosc.classmate.aggregator.DatabaseAdapter;

public class Invoker {
	
	private static Aggregator aggr = new Aggregator();
	
	// Invoker commands for accessing the Aggregator
	public static Message addMessage( Message msg ) {
		return (Message) new AddMessage( aggr, msg ).execute();
	}
	
	public static Message deleteMessage( int index ) throws IndexOutOfBoundsException {
		return (Message) new DeleteMessage( aggr, index ).execute();
	}
	
	public static Long deleteAll() {
		return (Long) new DeleteAll( aggr ).execute();
	}
	
	public static DatabaseAdapter displayAllMessages() {
		return (DatabaseAdapter) new DisplayAllMessages( aggr ).execute();
	}
	
	public static Message getMessage( int index ) throws IndexOutOfBoundsException {
		return (Message) new GetMessage( aggr, index ).execute();
	}
	
	// Database Method(s)
	public static DatabaseAdapter openDatabase( Context ctx ) {
		return aggr.openDatabase( ctx );
	}
	
	public static void closeDatabase() {
		aggr.closeDatabase();
	}
	
}
