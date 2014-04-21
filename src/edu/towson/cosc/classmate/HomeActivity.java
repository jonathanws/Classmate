package edu.towson.cosc.classmate;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Random;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

// Class defines activity where communication will take place
public class HomeActivity extends ListActivity {
	
	ArrayList<Message> messages;
	AwesomeAdapter adapter;
	EditText text;
	static Random rand = new Random();
	static String sender;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.home_draft_1); //TODO change me
		
		// Populate listview with an arraylist, which is in turn populated with messages
		
		text = (EditText) this.findViewById(R.id.message_text);
		
		sender = "Joe Shmoe";
		this.setTitle(sender);
		messages = new ArrayList<Message>();
		
		// Get current system time
		Calendar c = Calendar.getInstance(); 
		
		messages.add(new Message(true, "Fuck man, she's still talking", "192.168.0.42", getCurrentSystemTime()));
		messages.add(new Message(true, "She does this literally every class", "192.168.0.42", getCurrentSystemTime()));
		messages.add(new Message(false, "you'd think she'd catch on by now", "192.168.0.43", getCurrentSystemTime()));
		messages.add(new Message(true, "fat chance, she can't tell that we don't pay attention", "192.168.0.42", getCurrentSystemTime()));
		messages.add(new Message(false, "truth.  Susq after this?", "192.168.0.43", getCurrentSystemTime()));
		messages.add(new Message(true, "If it ever finishes, sure", "192.168.0.42", getCurrentSystemTime()));
		
		adapter = new AwesomeAdapter(this, messages);
		setListAdapter(adapter);
		
		// Demonstrate hot-swapping of messages in list
		addNewMessage(new Message(false, "foo"));
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

	// Call to initiate a thread (AsyncTask)
	public void sendMessage(View v) {
		String newMessage = text.getText().toString().trim(); 
		if(newMessage.length() > 0) {
			text.setText("");
			addNewMessage(new Message(true, newMessage));
			new SendMessage().execute();
		}
	}
	
	// Get current system time
	public String getCurrentSystemTime() {
		
		Calendar c = Calendar.getInstance();
		String time = "";
		String AM_PM = "";
		
		if (c.get(Calendar.AM_PM) == 1)
			AM_PM = "PM";
		else
			AM_PM = "AM";
		
		time = c.get(Calendar.HOUR) + ":" 
				+ c.get(Calendar.MINUTE) + " " 
				+ AM_PM;
		
		return time;
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
	
	void addNewMessage(Message m) {
		messages.add(m);
		adapter.notifyDataSetChanged();
		getListView().setSelection(messages.size()-1);
	}

}
