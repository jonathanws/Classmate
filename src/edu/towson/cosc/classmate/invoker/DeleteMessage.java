package edu.towson.cosc.classmate.invoker;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import edu.towson.cosc.classmate.aggregator.Aggregator;
import edu.towson.cosc.classmate.aggregator.DatabaseConstants;
import edu.towson.cosc.classmate.scheduler.SystemCall;

class DeleteMessage implements Command {
	
	private SQLiteDatabase mDb;
	private final long id;
	private SystemCall thread;
	
	DeleteMessage( Aggregator aggr, SystemCall thread, long id ) {
		this.mDb = aggr.getDatabase();
		this.id = id;
		this.thread = thread;
	}
	
	public Object execute() {
		String query = "SELECT " + DatabaseConstants.DATABASE_NAME + " FROM " + DatabaseConstants.TABLE_DRAFTS + " WHERE " + DatabaseConstants.KEY_ROWID + " = " + id;
		
		Cursor c = mDb.rawQuery( query, null );
		
		Aggregator.request( thread );
		mDb.delete( DatabaseConstants.TABLE_DRAFTS, DatabaseConstants.KEY_ROWID + "='" + id + "'", null );
		Aggregator.signal();
		
		return c.getString( c.getColumnIndex( DatabaseConstants.KEY_ISMINE ) );
	}
	
}
