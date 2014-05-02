package edu.towson.cosc.classmate.scheduler;

import android.os.Looper;
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
	
	// Update List View
	public void updateListView( String str ) {
		Looper.prepare();
		
		this.home.displayListView();
		
		if( str != null && !str.equals( "" ) ) {
			this.home.popToast( str );
		}
		
		Looper.loop();
	}
}
