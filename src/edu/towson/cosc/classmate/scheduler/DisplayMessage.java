package edu.towson.cosc.classmate.scheduler;

import edu.towson.cosc.classmate.invoker.Invoker;

public class DisplayMessage extends UserCall {
	
	// TODO: Add constructors
	DisplayMessage( int index ) {
		super();
		this.index = index;
	}
	
	// TODO: Implement
	public synchronized void run() {
		Invoker.getMessage( this.index );
	}
}
