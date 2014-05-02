package edu.towson.cosc.classmate.aggregator;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import edu.towson.cosc.classmate.DatabaseConstants;

public class DatabaseAdapter {
	
	private SQLiteDatabase database;
	
	DatabaseAdapter( SQLiteDatabase database ) {
		this.database = database;
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
