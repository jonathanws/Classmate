package edu.towson.cosc.classmate;

import java.util.ArrayList;

import android.content.Context;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

// Since we aren't using a vanilla listview, and we want to customize
// our messages, we need a custom listview
public class AwesomeAdapter extends BaseAdapter {
	
	private Context mContext; // In layman's terms, a flag to identify a thread
	private ArrayList<Message> mMessages;

	public AwesomeAdapter(Context context, ArrayList<Message> messages) {
		super();
		this.mContext = context;
		this.mMessages = messages;
	}

	@Override
	public int getCount() {
		return mMessages.size();
	}

	@Override
	public Object getItem(int position) {
		return mMessages.get(position);
	}

	// For every message in our listview, we are recycling 'message_row.xml'
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		Message message = (Message) this.getItem(position);

		ViewHolder holder;
		if (convertView == null) {
			holder = new ViewHolder();
			
			if (message.isMine())
				convertView = LayoutInflater.from(mContext).inflate(R.layout.message_row_gravity_right, parent, false);
			
			else
				convertView = LayoutInflater.from(mContext).inflate(R.layout.message_row_gravity_left, parent, false);
			
			holder.message = (TextView) convertView.findViewById(R.id.message_text);
			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();

		holder.message.setText(message.getMessage() + "\n" + message.getTimestamp() + " - " + message.getIP());

		LayoutParams lp = (LayoutParams) holder.message.getLayoutParams();
		
		if (message.isMine()) {
			holder.message.setBackgroundResource(android.R.color.holo_orange_dark);
			lp.gravity = Gravity.RIGHT;
			lp.leftMargin = 100;
		} else {
			holder.message.setBackgroundResource(android.R.color.holo_blue_dark);
			lp.gravity = Gravity.LEFT;
			lp.rightMargin = 100;
		}
		
		Typeface tf_light = Typeface.createFromAsset(mContext.getAssets(), "fonts/roboto_light.ttf");
		holder.message.setTypeface(tf_light);
		holder.message.setLayoutParams(lp);
		
		return convertView;
	}

	private static class ViewHolder {
		TextView message;
	}

	@Override
	public long getItemId(int position) {
		// Unimplemented, because we aren't using Sqlite.
		// ^^^^ uh, yeah we probably will.
		return position;
	}

}