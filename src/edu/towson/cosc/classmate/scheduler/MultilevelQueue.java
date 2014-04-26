package edu.towson.cosc.classmate.scheduler;

import java.util.LinkedList;
import java.util.Queue;

public class MultilevelQueue {
	
	private int size;
	private Queue<UserCall> user;
	private Queue<SendMessage> outgoing;
	private Queue<ReceiveMessage> incoming;
	private Queue<SystemCall> FCFS;
	
	MultilevelQueue() {
		this.user = new LinkedList<UserCall>();
		this.outgoing = new LinkedList<SendMessage>();
		this.incoming = new LinkedList<ReceiveMessage>();
		this.FCFS = new LinkedList<SystemCall>();
	}
	
	synchronized int size() {
		return this.size;
	}
	
	// Executing Next Command Methods
	synchronized SystemCall nextCommand() {
		if( this.size > 0 ) {
			this.size--;
			
			if( this.user.size() > 0 ) {
				return this.user.poll();
			} else if( this.outgoing.size() > 0 ) {
				return this.outgoing.poll();
			} else if( this.incoming.size() > 0 ) {
				return this.incoming.poll();
			} else {
				return this.FCFS.poll();
			}
		}
		
		return null;
	}
	
	// Scheduling Methods
	synchronized boolean schedule( UserCall call ) {
		if( this.user.add( call ) ) {
			this.size++;
			return true;
		}
		
		return false;
	}
	
	synchronized boolean schedule( SendMessage call ) {
		if( this.outgoing.add( call ) ) {
			this.size++;
			return true;
		}
		
		return false;
	}
	
	synchronized boolean schedule( ReceiveMessage call ) {
		if( this.incoming.add( call ) ) {
			this.size++;
			return true;
		}
		
		return false;
	}
	
	synchronized boolean schedule( SystemCall call ) {
		if( this.FCFS.add( call ) ) {
			this.size++;
			return true;
		}
		
		return false;
	}
	
}
