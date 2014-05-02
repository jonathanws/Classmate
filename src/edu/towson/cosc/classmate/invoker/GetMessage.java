package edu.towson.cosc.classmate.invoker;

import edu.towson.cosc.classmate.aggregator.Aggregator;

class GetMessage implements Command {
	
	private final int index;
	
	GetMessage( Aggregator aggr, int index ) {
		this.index = index;
	}
	
	public synchronized Object execute() {
		return null;
	}
	
}
