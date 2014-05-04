package edu.towson.cosc.classmate.invoker;

import edu.towson.cosc.classmate.aggregator.Aggregator;
import edu.towson.cosc.classmate.aggregator.DatabaseAdapter;

class DeleteAll implements Command {
	
	private DatabaseAdapter adapter;
	
	DeleteAll( Aggregator aggr ) {
		this.adapter = aggr.getDatabaseAdapter();
	}
	
	public Object execute() {
		long count = adapter.getNumEntries();
		
		adapter.deleteAllMessages();
		
		return Long.valueOf( count );
	}
}
