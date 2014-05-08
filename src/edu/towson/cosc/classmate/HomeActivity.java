package edu.towson.cosc.classmate;

import android.app.ListActivity;
import android.content.Context;
import android.content.Intent;
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
import android.widget.Toast;
import android.widget.ViewFlipper;
import edu.towson.cosc.classmate.aggregator.DatabaseAdapter;

public class HomeActivity extends ListActivity {

	protected EditText et_message;
	protected View v_bottomBar;
	protected ViewFlipper v_flipper;

	private MyCursorAdapter cursorAdapter;

	private NetworkThread connection = new NetworkThread(this);

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

		getListView().setOnItemLongClickListener(new OnItemLongClickListener() {

			public boolean onItemLongClick(AdapterView<?> arg0, View arg1, int pos, long id) {
				Toast.makeText(getApplicationContext(), "sweet deal, " + pos, Toast.LENGTH_SHORT).show();
				return true;
			}
		});

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

	public String getLocalIpAddress() {
		WifiManager wm = (WifiManager) getSystemService(WIFI_SERVICE);
		return Formatter.formatIpAddress(wm.getConnectionInfo().getIpAddress());
	}

}
