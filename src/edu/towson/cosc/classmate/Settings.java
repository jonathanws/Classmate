package edu.towson.cosc.classmate;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.OnSharedPreferenceChangeListener;
import android.os.Bundle;
import android.preference.CheckBoxPreference;
import android.preference.EditTextPreference;
import android.preference.Preference;
import android.preference.PreferenceActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ListAdapter;
import android.widget.ListView;

public class Settings extends PreferenceActivity implements OnSharedPreferenceChangeListener {
	
	public static final String TAG = "river"; // For Android logs
	public static final boolean MODE_SEND = true;
	public static final boolean MODE_RECEIVE = false;
	public static final String PREF = "preference_";
	
	public static final String KEY_FIRST_RUN = PREF + "first_run";
	public static final String KEY_DELETE = PREF + "delete";
	public static final String KEY_FINALTEST = PREF + "finaltestcase";
	public static final String KEY_NAME = PREF + "name";
	public static final String KEY_PRIORITY = PREF + "priority";
	public static final String KEY_SCHEDULING = PREF + "scheduling";
	public static final String KEY_SPLASH = PREF + "splash";
	public static final String KEY_THEME = PREF + "theme";
	public static final String KEY_TOGGLE = PREF + "toggle";
	public static final String KEY_TYPEFACE = PREF + "typeface";
	
	public static final int DARK_THEME = android.R.style.Theme_Holo;
	public static final int LIGHT_THEME = android.R.style.Theme_Holo_Light;
	public static int current_theme = DARK_THEME;
	
	private static HomeActivity homeActivity;
	
	@Deprecated
	@Override
	protected void onCreate( Bundle paramBundle ) {
		setTheme( current_theme );
		super.onCreate( paramBundle );
		
		getActionBar().setDisplayHomeAsUpEnabled( true );
		addPreferencesFromResource( R.xml.preferences );
		getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener( this );
		setSummaries();
		
		// Implement a long click listener for select elements
		// http://kmansoft.com/2011/08/29/implementing-long-clickable-preferences/
		ListView listView = getListView();
		listView.setOnItemLongClickListener( new OnItemLongClickListener() {
			
			// This listener is more of a little easter egg
			@Override
			public boolean onItemLongClick( AdapterView<?> parent, View view, int position, long id ) {
				ListView listView = (ListView) parent;
				ListAdapter listAdapter = listView.getAdapter();
				Object obj = listAdapter.getItem( position );
				
				if( obj != null && obj instanceof View.OnLongClickListener ) {
					View.OnLongClickListener longListener = (View.OnLongClickListener) obj;
					return longListener.onLongClick( view );
				}
				return false;
			}
		} );
		
		Preference button = (Preference) findPreference( KEY_DELETE );
		button.setOnPreferenceClickListener( new Preference.OnPreferenceClickListener() {
			
			@Override
			public boolean onPreferenceClick( Preference arg0 ) {
				SystemInterface.deleteAll( homeActivity );
				return true;
			}
		} );
		
		Preference testcase_scheduling = (Preference) findPreference( KEY_SCHEDULING );
		testcase_scheduling.setOnPreferenceClickListener( new Preference.OnPreferenceClickListener() {
			
			@Override
			public boolean onPreferenceClick( Preference arg0 ) {
				TestCases.scheduling();
				finish();
				return true;
			}
		} );
		
		Preference testcase_priority = (Preference) findPreference( KEY_PRIORITY );
		testcase_priority.setOnPreferenceClickListener( new Preference.OnPreferenceClickListener() {
			
			@Override
			public boolean onPreferenceClick( Preference arg0 ) {
				TestCases.priorityScheduling();
				finish();
				return true;
			}
		} );
		
		Preference testcase_final = (Preference) findPreference( KEY_FINALTEST );
		testcase_final.setOnPreferenceClickListener( new Preference.OnPreferenceClickListener() {
			
			@Override
			public boolean onPreferenceClick( Preference arg0 ) {
				TestCases.finalTestCase();
				finish();
				return true;
			}
		} );
		
	}
	
	@Override
	public boolean onOptionsItemSelected( MenuItem item ) {
		switch( item.getItemId() ) {
			case android.R.id.home:
				Intent mainIntent = new Intent( this, HomeActivity.class );
				mainIntent.addFlags( Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK );
				startActivity( mainIntent );
				finish();
				return true;
		}
		return super.onOptionsItemSelected( item );
	}
	
	@Deprecated
	@Override
	protected void onPause() {
		super.onPause();
		getPreferenceScreen().getSharedPreferences().unregisterOnSharedPreferenceChangeListener( this );
	}
	
	@Deprecated
	@Override
	protected void onResume() {
		super.onResume();
		setSummaries();
		getPreferenceScreen().getSharedPreferences().registerOnSharedPreferenceChangeListener( this );
	}
	
	public void onSharedPreferenceChanged( SharedPreferences sp, String key ) {
		setSummaries();
	}
	
	@Deprecated
	private void setSummaries() {
		
		SharedPreferences sp = getPreferenceScreen().getSharedPreferences();
		
		// Instantiation
		CheckBoxPreference cbp_splash = (CheckBoxPreference) findPreference( KEY_SPLASH );
		CheckBoxPreference cbp_typeface = (CheckBoxPreference) findPreference( KEY_TYPEFACE );
		EditTextPreference etp_name = (EditTextPreference) findPreference( KEY_NAME );
		Preference p_toggle = (Preference) findPreference( KEY_TOGGLE );
		
		// Read values
		boolean boolean_splash = sp.getBoolean( KEY_SPLASH, true );
		boolean boolean_toggle = sp.getBoolean( KEY_TOGGLE, MODE_SEND );
		boolean boolean_typeface = sp.getBoolean( KEY_TYPEFACE, false );
		String string_name = sp.getString( KEY_NAME, "Slartibartfast" ); // Default if none found
		
		// Set Summaries
		cbp_splash.setChecked( boolean_splash );
		cbp_typeface.setChecked( boolean_typeface );
		etp_name.setSummary( capitalizeFirstLetter( string_name ) );
		if( boolean_toggle )
			p_toggle.setSummary( "Sending" );
		else
			p_toggle.setSummary( "Receiving" );
	}
	
	public static void setHomeActivity( HomeActivity home ) {
		homeActivity = home;
	}
	
	private String capitalizeFirstLetter( String paramString ) {
		if( paramString.equalsIgnoreCase( "" ) )
			return "- - -";
		else {
			StringBuilder sb = new StringBuilder( paramString );
			sb.setCharAt( 0, Character.toUpperCase( sb.charAt( 0 ) ) );
			return sb.toString();
		}
	}
	
}
