package edu.towson.cosc.classmate;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

// Class defines activity where communication will take place
public class HomeActivity extends ListActivity {
	
	ArrayList<Message> messages;
	AwesomeAdapter adapter;
	EditText et_message;
	Button bu_priority;
	static Random rand = new Random();
	static String sender;
	
	private static final int PRIORITY_FLAG = 7; // Why not

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_draft_1); //TODO change me
		
		// Set FIRST_RUN variable to false now
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		Editor ed = sp.edit();
		ed.putBoolean(Settings.KEY_FIRST_RUN, false);
		ed.commit();
		
		// Populate listview with an arraylist, which is in turn populated with messages
		
		init();
		
		// Set title of screen to name of network
		this.setTitle(getNetworkName());
		messages = new ArrayList<Message>();
		
		messages.add(new Message(true, "Fuck man, she's still talking", sp.getString(Settings.KEY_NAME, "Slartibartfast"), getCurrentSystemTime()));
		messages.add(new Message(true, "She does this literally every class", sp.getString(Settings.KEY_NAME, "Slartibartfast"), getCurrentSystemTime()));
		messages.add(new Message(false, "you'd think she'd catch on by now", sp.getString(Settings.KEY_NAME, "Slartibartfast"), getCurrentSystemTime()));
		messages.add(new Message(true, "fat chance, she can't tell that we don't pay attention", sp.getString(Settings.KEY_NAME, "Slartibartfast"), getCurrentSystemTime()));
		messages.add(new Message(false, "truth.  Susq after this?", sp.getString(Settings.KEY_NAME, "Slartibartfast"), getCurrentSystemTime()));
		messages.add(new Message(true, "If it ever finishes, sure", sp.getString(Settings.KEY_NAME, "Slartibartfast"), getCurrentSystemTime()));
		
		adapter = new AwesomeAdapter(this, messages);
		setListAdapter(adapter);
		
		// Demonstrate hot-swapping of messages in list
		addNewMessage(new Message(false, "foo"));
	}
	
	protected void init() {
		et_message = (EditText) this.findViewById(R.id.message_text);
		bu_priority = (Button) this.findViewById(R.id.bu_priority);
	}
	
	protected String getNetworkName() {
		String ssid = "";
		WifiManager wifiMgr = (WifiManager)getSystemService(Context.WIFI_SERVICE);
		if (wifiMgr.isWifiEnabled()) {
			WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
			ssid = wifiInfo.getSSID();
		}
		return ssid;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.home, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {

			case R.id.menu_settings:
				// This would be moved to before the super() call in the Settings() class, but
				// apparently you cannot make a SharedPreferences call before the super() call.
				SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
				if (sp.getBoolean(Settings.KEY_THEME, true))
					Settings.current_theme = Settings.DARK_THEME;
				else
					Settings.current_theme = Settings.LIGHT_THEME;

				Intent settingsIntent = new Intent(this, Settings.class);
				this.startActivity(settingsIntent);
				return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	// Called when user wants to set the priority of their message
	public void setPriority(View v) {
		showDialog(PRIORITY_FLAG);
		Toast.makeText(getApplicationContext(), "got it", Toast.LENGTH_SHORT).show();
	}

	// Call to initiate a thread (AsyncTask)
	public void sendMessage(View v) {
		String newMessage = et_message.getText().toString().trim(); 
		if(newMessage.length() > 0) {
			et_message.setText("");
			addNewMessage(new Message(true, newMessage));
			new SendMessage().execute();
		}
	}
	
	public String getCurrentSystemTime() {
		return DateFormat.getTimeInstance(DateFormat.SHORT).format(new Date());
	}
	
	void addNewMessage(Message m) {
		messages.add(m);
		adapter.notifyDataSetChanged();
		getListView().setSelection(messages.size()-1);
	}
	
	@Deprecated
	@Override
	protected Dialog onCreateDialog(int id) {
		
		switch(id) {
			case PRIORITY_FLAG:
				return new AlertDialog.Builder(this) // Make a dialog
				.setTitle("Set message priority")
				.setItems(R.array.priority_array, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {						
						if (which == 0) {
							bu_priority.setBackgroundResource(R.drawable.ic_priority_withbox_1);
						}
						else if(which == 1) {
							bu_priority.setBackgroundResource(R.drawable.ic_priority_withbox_2);
						}
						else if(which == 2) {
							bu_priority.setBackgroundResource(R.drawable.ic_priority_withbox_3);
						}
					}
				})
				.create();
		}
		return super.onCreateDialog(id);
	}
	
	
	
	// New thread to send a message
	private class SendMessage extends AsyncTask<Void, String, String> {
		
		@Override
		protected String doInBackground(Void... params) {
			
			try {
				// Line below is from previous project.  We'll probably remove this
				Thread.sleep(2000); //simulate a network call
			}catch (InterruptedException e) {
				e.printStackTrace();
			}

			this.publishProgress(String.format("%s started writing", sender));
			try {
				Thread.sleep(2000); //simulate a network call
			}catch (InterruptedException e) {
				e.printStackTrace();
			}
			this.publishProgress(String.format("%s has entered text", sender));
			try {
				Thread.sleep(3000);//simulate a network call
			}catch (InterruptedException e) {
				e.printStackTrace();
			}

			return "nice";
		}
		
		@Override
		public void onProgressUpdate(String... v) {
			
			// While thread is happening do...
			
			// Nothing
			
		}
		@Override
		protected void onPostExecute(String text) {
			
			// Once thread has finished do...
			
			// Nothing
			
		}

	}

}
