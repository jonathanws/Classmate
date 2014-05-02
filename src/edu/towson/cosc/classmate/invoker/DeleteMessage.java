package edu.towson.cosc.classmate.invoker;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import edu.towson.cosc.classmate.aggregator.Aggregator;
import edu.towson.cosc.classmate.aggregator.DatabaseConstants;

class DeleteMessage implements Command {
	
	private final long id;
	private SQLiteDatabase mDb;
	
	DeleteMessage( Aggregator aggr, long id ) {
		this.id = id;
		this.mDb = aggr.getDatabase();
	}
	
	public synchronized Object execute() {
		
		String query = "SELECT " + DatabaseConstants.DATABASE_NAME + " FROM " + DatabaseConstants.TABLE_DRAFTS + " WHERE " + DatabaseConstants.KEY_ROWID + " = " + id;
		
		Cursor c = mDb.rawQuery( query, null );
		
		mDb.delete( DatabaseConstants.TABLE_DRAFTS, DatabaseConstants.KEY_ROWID + "='" + id + "'", null );
		
		return c.getString( c.getColumnIndex( DatabaseConstants.KEY_ISMINE ) );
	}
	
}
