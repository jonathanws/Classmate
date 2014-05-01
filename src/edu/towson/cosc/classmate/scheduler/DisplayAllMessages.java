package edu.towson.cosc.classmate.scheduler;

import edu.towson.cosc.classmate.aggregator.Conversation;
import edu.towson.cosc.classmate.invoker.Invoker;

class DisplayAllMessages extends UserCall {
	
	public DisplayAllMessages() {
		super();
	}
	
	// TODO: Implement
	public void run() {
		Conversation list = Invoker.getAllMessages();
	}
	
}
