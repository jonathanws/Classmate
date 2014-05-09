package edu.towson.cosc.classmate;

import android.content.SharedPreferences;
import android.preference.PreferenceManager;

public class TestCases {
	
	static HomeActivity ha;
	
	public static void setHomeActivity( HomeActivity home ) {
		ha = home;
	}
	
	// First one
	public static void scheduling() {
		SystemInterface.deleteAll( ha );
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( ha );
		String name = prefs.getString( Settings.KEY_NAME, "Slartibartfast" );
		
		SystemInterface.send( ha, new Message( true, "Lorem", name, 1 ) );
		SystemInterface.receive( ha, new Message( false, "Ipsum", name, 1 ) );
		
		SystemInterface.send( ha, new Message( true, "dolor", name, 1 ) );
		SystemInterface.receive( ha, new Message( false, "sit", name, 1 ) );
		
		SystemInterface.send( ha, new Message( true, "amet", name, 1 ) );
		SystemInterface.receive( ha, new Message( false, "consectetur", name, 1 ) );
	}
	
	public static void priorityScheduling() {
		SystemInterface.deleteAll( ha );
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( ha );
		String name = prefs.getString( Settings.KEY_NAME, "Slartibartfast" );
		
		SystemInterface.send( ha, new Message( true, "em	", name, 2 ) ); // Priority 2
		SystemInterface.send( ha, new Message( true, "gee", name, 3 ) ); // Priority 3
		SystemInterface.send( ha, new Message( true, "oh", name, 1 ) ); // Priority 1
	}
	
	public static void finalTestCase() {
		SystemInterface.deleteAll( ha );
		
		SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( ha );
		String name = prefs.getString( Settings.KEY_NAME, "Slartibartfast" );
		
		SystemInterface.receive( ha, new Message( false, "Hello" ) );
		SystemInterface.receive( ha, new Message( false, "Hello", name, 3 ) );
		SystemInterface.receive( ha, new Message( false, "Hello", "192.168.1.1", 2 ) );
		SystemInterface.receive( ha, new Message( false, "Hello" ) );
		SystemInterface.receive( ha, new Message( false, "Hello", "192.168.1.1", 2 ) );
		SystemInterface.receive( ha, new Message( false, "Hello" ) );
		
		SystemInterface.send( ha, new Message( true, "Hello" ) );
		SystemInterface.send( ha, new Message( true, "Hello", "192.168.1.1", 3 ) );
		SystemInterface.send( ha, new Message( true, "Hello", "192.168.1.1", 2 ) );
		SystemInterface.send( ha, new Message( true, "Hello" ) );
		SystemInterface.send( ha, new Message( true, "Hello", "192.168.1.1", 2 ) );
		SystemInterface.send( ha, new Message( true, "Hello" ) );
	}
	
}
