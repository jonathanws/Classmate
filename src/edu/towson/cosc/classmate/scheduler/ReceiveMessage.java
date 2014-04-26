package edu.towson.cosc.classmate.scheduler;

import edu.towson.cosc.classmate.Message;
import edu.towson.cosc.classmate.invoker.Invoker;

public class ReceiveMessage extends NetworkCall {
	
	ReceiveMessage( Message msg ) {
		super();
		this.msg = msg;
	}
	
	// TODO: Implement
	public synchronized void run() {
		Invoker.addMessage( this.msg );
	}
	
}
