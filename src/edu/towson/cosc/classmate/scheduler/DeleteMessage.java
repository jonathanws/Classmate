package edu.towson.cosc.classmate.scheduler;

import edu.towson.cosc.classmate.HomeActivity;
import edu.towson.cosc.classmate.SystemInterface;
import edu.towson.cosc.classmate.invoker.Invoker;

class DeleteMessage extends UserCall {
	
	DeleteMessage( HomeActivity home, long id ) {
		super( home );
		this.id = id;
	}
	
	// TODO: Implement
	public void run() {
		Invoker.deleteMessage( this, this.id );
		SystemInterface.displayAllMessages( home );
		home.popToast( "Message Deleted" );
	}
}
