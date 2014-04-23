package edu.towson.cosc.classmate.invoker;

import edu.towson.cosc.classmate.Message;
import edu.towson.cosc.classmate.aggregator.Aggregator;
import edu.towson.cosc.classmate.aggregator.MessageList;

public class AddMessage implements Command {
	
	private MessageList list;
	private final Message msg;
	
	AddMessage( Aggregator aggr, Message msg ) {
		this.list = aggr.getMessages();
		this.msg = msg;
	}
	
	public synchronized Object execute() {
		if ( this.list.addMessage( this.msg ) ) {
			return this.msg;
		}
		
		return null;
	}
	
}
