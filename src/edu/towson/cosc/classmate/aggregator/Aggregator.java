package edu.towson.cosc.classmate.aggregator;

import java.util.ArrayList;

import edu.towson.cosc.classmate.Message;

public class Aggregator {
	
	// Link to message storage here
	ArrayList<Message> messages = new ArrayList<Message>();
	
	public ArrayList<Message> getMessages() {
		return this.messages;
	}
	
}
