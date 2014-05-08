package edu.towson.cosc.classmate;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.BroadcastReceiver;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.database.Cursor;
import android.graphics.Color;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.text.format.Formatter;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;
import edu.towson.cosc.classmate.aggregator.DatabaseAdapter;

public class HomeActivity extends ListActivity {

	protected EditText et_message;
	protected View v_bottomBar;
	protected ViewFlipper v_flipper;
	private NetworkThread connection = new NetworkThread(this);
	private BroadcastReceiver receiver;
	
	private MyCursorAdapter cursorAdapter;

	private static final int MESSAGE_OPTIONS = 625;
	private static final int MESSAGE_INFO = 18;

	@Override
	protected synchronized void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.layout_main);

		SystemInterface.openDatabase(this);
		Settings.setHomeActivity(this);

		// Set FIRST_RUN variable to false now
		setFirstRunToFalse();
		init();

		this.connection.start();

		scrollListViewToBottom();
	}

	@Override
	protected void onResume() {
		super.onResume();
		// Generate ListView from SQLite Database
		SystemInterface.displayAllMessages(this);
		setUpTitle();
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		unregisterReceiver(receiver);
	}

	protected void setFirstRunToFalse() {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		Editor ed = sp.edit();
		ed.putBoolean(Settings.KEY_FIRST_RUN, false);
		ed.commit();
	}

	protected void init() {
		et_message = (EditText) this.findViewById(R.id.et_text);
		v_bottomBar = (View) this.findViewById(R.id.bottom_view_bar);
		v_flipper = (ViewFlipper) this.findViewById(R.id.vf_priority);

		int[] priorities = { R.drawable.ic_priority_withbox_1, R.drawable.ic_priority_withbox_2, R.drawable.ic_priority_withbox_3 };

		for (int i = 0; i < priorities.length; i++) {
			ImageView image = new ImageView(getApplicationContext());
			image.setBackgroundResource(priorities[i]);
			v_flipper.addView(image);
		}
		
		// Code for the receiver
		IntentFilter filter = new IntentFilter();
		filter.addAction(WifiManager.NETWORK_STATE_CHANGED_ACTION);

		receiver = new BroadcastReceiver() {
			@Override
			public void onReceive(Context context, Intent intent) {
				setUpTitle();
			}
		};
		registerReceiver(receiver, filter);
		
		// Listener for long clicks
		getListView().setOnItemLongClickListener(new OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> arg0, View view, int pos, long id) {
				createDialog(view, MESSAGE_OPTIONS);
				return true;
			}
		});

	}
	
	private void createDialog(final View v, int flag) {
		
		AlertDialog.Builder alert = new AlertDialog.Builder(this);
		alert.setCancelable(true);
		
		final HomeActivity ha = this;
		
		switch(flag) {
			case MESSAGE_OPTIONS:
				alert.setTitle("Message options");
				alert.setItems(R.array.message_details_array, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						if (which == 0) { // Copy
							TextView oldMessage = (TextView) v.findViewById(R.id.row_message);
							copyToClipboard(oldMessage.getText().toString());
						} else if(which == 1) { // Delete
							TextView oldId = (TextView) v.findViewById(R.id.row_rowid);
							SystemInterface.delete(ha, Long.parseLong(oldId.getText().toString()));
						} else if(which == 2) { // Message details
							createDialog(v, MESSAGE_INFO);
							
						}
					}
				});
				break;
				
			case MESSAGE_INFO:
				
				// Old view
				TextView oldMessage = (TextView) v.findViewById(R.id.row_message);
				TextView oldName = (TextView) v.findViewById(R.id.row_name);
				TextView oldPriority = (TextView) v.findViewById(R.id.row_priorityGONE);
				TextView oldTimestamp = (TextView) v.findViewById(R.id.row_timestamp);
				TextView oldId = (TextView) v.findViewById(R.id.row_rowid);
				
				String sOldMessage = oldMessage.getText().toString();
				String sOldName = oldName.getText().toString();
				String sOldPriority = oldPriority.getText().toString();
				String sOldTimestamp = oldTimestamp.getText().toString();
				String sOldId = oldId.getText().toString();
				
				// New view
				View view = null;
				
				// Base initialization
				view = this.getLayoutInflater().inflate(R.layout.dialog_messageinfo_low, null);
				
				if (sOldPriority.contains("3"))
					view = this.getLayoutInflater().inflate(R.layout.dialog_messageinfo_high, null);
				else if (sOldPriority.contains("2"))
					view = this.getLayoutInflater().inflate(R.layout.dialog_messageinfo_medium, null);
				else
					view = this.getLayoutInflater().inflate(R.layout.dialog_messageinfo_low, null);
				
				TextView message = (TextView) view.findViewById(R.id.dialog_message);
				TextView name = (TextView) view.findViewById(R.id.dialog_name);
				TextView timestamp = (TextView) view.findViewById(R.id.dialog_timestamp);
				TextView id = (TextView) view.findViewById(R.id.dialog_id);
				
				message.setText(sOldMessage);
				name.setText("From: " + sOldName);
				timestamp.setText(sOldTimestamp);
				id.setText("Row: " + sOldId);
				
			    alert.setView(view);
		}
        
        alert.create();
        alert.show();
	}
	
	private void copyToClipboard(String text) {
		ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
		ClipData clip = ClipData.newPlainText(Settings.TAG, text);
		clipboard.setPrimaryClip(clip);
		Toast.makeText(getApplicationContext(), "Copied to clipboard", Toast.LENGTH_LONG).show();
	}

	protected void setUpTitle() {
		this.setTitle(getNetworkName());
	}

	protected String getNetworkName() {
		String ssid = "";
		WifiManager wifiMgr = (WifiManager) getSystemService(Context.WIFI_SERVICE);
		if (wifiMgr.isWifiEnabled()) {
			WifiInfo wifiInfo = wifiMgr.getConnectionInfo();
			ssid = wifiInfo.getSSID();
		} else
			ssid = "Wifi disabled";

		// Remove quotations from string
		ssid = ssid.replace("\"", "");

		return ssid;
	}

	public void displayListView(DatabaseAdapter dataAdapter) {
		final ListView listview = getListView();
		final Cursor c = dataAdapter.selectStar();
		final HomeActivity $this = this;

		runOnUiThread(new Runnable() {
			public void run() {
				cursorAdapter = new MyCursorAdapter($this, c);
				cursorAdapter.notifyDataSetChanged();

				listview.setAdapter(cursorAdapter);
				scrollListViewToBottom();
			}
		});
	}

	// http://stackoverflow.com/a/7032341/1097170
	public void scrollListViewToBottom() {
		getListView().post(new Runnable() {

			@Override
			public void run() {
				// Select the last row so it will scroll into view...
				try {
					getListView().setSelection(cursorAdapter.getCount() - 1);
				} catch (NullPointerException error) {

				}
			}
		});
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
			// This would be moved to before the super() call in the Settings()
			// class, but
			// apparently you cannot make a SharedPreferences call before the
			// super() call.
			SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());

			if (sp.getBoolean(Settings.KEY_THEME, true)) {
				Settings.current_theme = Settings.DARK_THEME;
			} else {
				Settings.current_theme = Settings.LIGHT_THEME;
			}

			Intent settingsIntent = new Intent(this, Settings.class);
			this.startActivity(settingsIntent);

			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	// Called when users wants to send a message
	public void onSend(View v) {
		String message = et_message.getText().toString().trim();

		if (message.length() > 0) {
			et_message.setText("");

			SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
			String name = prefs.getString(Settings.KEY_NAME, "Default User");

			Message m = new Message(true, message, name, getLocalIpAddress(), getPriority());
			SystemInterface.send(this, m);

			scrollListViewToBottom();
		}

	}
	
	//TODO

	public NetworkThread getConnection() {
		return this.connection;
	}

	// TOASTS
	public void popToast(final String str) {
		runOnUiThread(new Runnable() {
			public void run() {
				Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
			}
		});
	}

	// Called when user wants to set the priority of their message
	@Deprecated
	public void setPriority(View v) {
		v_flipper.showNext();

		if (v_flipper.getDisplayedChild() == 0) {
			v_bottomBar.setBackgroundColor(Color.parseColor("#669900"));
		} else if (v_flipper.getDisplayedChild() == 1) {
			v_bottomBar.setBackgroundColor(Color.parseColor("#FF8800"));
		} else if (v_flipper.getDisplayedChild() == 2) {
			v_bottomBar.setBackgroundColor(Color.parseColor("#CC0000"));
		}
	}

	public int getPriority() {
		if (v_flipper.getDisplayedChild() == 0)
			return 1;
		else if (v_flipper.getDisplayedChild() == 1)
			return 2;
		else
			return 3;
	}
	
	@Deprecated
	public String getLocalIpAddress() {
		WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
		return Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
	}

}
