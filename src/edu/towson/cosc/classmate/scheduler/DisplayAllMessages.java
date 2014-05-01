<<<<<<< HEAD
package edu.towson.cosc.classmate.scheduler;

import edu.towson.cosc.classmate.aggregator.Conversation;
import edu.towson.cosc.classmate.invoker.Invoker;

public class DisplayAllMessages extends UserCall {
	
	public DisplayAllMessages() {
		super();
	}
	
	// TODO: Implement
	public void run() {
		Conversation list = Invoker.getAllMessages();
	}
	
}
=======
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
>>>>>>> 091517e83a0d3a2657254b830812765bd24d0260
