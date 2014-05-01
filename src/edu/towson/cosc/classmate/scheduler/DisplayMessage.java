<<<<<<< HEAD
package edu.towson.cosc.classmate.scheduler;

import edu.towson.cosc.classmate.Message;
import edu.towson.cosc.classmate.invoker.Invoker;

public class DisplayMessage extends UserCall {
	
	DisplayMessage( int index ) {
		super();
		this.index = index;
	}
	
	// TODO: Implement
	public synchronized void run() {
		try {
			Message msg = Invoker.getMessage( this.index );
		} catch( IndexOutOfBoundsException error ) {
			// Notify the user that the message to get doesn't exist in memory
		}
	}
}
=======
package edu.towson.cosc.classmate.scheduler;

import edu.towson.cosc.classmate.Message;
import edu.towson.cosc.classmate.invoker.Invoker;

class DisplayMessage extends UserCall {
	
	DisplayMessage( int index ) {
		super();
		this.index = index;
	}
	
	// TODO: Implement
	public synchronized void run() {
		try {
			Message msg = Invoker.getMessage( this.index );
		} catch( IndexOutOfBoundsException error ) {
			// Notify the user that the message to get doesn't exist in memory
		}
	}
}
>>>>>>> 091517e83a0d3a2657254b830812765bd24d0260
