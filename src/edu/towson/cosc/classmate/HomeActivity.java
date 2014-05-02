package edu.towson.cosc.classmate;

import java.util.ArrayList;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.graphics.Color;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.format.Formatter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import edu.towson.cosc.classmate.invoker.DatabaseAdapter_Drafts;

public class HomeActivity extends ListActivity {
	
	ArrayList<Message> messages;
	protected EditText et_message;
	protected View v_bottomBar;
	protected ViewFlipper v_flipper;
	
	private DatabaseAdapter_Drafts dataAdapter;
	private MyCursorAdapter myCursorAdapter;
	
	@Override
	protected void onCreate( Bundle savedInstanceState ) {
		super.onCreate( savedInstanceState );
		
		// Open Database
		SystemInterface.openDatabase( this );
		
		setContentView( R.layout.layout_main ); // TODO change me
		
		// Set FIRST_RUN variable to false now
		setFirstRunToFalse();
		init();
		setUpTitle();
		
		dataAdapter = new DatabaseAdapter_Drafts( this );
		dataAdapter.open();
		
		// Clean all data
		// dataAdapter.deleteAllMessages();
		
		// Add some sample data
		dataAdapter.insertSomeMessages();
		
		// Log.d("Tag", showTableData());
		
		// Toast.makeText(getApplicationContext(), getLocalIpAddress(), Toast.LENGTH_LONG).show();
		
		// Generate ListView from SQLite Database
		displayListView();
		
		scrollListViewToBottom();
	}
	
	@Override
	protected void onResume() {
		super.onResume();
		setUpTitle();
		// ((BaseAdapter) getListView().getAdapter()).notifyDataSetChanged(); TODO
	}
	
	protected void setFirstRunToFalse() {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences( getBaseContext() );
		Editor ed = sp.edit();
		ed.putBoolean( Settings.KEY_FIRST_RUN, false );
		ed.commit();
	}
	
	protected void init() {
		et_message = (EditText) this.findViewById( R.id.et_text );
		v_bottomBar = (View) this.findViewById( R.id.bottom_view_bar );
		
		v_flipper = (ViewFlipper) this.findViewById( R.id.vf_priority );
		int[] priorities = { R.drawable.ic_priority_withbox_1, R.drawable.ic_priority_withbox_2, R.drawable.ic_priority_withbox_3 };
		for( int i = 0; i < priorities.length; i++ ) {
			ImageView image = new ImageView( getApplicationContext() );
			image.setBackgroundResource( priorities[i] );
			v_flipper.addView( image );
		}
		
	}
	
	protected void setUpTitle() {
		this.setTitle( getNetworkName() );
	}
	
	protected String getNetworkName() {
		String ssid = "";
		WifiManager wifiMgr = (WifiManager) getSystemService( Context.WIFI_SERVICE );
		if( wifiMgr.isWifiEnabled() ) {
			WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
			ssid = wifiInfo.getSSID();
		} else
			ssid = "Wifi disabled";
		
		// Remove quotations from string
		ssid = ssid.replace( "\"", "" );
		
		return ssid;
	}
	
	public synchronized void displayListView() {
		ListView listview = getListView();
		Cursor c = dataAdapter.selectStar();
		myCursorAdapter = new MyCursorAdapter( this, c );
		myCursorAdapter.notifyDataSetChanged();
		listview.setAdapter( myCursorAdapter );
		
		// listview.setOnItemLongClickListener(new OnItemLongClickListener() {
		// @Override
		// public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
		// // Cursor cursor = (Cursor) listview.getItemAtPosition(position);
		// // String countryCode = cursor.getString(cursor.getColumnIndexOrThrow("code"));
		//
		// Toast.makeText(getApplicationContext(), "Sweet, " + position, Toast.LENGTH_SHORT).show();
		// return false;
		// }
		// });
		
	}
	
	// http://stackoverflow.com/a/7032341/1097170
	public void scrollListViewToBottom() {
		getListView().post( new Runnable() {
			
			@Override
			public void run() {
				// Select the last row so it will scroll into view...
				getListView().setSelection( myCursorAdapter.getCount() - 1 );
			}
		} );
	}
	
	@Override
	public boolean onCreateOptionsMenu( Menu menu ) {
		getMenuInflater().inflate( R.menu.home, menu );
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected( MenuItem item ) {
		switch( item.getItemId() ) {
		
			case R.id.menu_settings:
				// This would be moved to before the super() call in the Settings() class, but
				// apparently you cannot make a SharedPreferences call before the super() call.
				SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences( getBaseContext() );
				if( sp.getBoolean( Settings.KEY_THEME, true ) )
					Settings.current_theme = Settings.DARK_THEME;
				else
					Settings.current_theme = Settings.LIGHT_THEME;
				
				Intent settingsIntent = new Intent( this, Settings.class );
				this.startActivity( settingsIntent );
				return true;
		}
		return super.onOptionsItemSelected( item );
	}
	
	// Called when users wants to send a message
	public void onSend( View v ) {
		String message = et_message.getText().toString().trim();
		if( message.length() > 0 ) {
			et_message.setText( "" );
			
			SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences( this );
			String name = prefs.getString( Settings.KEY_NAME, "Default User" );
			
			Message m = new Message( true, message, name, getLocalIpAddress(), getPriority() );
			
			SystemInterface.send( this, m );
			
			this.displayListView();
			scrollListViewToBottom();
		}
		
	}
	
	// TOASTS
	public void popToast( String str ) {
		Toast.makeText( getApplicationContext(), str, Toast.LENGTH_LONG ).show();
	}
	
	// Called when user wants to set the priority of their message
	@Deprecated
	public void setPriority( View v ) {
		v_flipper.showNext();
		
		if( v_flipper.getDisplayedChild() == 0 ) {
			v_bottomBar.setBackgroundColor( Color.parseColor( "#669900" ) );
		} else if( v_flipper.getDisplayedChild() == 1 ) {
			v_bottomBar.setBackgroundColor( Color.parseColor( "#FF8800" ) );
		} else if( v_flipper.getDisplayedChild() == 2 ) {
			v_bottomBar.setBackgroundColor( Color.parseColor( "#CC0000" ) );
		}
	}
	
	public int getPriority() {
		if( v_flipper.getDisplayedChild() == 0 )
			return 1;
		else if( v_flipper.getDisplayedChild() == 1 )
			return 2;
		else
			return 3;
	}
	
	// Call to initiate a thread (AsyncTask)
	public void sendMessage() {
		String newMessage = et_message.getText().toString().trim();
		if( newMessage.length() > 0 ) {
			et_message.setText( "" );
			addNewMessage( new Message( true, newMessage ) );
			new SendMessage().execute();
		}
	}
	
	// public String getCurrentSystemTime() {
	// return DateFormat.getTimeInstance(DateFormat.SHORT).format(new Date());
	// }
	
	public String getLocalIpAddress() {
		WifiManager wm = (WifiManager) getSystemService( WIFI_SERVICE );
		return Formatter.formatIpAddress( wm.getConnectionInfo().getIpAddress() );
	}
	
	void addNewMessage( Message m ) {
		messages.add( m );
		// adapter.notifyDataSetChanged();
		getListView().setSelection( messages.size() - 1 );
	}
	
	// For development purposes
	public void showSQLTables() {
		Cursor c = dataAdapter.showAllTables();
		String all = "";
		
		if( c.moveToFirst() ) {
			do {
				all = all + c.getString( 0 ) + ", ";
			} while( c.moveToNext() );
		}
		
		Toast.makeText( getApplicationContext(), all, Toast.LENGTH_LONG ).show();
	}
	
	// For development purposes
	public String showTableData() {
		Cursor c = dataAdapter.selectStar();
		return DatabaseUtils.dumpCursorToString( c );
	}
	
	// New thread to send a message
	private class SendMessage extends AsyncTask<Void, String, String> {
		
		@Override
		protected String doInBackground( Void... params ) {
			
			try {
				// Line below is from previous project. We'll probably remove this
				Thread.sleep( 2000 ); // simulate a network call
			} catch( InterruptedException e ) {
				e.printStackTrace();
			}
			
			this.publishProgress( String.format( "%s started writing", "Joe" ) );
			try {
				Thread.sleep( 2000 ); // simulate a network call
			} catch( InterruptedException e ) {
				e.printStackTrace();
			}
			this.publishProgress( String.format( "%s has entered text", "Joe" ) );
			try {
				Thread.sleep( 3000 );// simulate a network call
			} catch( InterruptedException e ) {
				e.printStackTrace();
			}
			
			return "nice";
		}
		
		@Override
		public void onProgressUpdate( String... v ) {
			// While thread is happening do...
			// Nothing
		}
		
		@Override
		protected void onPostExecute( String text ) {
			// Once thread has finished do...
			// Nothing
		}
		
	}
	
}
