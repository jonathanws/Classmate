package edu.towson.cosc.classmate.invoker;

import edu.towson.cosc.classmate.aggregator.Aggregator;
import edu.towson.cosc.classmate.aggregator.DatabaseAdapter;
import edu.towson.cosc.classmate.scheduler.SystemCall;

class DeleteAll implements Command {
	
	private DatabaseAdapter adapter;
	private SystemCall thread;
	
	DeleteAll( Aggregator aggr, SystemCall thread ) {
		this.adapter = aggr.getDatabaseAdapter();
		this.thread = thread;
	}
	
	public Object execute() {
		Aggregator.request( thread );
		long count = this.adapter.getNumEntries();
		this.adapter.deleteAllMessages();
		Aggregator.signal();
		
		return Long.valueOf( count );
	}
}
