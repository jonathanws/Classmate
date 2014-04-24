package edu.towson.cosc.classmate.scheduler;

import edu.towson.cosc.classmate.invoker.Invoker;

public class DisplayAllMessages extends UserCall {
	
	public DisplayAllMessages() {
		super();
	}
	
	// TODO: Implement
	public void run() {
		Invoker.getAllMessages();
	}
	
}
