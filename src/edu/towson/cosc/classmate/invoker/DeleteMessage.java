package edu.towson.cosc.classmate.invoker;

import edu.towson.cosc.classmate.Message;
import edu.towson.cosc.classmate.aggregator.Aggregator;
import edu.towson.cosc.classmate.aggregator.Conversation;

class DeleteMessage implements Command {
	
	private Conversation list;
	private final int index;
	private Message msg;
	
	DeleteMessage( Aggregator aggr, int index ) {
		this.list = aggr.getMessages();
		this.index = index;
	}
	
	public synchronized Object execute() {
		this.msg = list.getMessage( this.index );
		
		if( this.msg != null ) {
			this.list.deleteMessage( this.index );
		}
		
		return this.msg;
	}
	
	public synchronized Object redo() {
		return null;
	}
	
	public synchronized Object undo() {
		return null;
	}
}
