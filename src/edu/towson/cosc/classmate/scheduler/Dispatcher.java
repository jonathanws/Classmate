package edu.towson.cosc.classmate.scheduler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Dispatcher implements Runnable {
	
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
	
	public boolean isAlive() {
		return runner.isAlive();
	}
	
	// For "fun" method (can implement if bored)
	public synchronized void preempt( SystemCall task ) {
		try {
			this.dispatcher.wait();
			this.dispatcher.execute( task );
		} catch( InterruptedException error ) {
			
		}
	}
	
	public void join() {
		try {
			this.runner.join();
		} catch( InterruptedException error ) {
			error.printStackTrace();
		}
	}
	
	public void start() {
		try {
			this.runner.start();
		} catch( IllegalMonitorStateException error ) {
			
		} catch( IllegalThreadStateException error ) {
			
		}
	}
	
}
