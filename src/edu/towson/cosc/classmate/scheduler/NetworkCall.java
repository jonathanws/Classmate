package edu.towson.cosc.classmate.scheduler;

import edu.towson.cosc.classmate.HomeActivity;
import edu.towson.cosc.classmate.Message;

abstract class NetworkCall extends SystemCall {
	
	// TODO: private SocketThread caller;
	protected Message msg;
	protected HomeActivity home;
	
	public NetworkCall( HomeActivity home ) {
		super();
		this.home = home;
	}
	
}
