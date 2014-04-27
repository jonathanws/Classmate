package edu.towson.cosc.classmate.aggregator;

import edu.towson.cosc.classmate.Message;

public class Aggregator {
	
	private Conversation messages = new Conversation();
	private Message draft = new Message( true, "" ); // Possibly not needed
	
	// Getter Method(s)
	public synchronized Conversation getMessages() {
		return this.messages;
	}
	
	public synchronized Message getDraft() {
		return this.draft;
	}
	
}
