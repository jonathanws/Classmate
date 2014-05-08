package edu.towson.cosc.classmate.invoker;

import android.database.sqlite.SQLiteDatabase;
import edu.towson.cosc.classmate.aggregator.Aggregator;

class GetMessage implements Command {
	
	private SQLiteDatabase mDb;
	private Runnable thread;
	private final long id;
	
	GetMessage( Aggregator aggr, Runnable thread, long id ) {
		this.mDb = aggr.getDatabase();
		this.thread = thread;
		this.id = id;
	}
	
	public synchronized Object execute() {
		return null;
	}
	
}
