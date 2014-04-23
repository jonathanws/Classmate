package edu.towson.cosc.classmate.system;

public abstract class SystemCall implements Runnable {
	
	private Thread runner;
	
	public abstract void run();
	
}
