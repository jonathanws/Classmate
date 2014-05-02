package edu.towson.cosc.classmate.invoker;

import android.content.Context;
import edu.towson.cosc.classmate.Message;
import edu.towson.cosc.classmate.aggregator.Aggregator;
import edu.towson.cosc.classmate.aggregator.Conversation;

public class Invoker {
	
	private static Aggregator aggr = new Aggregator();
	
	// Invoker commands for accessing the Aggregator
	public synchronized static Message addMessage( Message msg ) {
		return (Message) new AddMessage( aggr, msg ).execute();
	}
	
	public synchronized static Message deleteMessage( int index ) throws IndexOutOfBoundsException {
		Command action = new DeleteMessage( aggr, index );
		return (Message) action.execute();
	}
	
	public synchronized static Conversation getAllMessages() {
		return (Conversation) new GetMessageList( aggr ).execute();
	}
	
	public synchronized static Message getMessage( int index ) throws IndexOutOfBoundsException {
		return (Message) new GetMessage( aggr, index ).execute();
	}
	
	public static void openDatabase( Context ctx ) {
		aggr.openDatabase( ctx );
	}
	
	public static void closeDatabase() {
		aggr.closeDatabase();
	}
	
}
