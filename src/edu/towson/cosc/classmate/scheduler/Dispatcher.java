package edu.towson.cosc.classmate.scheduler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class Dispatcher implements Runnable {
	
	private ExecutorService dispatcher = Executors.newSingleThreadExecutor();
	private Thread runner = new Thread( this, "Dispatcher" );
	private SystemCall current;
	
	public synchronized void run() {
		MultilevelQueue queue = Scheduler.getQueue();
		
		while( queue.size() > 0 ) {
			this.current = queue.nextCommand();
			
			this.dispatcher.execute( this.current );
			
			this.current.join();
		}
		
		this.dispatcher.shutdown();
	}
	
	synchronized boolean isAlive() {
		return runner.isAlive();
	}
	
	// For "fun" method (can implement if bored)
	synchronized void preempt( SystemCall task ) {
		if( this.runner.isAlive() ) {
			this.dispatcher.execute( task ); // Not sure if this will work
		}
	}
	
	synchronized void join() {
		try {
			this.runner.join();
		} catch( InterruptedException error ) {
		}
	}
	
	synchronized void start() {
		try {
			this.runner.start();
		} catch( IllegalMonitorStateException error ) {
		} catch( IllegalThreadStateException error ) {
		}
	}
	
}
