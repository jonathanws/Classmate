package edu.towson.cosc.classmate.aggregator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

public class Aggregator {
	
	private Conversation messages = new Conversation();
	private SQLiteDatabase database;
	
	// Getter Method(s)
	public synchronized Conversation getMessages() {
		return this.messages;
	}
	
	public synchronized SQLiteDatabase getDatabase() {
		return this.database;
	}
	
	// Setter Method(s)
	public synchronized DatabaseAdapter openDatabase( Context ctx ) {
		this.database = new DatabaseHelper( ctx ).getWritableDatabase();
		return new DatabaseAdapter( this.database );
	}
	
	public void closeDatabase() {
		// TODO: Code to close database!!
	}
	
}
