package edu.towson.cosc.classmate.scheduler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Dispatcher implements Runnable {
	
	private ExecutorService dispatcher = Executors.newSingleThreadExecutor();
	private Thread runner = new Thread( this, "Dispatcher" );
	
	public synchronized void run() {
		MultilevelQueue queue = Scheduler.getQueue();
		
		while( queue.getCount() > 0 ) {
			SystemCall next = queue.nextCommand();
			
			dispatcher.execute( next );
			
			next.join();
		}
	}
	
	// For "fun" method (can implement if bored)
	public synchronized void preempt( SystemCall task ) {
		
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
