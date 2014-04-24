package edu.towson.cosc.classmate.aggregator;

public class Aggregator {
	
	// Link to message storage here
	MessageList messages = new MessageList();
	
	public synchronized MessageList getMessages() {
		return this.messages;
	}
	
}
