package edu.towson.cosc.classmate.scheduler;

import java.lang.Thread.State;

import android.util.Log;

class Dispatcher implements Runnable {
	
	private Thread runner = new Thread( this, "Dispatcher" );
	private SystemCall current;
	
	public void run() {
		MultilevelQueue queue = Scheduler.getQueue();
		
		while( queue.size() > 0 ) {
			this.current = queue.nextCommand();
			this.current.start();
			
			try {
				this.current.join();
			} catch( InterruptedException error ) {
				error.printStackTrace();
			}
		}
		
		this.current = null;
	}
	
	// Preemption Method(s)
	void preempt( SystemCall task ) {
		if( this.runner.isAlive() ) {
			SystemCall waiting = this.current;
			
			try {
				waiting.wait();
				
				this.current = task;
				this.current.start();
				this.current.join();
				
				waiting.notify();
			} catch( InterruptedException error ) {
				error.printStackTrace();
			}
		}
		
		this.current = null;
	}
	
	boolean isAlive() {
		return this.runner.isAlive();
	}
	
	void join() {
		try {
			this.runner.join();
		} catch( InterruptedException error ) {
		}
	}
	
	void start() {
		try {
			this.runner.start();
		} catch( IllegalMonitorStateException error ) {
			Log.d( "DISPATCHER", "START IllegalMonitorState" );
		} catch( IllegalThreadStateException error ) {
			Log.d( "DISPATCHER", "START IllegalThreadState" );
		}
	}
	
	State getState() {
		return this.runner.getState();
	}
	
}
