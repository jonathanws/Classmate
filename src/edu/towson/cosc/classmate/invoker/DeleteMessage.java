package edu.towson.cosc.classmate.invoker;

import edu.towson.cosc.classmate.aggregator.Aggregator;
import edu.towson.cosc.classmate.aggregator.DatabaseAdapter;
import edu.towson.cosc.classmate.scheduler.SystemCall;

class DeleteMessage implements Command {
	
	private DatabaseAdapter adapter;
	private final long id;
	private SystemCall thread;
	
	DeleteMessage( Aggregator aggr, SystemCall thread, long id ) {
		this.adapter = aggr.getDatabaseAdapter();
		this.id = id;
		this.thread = thread;
	}
	
	public Object execute() {
		Aggregator.request( this.thread );
		this.adapter.deleteMessageById( this.id );
		Aggregator.signal();
		
		return null;
	}
	
}
