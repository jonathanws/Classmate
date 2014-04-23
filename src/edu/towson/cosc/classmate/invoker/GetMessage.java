package edu.towson.cosc.classmate.invoker;

import edu.towson.cosc.classmate.aggregator.Aggregator;
import edu.towson.cosc.classmate.aggregator.MessageList;

public class GetMessage implements Command {
	
	private MessageList list;
	private final int index;
	
	GetMessage( Aggregator aggr, int index ) {
		this.list = aggr.getMessages();
		this.index = index;
	}
	
	public synchronized Object execute() {
		return this.list.getMessage( this.index );
	}
}
