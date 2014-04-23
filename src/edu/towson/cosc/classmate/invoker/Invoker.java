package edu.towson.cosc.classmate.invoker;

import edu.towson.cosc.classmate.Message;
import edu.towson.cosc.classmate.aggregator.Aggregator;
import edu.towson.cosc.classmate.aggregator.MessageList;

public class Invoker {
	
	private static Aggregator aggr = new Aggregator();
	
	public static Message addMessage( Message msg ) {
		return (Message) new AddMessage( aggr, msg ).execute();
	}
	
	public static Message deleteMessage( int index ) {
		return (Message) new DeleteMessage( aggr, index ).execute();
	}
	
	public static Message getMessage( int index ) {
		return (Message) new GetMessage( aggr, index ).execute();
	}
	
	public static MessageList getAllMessages() {
		return (MessageList) new GetMessageList( aggr ).execute();
	}
	
}
