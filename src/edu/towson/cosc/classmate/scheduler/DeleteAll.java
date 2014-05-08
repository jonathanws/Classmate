package edu.towson.cosc.classmate.scheduler;

import edu.towson.cosc.classmate.HomeActivity;
import edu.towson.cosc.classmate.SystemInterface;
import edu.towson.cosc.classmate.invoker.Invoker;

public class DeleteAll extends UserCall {
	
	DeleteAll( HomeActivity home ) {
		super( home );
	}
	
	public void run() {
		long count = Invoker.deleteAll( this );
		
		SystemInterface.displayAllMessages( this.home );
		this.home.popToast( count + " Messages Deleted" );
	}
	
}
