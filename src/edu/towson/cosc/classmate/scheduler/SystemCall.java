package edu.towson.cosc.classmate.scheduler;


abstract class SystemCall implements Runnable {
	
	private Thread runner;
	
	// Constructor Method(s)
	public SystemCall() {
		this.runner = new Thread( this );
	}
	
	// Abstract Method(s)
	public abstract void run();
	
	// Thread Method(s)
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
	
	public void interrupt() throws InterruptedException {
		this.runner.wait();
	}
	
	public void resume() {
		this.runner.notify();
	}
	
}
