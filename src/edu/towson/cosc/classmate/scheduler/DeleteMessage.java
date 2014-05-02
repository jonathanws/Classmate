package edu.towson.cosc.classmate.scheduler;

import edu.towson.cosc.classmate.HomeActivity;
import edu.towson.cosc.classmate.invoker.Invoker;

class DeleteMessage extends UserCall {
	
	DeleteMessage( HomeActivity home, int index ) {
		super( home );
		this.index = index;
	}
	
	// TODO: Implement
	public synchronized void run() {
		System.out.println( "DELETE MESSAGE" );
		try {
			Invoker.deleteMessage( this.index );
			updateListView( "Message Deleted" );
		} catch( IndexOutOfBoundsException error ) {
			// Notify the user that the message to delete doesn't exist in memory
		}
	}
	
}
