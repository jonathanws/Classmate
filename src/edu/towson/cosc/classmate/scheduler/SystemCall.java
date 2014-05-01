package edu.towson.cosc.classmate.scheduler;

public abstract class SystemCall implements Runnable {
	
	private Thread runner;
	
	public SystemCall() {
		this.runner = new Thread( this );
	}
	
	public abstract void run();
	
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
