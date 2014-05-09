package edu.towson.cosc.classmate.aggregator;

import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseAdapter {
	
	private SQLiteDatabase database;
	
	DatabaseAdapter( SQLiteDatabase database ) {
		this.database = database;
	}
	
	public void deleteAllMessages() {
		database.delete( DatabaseConstants.TABLE_DRAFTS, null, null );
	}
	
	public void deleteMessageById( long n ) {
		int i = Integer.parseInt( Long.toString( n ) );
		database.delete( DatabaseConstants.TABLE_DRAFTS, DatabaseConstants.KEY_ROWID + "=" + i, null );
	}
	
	// http://stackoverflow.com/a/9076679/1097170
	public void getMessageById( long n ) {
		database.query( true, DatabaseConstants.TABLE_DRAFTS,
		// select clause
				new String[] { DatabaseConstants.KEY_ROWID, DatabaseConstants.KEY_NAME, DatabaseConstants.KEY_MESSAGE, DatabaseConstants.KEY_TIMESTAMP, DatabaseConstants.KEY_IP_ADDR, DatabaseConstants.KEY_PRIORITY, DatabaseConstants.KEY_ISMINE }, DatabaseConstants.KEY_ROWID + " LIKE ?", // where clause
				new String[] { Long.valueOf( n ).toString() }, // quotation marks cast to string
				null, null, null, null );
		
		// TODO i'm skeptic about there being no space after the question mark
		
		// TODO determine return type
	}
	
	// http://stackoverflow.com/questions/11251901/check-whether-database-is-empty
	public boolean isDBEmpty() {
		Cursor c = database.rawQuery( "SELECT * FROM " + DatabaseConstants.TABLE_DRAFTS, null );
		
		if( c.moveToFirst() )
			return false;
		
		return true;
	}
	
	public long getNumEntries() {
		// Available only in api 11+
		return DatabaseUtils.queryNumEntries( database, DatabaseConstants.TABLE_DRAFTS, null );
	}
	
	// For debugging purposes. Toast this from HomeActivity.java
	public Cursor showAllTables() {
		String allTables = " SELECT name FROM sqlite_master " + " WHERE type='table'";
		return this.database.rawQuery( allTables, null );
	}
	
	// For debugging purposes. Toast this from HomeActivity.java
	public Cursor selectStar() {
		String selectStar = " SELECT * FROM " + DatabaseConstants.TABLE_DRAFTS;
		return this.database.rawQuery( selectStar, null );
	}
	
}
