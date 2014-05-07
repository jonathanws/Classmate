package edu.towson.cosc.classmate.invoker;

import android.database.sqlite.SQLiteDatabase;
import edu.towson.cosc.classmate.aggregator.Aggregator;

class GetMessage implements Command {
	
	private final long id;
	private SQLiteDatabase mDb;
	
	GetMessage( Aggregator aggr, long id ) {
		this.id = id;
		this.mDb = aggr.getDatabase();
	}
	
	public synchronized Object execute() {
		return null;
	}
	
}
