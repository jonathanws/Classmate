package edu.towson.cosc.classmate.invoker;

import edu.towson.cosc.classmate.Message;
import edu.towson.cosc.classmate.aggregator.Aggregator;
import edu.towson.cosc.classmate.aggregator.MessageList;

public class DeleteMessage implements Command {
	
	private MessageList list;
	private final int index;
	
	DeleteMessage( Aggregator aggr, int index ) {
		this.list = aggr.getMessages();
		this.index = index;
	}
	
	public synchronized Object execute() {
		Message msg = list.getMessage( this.index );
		
		if ( msg != null ) {
			this.list.deleteMessage( this.index );
		}
		
		return msg;
	}
}
