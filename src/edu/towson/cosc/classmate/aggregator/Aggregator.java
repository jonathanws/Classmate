package edu.towson.cosc.classmate.aggregator;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import edu.towson.cosc.classmate.Message;

public class Aggregator {
	
	private Conversation messages = new Conversation();
	private Message draft = new Message( true, "" ); // Possibly not needed
	private SQLiteDatabase mDb;
	
	
	// Getter Method(s)
	public synchronized Conversation getMessages() {
		return this.messages;
	}
	
	public synchronized Message getDraft() {
		return this.draft;
	}
	
	public synchronized SQLiteDatabase getDatabase() {
		return this.mDb;
	}
	
	// Setter Method(s)
	public synchronized void openDatabase( Context ctx ) {
		mDb = new DatabaseHelper( ctx ).getWritableDatabase();
	}
	
	public void closeDatabase() {
		// TODO: Code to close database!!
	}
	
}
