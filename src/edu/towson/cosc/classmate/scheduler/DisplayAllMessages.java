package edu.towson.cosc.classmate.scheduler;

import edu.towson.cosc.classmate.HomeActivity;
import edu.towson.cosc.classmate.invoker.Invoker;

class DisplayAllMessages extends UserCall {
	
	public DisplayAllMessages( HomeActivity home ) {
		super();
		this.home = home;
	}
	
	// TODO: Implement
	public void run() {
		Invoker.getAllMessages();
	}
	
}
