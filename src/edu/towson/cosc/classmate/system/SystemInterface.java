package edu.towson.cosc.classmate.system;

import java.text.Format;
import java.util.Queue;
import java.util.concurrent.ExecutorService;

import edu.towson.cosc.classmate.Message;

public class SystemInterface {
	
	private static boolean idle = false;
	private static Format returnFormat;
	
	// Some form of thread pool needed
	// TODO: Determine best data structure(s)
	// to use and/or implement our own.
	private static ExecutorService executor;
	
	private static MultilevelQueue queue = new MultilevelQueue();
	
	public static String[] delete( Message msg ) {
		return null;
	}
	
	public static String[] delete( Queue msgs ) {
		return null;
	}
	
	public static String[] displayMessage( int index ) {
		return null;
	}
	
	public static String[] displayAllMessages() {
		return null;
	}
	
	public static String[] receive( Message msg ) {
		return null;
	}
	
	public static String[] send( Message draft ) {
		return null;
	}
	
	public static String[] setFormat() {
		return null;
	}
	
	private static void executeNext() {
		
	}
	
}
