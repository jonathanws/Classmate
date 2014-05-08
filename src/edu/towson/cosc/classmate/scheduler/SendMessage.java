package edu.towson.cosc.classmate.scheduler;

import edu.towson.cosc.classmate.HomeActivity;
import edu.towson.cosc.classmate.Message;
import edu.towson.cosc.classmate.SystemInterface;
import edu.towson.cosc.classmate.invoker.Invoker;

class SendMessage extends NetworkCall implements Comparable<SendMessage> {
	
	SendMessage( HomeActivity home, Message msg ) {
		super( home );
		this.msg = msg;
	}
	
	public void run() {
		Invoker.addMessage( this, this.msg );
		SystemInterface.displayAllMessages( home );
		home.popToast( "Message Sent" );
	}
	
	public int compareTo( SendMessage call ) {
		return this.msg.getPriority() - call.msg.getPriority();
	}
}
