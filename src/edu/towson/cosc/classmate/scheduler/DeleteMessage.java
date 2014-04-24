package edu.towson.cosc.classmate.scheduler;

public class DeleteMessage extends UserCall {
	
	private int index;
	
	DeleteMessage( int index ) {
		this.index = index;
	}
	
	public synchronized void run() {
		
	}
	
}
