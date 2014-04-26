package edu.towson.cosc.classmate.scheduler;

import edu.towson.cosc.classmate.Message;

public class Scheduler {
	
	private static Dispatcher dispatcher = new Dispatcher();
	private static MultilevelQueue queue = new MultilevelQueue();
	
	public static boolean delete( Runnable thread, int index ) {
		DeleteMessage call = new DeleteMessage( index );
		if( queue.schedule( call ) ) {
			notifyDispatcher();
			return true;
		}
		return false;
	}
	
	public static boolean display( Runnable thread, int index ) {
		DisplayMessage call = new DisplayMessage( index );
		if( queue.schedule( call ) ) {
			notifyDispatcher();
			return true;
		}
		return false;
	}
	
	public static boolean displayAllMessages( Runnable thread ) {
		DisplayAllMessages call = new DisplayAllMessages();
		if( queue.schedule( call ) ) {
			notifyDispatcher();
			return true;
		}
		return false;
	}
	
	public static boolean receive( Runnable thread, Message msg ) {
		ReceiveMessage call = new ReceiveMessage( msg );
		if( queue.schedule( call ) ) {
			notifyDispatcher();
			return true;
		}
		return false;
	}
	
	public static boolean send( Runnable thread, Message msg ) {
		SendMessage call = new SendMessage( msg );
		if( queue.schedule( call ) ) {
			notifyDispatcher();
			return true;
		}
		return false;
	}
	
	public synchronized static MultilevelQueue getQueue() {
		return Scheduler.queue;
	}
	
	// Notify Dispatcher that SystemCall has been queued
	public synchronized static void notifyDispatcher() {
		if( !dispatcher.isAlive() ) {
			dispatcher.start();
		}
	}
}
