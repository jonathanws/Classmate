package edu.towson.cosc.classmate.scheduler;

import edu.towson.cosc.classmate.Message;

abstract class NetworkCall extends SystemCall {
	
	// TODO: private SocketThread caller;
	protected Message msg;
	
	public NetworkCall() {
		super();
	}
}
