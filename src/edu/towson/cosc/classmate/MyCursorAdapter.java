package edu.towson.cosc.classmate;

import edu.towson.cosc.classmate.aggregator.DatabaseConstants;
import android.content.Context;
import android.database.Cursor;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class MyCursorAdapter extends CursorAdapter {
	
	private LayoutInflater mInflater;
	private Cursor c;
	
	public MyCursorAdapter(Context ctx, Cursor cur) {
		super(ctx, cur);
		mInflater = LayoutInflater.from(ctx);
		c = cur;
	}
	
	@Override
	public void bindView(View view, Context context, Cursor cursor) {
		ViewHolder holder = (ViewHolder) view.getTag();
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
