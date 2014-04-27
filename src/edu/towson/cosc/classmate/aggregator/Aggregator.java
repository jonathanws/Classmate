package edu.towson.cosc.classmate.aggregator;

import edu.towson.cosc.classmate.Message;

public class Aggregator {
	
	private MessageList messages = new MessageList();
	private Message draft = new Message( true, "" ); // Possibly not needed
	
	// Getter Method(s)
	public synchronized MessageList getMessages() {
		return this.messages;
	}
	
	public synchronized Message getDraft() {
		return this.draft;
	}
	
}
