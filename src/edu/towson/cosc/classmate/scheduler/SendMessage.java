package edu.towson.cosc.classmate.scheduler;

import edu.towson.cosc.classmate.Message;
import edu.towson.cosc.classmate.invoker.Invoker;

public class SendMessage extends NetworkCall {
	
	SendMessage( Message msg ) {
		super();
		this.msg = msg;
	}
	
	// TODO: Implement
	public synchronized void run() {
		Invoker.addMessage( this.msg );
	}
	
}
