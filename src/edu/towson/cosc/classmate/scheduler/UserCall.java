package edu.towson.cosc.classmate.scheduler;

import edu.towson.cosc.classmate.HomeActivity;

abstract class UserCall extends SystemCall {
	
	// TODO: private MessagesThread caller;
	protected int index;
	protected HomeActivity home;
	
	public UserCall( HomeActivity home ) {
		super();
		this.home = home;
	}
	
}
