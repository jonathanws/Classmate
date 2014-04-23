package edu.towson.cosc.classmate.invoker;

import edu.towson.cosc.classmate.aggregator.Aggregator;
import edu.towson.cosc.classmate.aggregator.MessageList;

public class GetMessageList {
	
	private MessageList list;
	
	GetMessageList( Aggregator aggr ) {
		this.list = aggr.getMessages();
	}
	
	public synchronized Object execute() {
		return this.list;
	}
	
}
