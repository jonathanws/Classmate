<<<<<<< HEAD
package edu.towson.cosc.classmate.scheduler;

import edu.towson.cosc.classmate.Message;

public abstract class NetworkCall extends SystemCall {
	
	// TODO: private SocketThread caller;
	protected Message msg;
	
	public NetworkCall() {
		super();
	}
}
=======
package edu.towson.cosc.classmate.scheduler;

import edu.towson.cosc.classmate.Message;

abstract class NetworkCall extends SystemCall {
	
	// TODO: private SocketThread caller;
	protected Message msg;
	
	public NetworkCall() {
		super();
	}
}
>>>>>>> 091517e83a0d3a2657254b830812765bd24d0260
