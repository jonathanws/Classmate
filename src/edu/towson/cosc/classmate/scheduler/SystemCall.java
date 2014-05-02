package edu.towson.cosc.classmate.scheduler;

import android.os.Looper;
import edu.towson.cosc.classmate.HomeActivity;

abstract class SystemCall implements Runnable {
	
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
	
	public void updateListView( final HomeActivity home, String str ) {
		Looper.prepare();
		
		home.runOnUiThread( new Runnable() {
			
			@Override
			public void run() {
				home.displayListView();
			}
		} );
		
		if( str != null && !str.equals( "" ) ) {
			home.popToast( str );
		}
		
		Looper.loop();
	}
	
}
