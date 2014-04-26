package edu.towson.cosc.classmate.scheduler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Dispatcher implements Runnable {
	
	private ExecutorService dispatcher = Executors.newSingleThreadExecutor();
	private Thread runner = new Thread( this, "Dispatcher" );
	private SystemCall current;
	
	public synchronized void run() {
		MultilevelQueue queue = Scheduler.getQueue();
		
		while( queue.getCount() > 0 ) {
			this.current = queue.nextCommand();
			
			dispatcher.execute( current );
			
			current.join();
		}
		
		dispatcher.shutdown();
	}
	
	public boolean isAlive() {
		return runner.isAlive();
	}
	
	// For "fun" method (can implement if bored)
	public synchronized void preempt( SystemCall task ) {
		try {
			dispatcher.wait();
			dispatcher.execute( task );
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
		this.runner.start();
	}
	
}
