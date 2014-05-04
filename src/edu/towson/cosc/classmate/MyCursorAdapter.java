package edu.towson.cosc.classmate;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.graphics.Typeface;
import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;
import edu.towson.cosc.classmate.aggregator.DatabaseConstants;

public class MyCursorAdapter extends CursorAdapter {
	
	private static final int NUM_LAYOUTS = 7;
	private LayoutInflater mInflater;
	private Cursor c;
	
	public MyCursorAdapter(Context ctx, Cursor cur) {
		super(ctx, cur);
		mInflater = LayoutInflater.from(ctx);
		c = cur;
	}
	
	@Override
	public int getViewTypeCount() {
		return NUM_LAYOUTS;
	}
	
	@Override
	public int getItemViewType(int pos) {
		Cursor cursor = (Cursor) getItem(pos);
		return getItemViewType(cursor);
	}
	
	private int getItemViewType(Cursor cursor) {
        int type = 1;
        
        // type is used internally by the system, where it saves a cache
        
        // Messages sent by me
        if ((cursor.getString(cursor.getColumnIndex(DatabaseConstants.KEY_ISMINE)).equals("1")) &&
				(cursor.getInt(cursor.getColumnIndex(DatabaseConstants.KEY_PRIORITY)) + "").equals("1")) {
			type = 1;
			
		} else if ((cursor.getString(cursor.getColumnIndex(DatabaseConstants.KEY_ISMINE)).equals("1")) &&
				(cursor.getInt(cursor.getColumnIndex(DatabaseConstants.KEY_PRIORITY)) + "").equals("2")) {
			type = 2;
			
		} else if ((cursor.getString(cursor.getColumnIndex(DatabaseConstants.KEY_ISMINE)).equals("1")) &&
					(cursor.getInt(cursor.getColumnIndex(DatabaseConstants.KEY_PRIORITY)) + "").equals("3")) {
			type = 3;
			
		// Messages sent by somebody else
		} else if ((cursor.getString(cursor.getColumnIndex(DatabaseConstants.KEY_ISMINE)).equals("0")) &&
				(cursor.getInt(cursor.getColumnIndex(DatabaseConstants.KEY_PRIORITY)) + "").equals("1")) {
			type = 4;
			
		} else if ((cursor.getString(cursor.getColumnIndex(DatabaseConstants.KEY_ISMINE)).equals("0")) &&
				(cursor.getInt(cursor.getColumnIndex(DatabaseConstants.KEY_PRIORITY)) + "").equals("2")) {
			type = 5;
			
		} else if ((cursor.getString(cursor.getColumnIndex(DatabaseConstants.KEY_ISMINE)).equals("0")) &&
				(cursor.getInt(cursor.getColumnIndex(DatabaseConstants.KEY_PRIORITY)) + "").equals("3")) {
			type = 6;
		}
        
        return type;
        
    }
	
	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		ViewHolder holder = (ViewHolder) view.getTag();
		SharedPreferences sp = PreferenceManager.getDefaultSharedPreferences(context);
		
		if (sp.getBoolean(Settings.KEY_TYPEFACE, false)) {
			Typeface tf_light = Typeface.createFromAsset(context.getAssets(), "fonts/roboto_thin.ttf");
			holder.message.setTypeface(tf_light);
			holder.name.setTypeface(tf_light);
			holder.timestamp.setTypeface(tf_light);
		}
		
		holder.message.setText(cursor.getString(cursor.getColumnIndex(DatabaseConstants.KEY_MESSAGE)));
		holder.name.setText(cursor.getString(cursor.getColumnIndex(DatabaseConstants.KEY_NAME)));
		holder.timestamp.setText(cursor.getString(cursor.getColumnIndex(DatabaseConstants.KEY_TIMESTAMP)));
	}
	
	@Override
	public View newView(Context context, Cursor cursor, ViewGroup parent) {
		ViewHolder holder = new ViewHolder();
		View v = null;
		
		if ((cursor.getString(cursor.getColumnIndex(DatabaseConstants.KEY_ISMINE)).equals("1")) &&
				(cursor.getInt(cursor.getColumnIndex(DatabaseConstants.KEY_PRIORITY)) + "").equals("1")) {
			v = mInflater.inflate(R.layout.message_priorityasline_gravityright_low, parent, false);
			
		} else if ((cursor.getString(cursor.getColumnIndex(DatabaseConstants.KEY_ISMINE)).equals("1")) &&
				(cursor.getInt(cursor.getColumnIndex(DatabaseConstants.KEY_PRIORITY)) + "").equals("2")) {
			v = mInflater.inflate(R.layout.message_priorityasline_gravityright_medium, parent, false);
			
		} else if ((cursor.getString(cursor.getColumnIndex(DatabaseConstants.KEY_ISMINE)).equals("1")) &&
					(cursor.getInt(cursor.getColumnIndex(DatabaseConstants.KEY_PRIORITY)) + "").equals("3")) {
			v = mInflater.inflate(R.layout.message_priorityasline_gravityright_high, parent, false);
			
			
		} else if ((cursor.getString(cursor.getColumnIndex(DatabaseConstants.KEY_ISMINE)).equals("0")) &&
				(cursor.getInt(cursor.getColumnIndex(DatabaseConstants.KEY_PRIORITY)) + "").equals("1")) {
			v = mInflater.inflate(R.layout.message_priorityasline_gravityleft_low, parent, false);
			
		} else if ((cursor.getString(cursor.getColumnIndex(DatabaseConstants.KEY_ISMINE)).equals("0")) &&
				(cursor.getInt(cursor.getColumnIndex(DatabaseConstants.KEY_PRIORITY)) + "").equals("2")) {
			v = mInflater.inflate(R.layout.message_priorityasline_gravityleft_medium, parent, false);
			
		} else if ((cursor.getString(cursor.getColumnIndex(DatabaseConstants.KEY_ISMINE)).equals("0")) &&
				(cursor.getInt(cursor.getColumnIndex(DatabaseConstants.KEY_PRIORITY)) + "").equals("3")) {
			v = mInflater.inflate(R.layout.message_priorityasline_gravityleft_high, parent, false);
		}
		
		holder.message = (TextView) v.findViewById(R.id.row_message);
		holder.name = (TextView) v.findViewById(R.id.row_name);
		holder.timestamp = (TextView) v.findViewById(R.id.row_timestamp);
		
		v.setTag(holder);
		return v;
	}

	static class ViewHolder {
		View priority;
		TextView message;
		TextView name;
		TextView timestamp;
	}

}
