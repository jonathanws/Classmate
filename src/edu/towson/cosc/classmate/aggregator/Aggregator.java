package edu.towson.cosc.classmate.aggregator;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import edu.towson.cosc.classmate.scheduler.SystemCall;

public class Aggregator {
	
	private SQLiteDatabase database;
	
	private static BlockingQueue<SystemCall> waiting = new LinkedBlockingQueue<SystemCall>();
	private static int count = 0;
	
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
	
	// Mutual Exclusion Method(s)
	public static synchronized void request( SystemCall thread ) {
		if( count > 0 ) {
			waiting.add( thread );
		}
		
		count++;
	}
	
	public static synchronized void signal() {
		if( waiting.size() > 0 ) {
			waiting.poll();
			count--;
		}
	}
}
