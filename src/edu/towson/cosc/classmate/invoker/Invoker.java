package edu.towson.cosc.classmate.invoker;

import android.content.Context;
import edu.towson.cosc.classmate.Message;
import edu.towson.cosc.classmate.aggregator.Aggregator;
import edu.towson.cosc.classmate.aggregator.DatabaseAdapter;
import edu.towson.cosc.classmate.scheduler.SystemCall;

public class Invoker {
	
	private static Aggregator aggr = new Aggregator();
	
	// Invoker commands for accessing the Aggregator
	public static Message addMessage( SystemCall thread, Message msg ) {
		return (Message) new AddMessage( aggr, thread, msg ).execute();
	}
	
	public static Message deleteMessage( SystemCall thread, long id ) throws IndexOutOfBoundsException {
		return (Message) new DeleteMessage( aggr, thread, id ).execute();
	}
	
	public static Long deleteAll( SystemCall thread ) {
		return (Long) new DeleteAll( aggr, thread ).execute();
	}
	
	public static DatabaseAdapter displayAllMessages( Runnable thread ) {
		return (DatabaseAdapter) new DisplayAllMessages( aggr, thread ).execute();
	}
	
	public static Message getMessage( Runnable thread, long id ) throws IndexOutOfBoundsException {
		return (Message) new GetMessage( aggr, thread, id ).execute();
	}
	
	// Database Method(s)
	public static DatabaseAdapter openDatabase( Context ctx ) {
		return aggr.openDatabase( ctx );
	}
	
	public static void closeDatabase() {
		aggr.closeDatabase();
	}
	
}
