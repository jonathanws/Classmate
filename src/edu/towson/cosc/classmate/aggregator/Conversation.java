package edu.towson.cosc.classmate.aggregator;

import java.util.ArrayList;

import edu.towson.cosc.classmate.Message;

public class Conversation {
	
	private ArrayList<Message> list;
	
	/** Include Reference to SQLite table here */
	
	Conversation() {
		this.list = new ArrayList<Message>();
	}
	
	public synchronized boolean addMessage( Message msg ) {
		return list.add( msg );
	}
	
	public synchronized void deleteMessage( int index ) throws IndexOutOfBoundsException {
		list.remove( index );
	}
	
	public synchronized ArrayList<Message> getList() {
		return this.list;
	}
	
	public synchronized Message getMessage( int index ) throws IndexOutOfBoundsException {
		return list.get( index );
	}
	
}
