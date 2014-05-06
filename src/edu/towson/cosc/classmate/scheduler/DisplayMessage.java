package edu.towson.cosc.classmate.scheduler;

import edu.towson.cosc.classmate.HomeActivity;
import edu.towson.cosc.classmate.Message;
import edu.towson.cosc.classmate.invoker.Invoker;

class DisplayMessage extends UserCall {
	
	DisplayMessage( HomeActivity home, int index ) {
		super( home );
		this.index = index;
	}
	
	// TODO: Implement
	public synchronized void run() {
		Message msg = Invoker.getMessage( this.index );
	}
}
