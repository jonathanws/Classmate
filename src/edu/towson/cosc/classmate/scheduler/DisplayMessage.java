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
		try {
			Invoker.getMessage( this.index );
		} catch( IndexOutOfBoundsException error ) {
			// Notify the user that the message to get doesn't exist in memory
		}
	}
}
