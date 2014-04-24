package edu.towson.cosc.classmate.scheduler;

import edu.towson.cosc.classmate.Message;

public class Scheduler {
	
	private static Dispatcher dispatcher = new Dispatcher();
	private static MultilevelQueue queue = new MultilevelQueue();
	
	public static boolean delete( Runnable thread, int index ) {
		DeleteMessage call = new DeleteMessage( index );
		return toQueue( call );
	}
	
	public static boolean display( Runnable thread, int index ) {
		DisplayMessage call = new DisplayMessage( index );
		return toQueue( call );
	}
	
	public static boolean displayAllMessages( Runnable thread ) {
		DisplayAllMessages call = new DisplayAllMessages();
		return toQueue( call );
	}
	
	public static boolean receive( Runnable thread, Message msg ) {
		ReceiveMessage call = new ReceiveMessage( msg );
		return toQueue( call );
	}
	
	public static boolean send( Runnable thread, Message msg ) {
		SendMessage call = new SendMessage( msg );
		return toQueue( call );
	}
	
	public static MultilevelQueue getQueue() {
		return Scheduler.queue;
	}
	
	private static boolean toQueue( SystemCall call ) {
		if( queue.schedule( call ) ) {
			notifyDispatcher();
			return true;
		}
		
		return false;
	}
	
	public static void notifyDispatcher() {
		if( queue.getCount() == 1 ) {
			dispatcher.start();
		}
	}
}
