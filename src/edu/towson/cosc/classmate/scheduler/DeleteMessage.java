package edu.towson.cosc.classmate.scheduler;

import edu.towson.cosc.classmate.invoker.Invoker;

public class DeleteMessage extends UserCall {
	
	DeleteMessage( int index ) {
		super();
		this.index = index;
	}
	
	// TODO: Implement
	public synchronized void run() {
		Invoker.deleteMessage( this.index );
	}
	
}
