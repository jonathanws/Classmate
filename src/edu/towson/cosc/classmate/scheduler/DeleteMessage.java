package edu.towson.cosc.classmate.scheduler;

import edu.towson.cosc.classmate.HomeActivity;
import edu.towson.cosc.classmate.SystemInterface;
import edu.towson.cosc.classmate.invoker.Invoker;

class DeleteMessage extends UserCall {
	
	DeleteMessage( HomeActivity home, int index ) {
		super( home );
		this.index = index;
	}
	
	// TODO: Implement
	public synchronized void run() {
		Invoker.deleteMessage( this.index );
		SystemInterface.displayAllMessages( home );
		home.popToast( "Message Deleted" );
	}
}
