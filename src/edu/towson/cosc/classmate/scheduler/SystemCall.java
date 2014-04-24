package edu.towson.cosc.classmate.scheduler;

public abstract class SystemCall implements Runnable {
	
	private Thread runner;
	
	public abstract void run();
	
}
