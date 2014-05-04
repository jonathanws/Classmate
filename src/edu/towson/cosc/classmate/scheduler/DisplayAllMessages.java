package edu.towson.cosc.classmate.scheduler;

import edu.towson.cosc.classmate.HomeActivity;
import edu.towson.cosc.classmate.invoker.Invoker;

class DisplayAllMessages extends UserCall {
	
	public DisplayAllMessages( HomeActivity home ) {
		super( home );
	}
	
	// TODO: Implement
	public void run() {
		home.runOnUiThread( new Runnable() {
			
			public void run() {
				home.displayListView( Invoker.displayAllMessages() );
			}
			
		} );
	}
	
}
