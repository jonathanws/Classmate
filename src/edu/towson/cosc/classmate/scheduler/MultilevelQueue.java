package edu.towson.cosc.classmate.scheduler;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class MultilevelQueue {
	
	private int count;
	private Queue<UserCall> user;
	private Queue<SendMessage> outgoing;
	private PriorityQueue<ReceiveMessage> incoming;
	private Queue<SystemCall> FCFS;
	
	MultilevelQueue() {
		this.user = new LinkedList<UserCall>();
		this.outgoing = new LinkedList<SendMessage>();
		this.incoming = new PriorityQueue<ReceiveMessage>();
		this.FCFS = new LinkedList<SystemCall>();
	}
	
	synchronized int getCount() {
		return this.count;
	}
	
	// Executing Next Command Methods
	synchronized SystemCall nextCommand() {
		if( this.count > 0 ) {
			this.count--;
			
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
	// TODO: Make sure system calls can be deterministically
	// added to a queue with this method
	private synchronized boolean toQueue( Queue q, SystemCall call ) {
		if( q.add( call ) ) {
			this.count++;
			return true;
		}
		
		return false;
	}
	
	synchronized boolean schedule( SystemCall call ) {
		return toQueue( this.FCFS, call );
	}
	
	synchronized boolean schedule( UserCall call ) {
		return toQueue( this.user, call );
	}
	
	synchronized boolean schedule( NetworkCall call ) {
		if( call instanceof SendMessage ) {
			return toQueue( this.outgoing, call );
		}
		
		return toQueue( this.incoming, call );
	}
	
}
