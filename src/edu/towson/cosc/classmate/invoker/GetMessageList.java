package edu.towson.cosc.classmate.invoker;

import edu.towson.cosc.classmate.aggregator.Aggregator;
import edu.towson.cosc.classmate.aggregator.Conversation;

class GetMessageList implements Command {
	
	private Conversation list;
	
	GetMessageList( Aggregator aggr ) {
		this.list = aggr.getMessages();
	}
	
	public synchronized Object execute() {
		return this.list;
	}
	
	public synchronized Object redo() {
		return null;
	}
	
	public synchronized Object undo() {
		return null;
	}
	
}
