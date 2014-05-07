package edu.towson.cosc.classmate.scheduler;

import edu.towson.cosc.classmate.HomeActivity;
import edu.towson.cosc.classmate.Message;
import edu.towson.cosc.classmate.SystemInterface;
import edu.towson.cosc.classmate.invoker.Invoker;

class SendMessage extends NetworkCall {
	
	SendMessage( HomeActivity home, Message msg ) {
		super( home );
		this.msg = msg;
	}
	
	// TODO: Implement
	public void run() {
		Invoker.addMessage( this.msg );
		SystemInterface.displayAllMessages( home );
		
		if( home.getConnection().send( msg ) ) {
			home.popToast( "Message Sent" );
		}
	}
	
}
