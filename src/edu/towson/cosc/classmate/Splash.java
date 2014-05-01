package edu.towson.cosc.classmate;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.widget.TextView;

public class Splash extends Activity {
	
	public static final int SLEEP_DURATION = 1000;
	
	@Override
	protected void onCreate(Bundle bundle) {
		super.onCreate(bundle);
		
		// Get instance of user settings
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(this);

		// If user wants a splash screen
		if (sp.getBoolean(Settings.KEY_SPLASH, true)) {
			setContentView(R.layout.layout_splash);
			init();
			runSplash();
		} else
			goTo(UserSetup.class);
	}

	private void runSplash() {
		Thread timer = new Thread() {
			@Override
			public void run() {
				try {
					sleep(SLEEP_DURATION);
				} catch(InterruptedException e) {
					e.printStackTrace();
				} finally {
					goTo(UserSetup.class);
				}
			}
		};
		timer.start();
	}

	private void init() {
		Typeface tf_light = Typeface.createFromAsset(getAssets(), "fonts/roboto_light.ttf");	
		TextView tv = (TextView) findViewById(R.id.tv_splash);
		tv.setTypeface(tf_light);
	}

	protected void goTo(Class<?> uri) {
		Intent intent = new Intent(Splash.this, uri);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
		finish();
	}

}