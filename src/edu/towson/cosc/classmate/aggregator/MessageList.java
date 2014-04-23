package edu.towson.cosc.classmate.aggregator;

import java.util.ArrayList;
import java.util.Collection;

import edu.towson.cosc.classmate.Message;

public class MessageList {
	
	private ArrayList<Message> list;
	
	public synchronized boolean addMessage( Message msg ) {
		return list.add( msg );
	}
	
	public synchronized void deleteMessage( int index ) {
		list.remove( index );
	}
	
	public synchronized Collection<Message> getList() {
		return this.list;
	}
	
	public synchronized Message getMessage( int index ) {
		return list.get( index );
	}
	
}
