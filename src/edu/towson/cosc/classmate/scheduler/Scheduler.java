package edu.towson.cosc.classmate.scheduler;

import android.util.Log;
import edu.towson.cosc.classmate.HomeActivity;
import edu.towson.cosc.classmate.Message;

public class Scheduler {
	
	private static Dispatcher dispatcher = new Dispatcher();
	private static MultilevelQueue queue = new MultilevelQueue();
	
	// Schedule Methods
	public static boolean delete( HomeActivity home, int index ) {
		DeleteMessage call = new DeleteMessage( home, index );
		if( queue.schedule( call ) ) {
			notifyDispatcher();
			return true;
		}
		
		return false;
	}
	
	public static boolean display( HomeActivity home, int index ) {
		DisplayMessage call = new DisplayMessage( home, index );
		if( queue.schedule( call ) ) {
			notifyDispatcher();
			return true;
		}
		
		return false;
	}
	
	public static boolean displayAllMessages( HomeActivity home ) {
		DisplayAllMessages call = new DisplayAllMessages( home );
		if( queue.schedule( call ) ) {
			notifyDispatcher();
			return true;
		}
		
		return false;
	}
	
	public static boolean receive( HomeActivity home, Message msg ) {
		ReceiveMessage call = new ReceiveMessage( home, msg );
		if( queue.schedule( call ) ) {
			notifyDispatcher();
			return true;
		}
		
		return false;
	}
	
	public static boolean send( HomeActivity home, Message msg ) {
		Log.d( "ALERT MUTHAFUCKA", "SCHEDULER.SEND MUTHAFUCKA" );
		
		SendMessage call = new SendMessage( home, msg );
		if( queue.schedule( call ) ) {
			notifyDispatcher();
			return true;
		}
		
		return false;
	}
	
	// Getter Method(s)
	public synchronized static MultilevelQueue getQueue() {
		return Scheduler.queue;
	}
	
	public synchronized static Dispatcher getDispatcher() {
		return Scheduler.dispatcher;
	}
	
	// Notify Dispatcher that SystemCall has been queued
	public synchronized static void notifyDispatcher() {
		Log.d( "ALERT MUTHAFUCKA", "DISPATCHER NOTIFIED MUTHAFUCKA" );
		
		if( !dispatcher.isAlive() ) {
			dispatcher.start();
			dispatcher = new Dispatcher();
		}
	}
}
