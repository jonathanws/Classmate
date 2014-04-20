package edu.towson.cosc.classmate;

import java.util.ArrayList;
import java.util.Random;

import android.app.ListActivity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

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
		
		messages.add(new Message(true, "Fuck man, she's still talking"));
		messages.add(new Message(true, "She does this literally every class"));
		messages.add(new Message(false, "you'd think she'd catch on by now"));
		messages.add(new Message(true, "fat chance, she can't tell that we don't pay attention"));
		messages.add(new Message(false, "truth.  Susq after this?"));
		messages.add(new Message(true, "If it ever finishes, sure"));
		
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
	
	// Call to initiate a thread (AsyncTask)
	public void sendMessage(View v) {
		String newMessage = text.getText().toString().trim(); 
		if(newMessage.length() > 0) {
			text.setText("");
			addNewMessage(new Message(true, newMessage));
			new SendMessage().execute();
		}
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
