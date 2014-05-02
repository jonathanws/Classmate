package edu.towson.cosc.classmate.scheduler;

import android.util.Log;
import edu.towson.cosc.classmate.HomeActivity;
import edu.towson.cosc.classmate.Message;
import edu.towson.cosc.classmate.invoker.Invoker;

class SendMessage extends NetworkCall {
	
	SendMessage( HomeActivity home, Message msg ) {
		super();
		this.home = home;
		this.msg = msg;
	}
	
	// TODO: Implement
	public synchronized void run() {
		Invoker.addMessage( this.msg );
		Log.d( "ALERT MUTHAFUCKA", "SENDING MESSAGE MUTHAFUCKA" );
	}
	
}
