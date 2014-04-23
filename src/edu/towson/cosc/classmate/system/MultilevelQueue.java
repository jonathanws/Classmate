package edu.towson.cosc.classmate.system;

import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;

public class MultilevelQueue {
	
	private int count;
	private Queue<LocalCall> user;
	private Queue<SendMessage> outgoing;
	private PriorityQueue<ReceiveMessage> incoming;
	private Queue<SystemCall> FCFS;
	
	MultilevelQueue() {
		this.user = new LinkedList<LocalCall>();
		this.outgoing = new LinkedList<SendMessage>();
		this.incoming = new PriorityQueue<ReceiveMessage>();
		this.FCFS = new LinkedList<SystemCall>();
	}
	
	int getCount() {
		return this.count;
	}
	
	synchronized SystemCall nextCommand() {
		if( this.count > 0 ) {
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
	
	synchronized void schedule( SystemCall call ) {
		if( call instanceof LocalCall ) {
			this.user.add( (LocalCall) call );
		} else if( call instanceof SendMessage ) {
			this.outgoing.add( (SendMessage) call );
		} else if( call instanceof ReceiveMessage ) {
			this.incoming.add( (ReceiveMessage) call );
		} else {
			this.FCFS.add( call );
		}
		
		this.count++;
	}
	
}
