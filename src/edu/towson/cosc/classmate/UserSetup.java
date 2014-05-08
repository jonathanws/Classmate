package edu.towson.cosc.classmate;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UserSetup extends Activity {
	
	TextView tv_displayName, tv_details;
	EditText et_displayname;
	
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		
		setContentView(R.layout.layout_user_setup);
		init();
		
		// If a user has done this all already, proceed to main
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		if (!((sp.getBoolean(Settings.KEY_FIRST_RUN, true)) || (sp.getString(Settings.KEY_NAME, "null")) == "null")) {
			Intent mainIntent = new Intent(this, HomeActivity.class);
			mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(mainIntent);
			finish();
		}
	}
	
	protected void init() {
		
		// Set beautiful typefaces
		Typeface tf_light = Typeface.createFromAsset(getAssets(), "fonts/roboto_thin.ttf");
		
		tv_displayName = (TextView) findViewById(R.id.usersetup_tv_displayname);
		tv_details = (TextView) findViewById(R.id.usersetup_tv_details);
		
		et_displayname = (EditText) findViewById(R.id.usersetup_et_displayname);
		
		tv_displayName.setTypeface(tf_light);
		tv_details.setTypeface(tf_light);
	}
	
	// Verify all fields have text, and verify not only spaces
	protected boolean hasText() {
		if (!(et_displayname.getText().toString().trim().equals("")))
			return true;
		else {
			Toast.makeText(getApplicationContext(), "Blank name not allowed", Toast.LENGTH_SHORT).show();
			return false;
		}
	}
	
	protected void submit() {
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(getBaseContext());
		Editor ed = sp.edit();
		
		ed.putString(Settings.KEY_NAME, et_displayname.getText().toString().trim());
		ed.commit();
	}
	
	// Yes button was pushed
	public void yesButton(View v) {
		if (hasText()) {
			// Write name to memory, and take me to the main screen
			submit();
			Intent mainIntent = new Intent(this, HomeActivity.class);
			mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
			startActivity(mainIntent);
			finish();
		}
	}
	
	// No button was pushed
	public void noButton(View v) {
		// Just take me to the main screen
		Intent mainIntent = new Intent(this, HomeActivity.class);
		mainIntent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(mainIntent);
		finish();
	}
	
	

}
