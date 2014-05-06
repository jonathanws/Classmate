package edu.towson.cosc.classmate.aggregator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Aggregator {
	
	private SQLiteDatabase database;
	
	public SQLiteDatabase getDatabase() {
		return this.database;
	}
	
	public DatabaseAdapter getDatabaseAdapter() {
		return new DatabaseAdapter( database );
	}
	
	// Setter Method(s)
	public DatabaseAdapter openDatabase( Context ctx ) {
		this.database = new DatabaseHelper( ctx ).getWritableDatabase();
		return new DatabaseAdapter( this.database );
	}
	
	public void closeDatabase() {
		// TODO: Code to close database!!
	}
	
}
