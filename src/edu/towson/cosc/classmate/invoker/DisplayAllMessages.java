package edu.towson.cosc.classmate.invoker;

import edu.towson.cosc.classmate.aggregator.Aggregator;
import edu.towson.cosc.classmate.aggregator.DatabaseAdapter;

class DisplayAllMessages implements Command {
	
	private DatabaseAdapter adapter;
	
	DisplayAllMessages( Aggregator aggr, Runnable thread ) {
		this.adapter = aggr.getDatabaseAdapter();
	}
	
	public synchronized Object execute() {
		return this.adapter;
	}
	
}
