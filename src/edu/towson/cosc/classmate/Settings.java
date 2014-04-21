package edu.towson.cosc.classmate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Settings extends PreferenceActivity implements OnSharedPreferenceChangeListener {

	public static final String PREF = "preference_";
	
	public static final String KEY_TYPEFACE = PREF + "typeface";
	public static final String KEY_THEME =  PREF + "theme";

	public static final int DARK_THEME = android.R.style.Theme_Holo;
	public static final int LIGHT_THEME = android.R.style.Theme_Holo_Light;
	public static int current_theme = DARK_THEME;

	@Deprecated
	@Override
	protected void onCreate(Bundle paramBundle) {
		setTheme(current_theme);
		super.onCreate(paramBundle);

		getActionBar().setDisplayHomeAsUpEnabled(true);
		addPreferencesFromResource(R.xml.preferences);
		getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
		setSummaries();

		// Implement a long click listener for select elements
		// http://kmansoft.com/2011/08/29/implementing-long-clickable-preferences/
		ListView listView = getListView();
        listView.setOnItemLongClickListener(new OnItemLongClickListener() {
        	
        	// This listener is more of a little easter egg
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                ListView listView = (ListView) parent;
                ListAdapter listAdapter = listView.getAdapter();
                Object obj = listAdapter.getItem(position);
                
                if (obj != null && obj instanceof View.OnLongClickListener) {
                    View.OnLongClickListener longListener = (View.OnLongClickListener) obj;
                    return longListener.onLongClick(view);
                }
                return false;
            }
        });
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			Intent mainIntent = new Intent(this, HomeActivity.class);
			mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(mainIntent);
			finish();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Deprecated
	@Override
	protected void onPause() {
		super.onPause();
		getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
	}

	@Deprecated
	@Override
	protected void onResume() {
		super.onResume();
		setSummaries();
		getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
	}

	public void onSharedPreferenceChanged(SharedPreferences sp, String key) {
		setSummaries();
	}

	@Deprecated
	private void setSummaries() {

		SharedPreferences sp = getPreferenceScreen().getSharedPreferences();

		// Instantiation
		CheckBoxPreference cbp_typeface = (CheckBoxPreference) findPreference(KEY_TYPEFACE);

		// Read values
		boolean boolean_typeface = sp.getBoolean(KEY_TYPEFACE, true);

		// Set Summaries
		cbp_typeface.setChecked(boolean_typeface);
	}

//	private String capitalizeFirstLetter(String paramString) {
//		if (paramString.equalsIgnoreCase(""))
//			return "- - -";
//		else {
//			StringBuilder sb = new StringBuilder(paramString);
//			sb.setCharAt(0, Character.toUpperCase(sb.charAt(0)));
//			return sb.toString();
//		}
//	}

	// http://stackoverflow.com/questions/11251901/check-whether-database-is-empty
//	protected boolean isDBEmpty() {
//		DatabaseListJSONData m_dbListData = new DatabaseListJSONData(this);
//		SQLiteDatabase db = m_dbListData.getWritableDatabase();
//		
//		Cursor cur = db.rawQuery("SELECT * FROM " + DatabaseConstants.TABLE_NAME, null);
//		if (cur.moveToFirst())
//			return false;
//		else
//			return true;
//	}

}