package edu.towson.cosc.classmate.invoker;

import edu.towson.cosc.classmate.aggregator.Aggregator;
import edu.towson.cosc.classmate.aggregator.DatabaseAdapter;

class DeleteAll implements Command {
	
	private DatabaseAdapter adapter;
	
	DeleteAll( Aggregator aggr ) {
		this.adapter = aggr.getDatabaseAdapter();
	}
	
	public Object execute() {
		long count = this.adapter.getNumEntries();
		
		this.adapter.deleteAllMessages();
		
		return Long.valueOf( count );
	}
}
