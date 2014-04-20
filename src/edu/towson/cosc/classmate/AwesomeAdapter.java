package edu.towson.cosc.classmate;

import java.util.ArrayList;

import android.content.Context;
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
			convertView = LayoutInflater.from(mContext).inflate(R.layout.message_row, parent, false);
			holder.message = (TextView) convertView.findViewById(R.id.message_text);
			convertView.setTag(holder);
		} else
			holder = (ViewHolder) convertView.getTag();

		holder.message.setText(message.getMessage());

		LayoutParams lp = (LayoutParams) holder.message.getLayoutParams();
		
		if (message.isMine()) {
			holder.message.setBackgroundResource(android.R.color.holo_orange_light);
			lp.gravity = Gravity.RIGHT;
		} else {
			holder.message.setBackgroundResource(android.R.color.holo_blue_bright);
			lp.gravity = Gravity.LEFT;
		}
		
		holder.message.setLayoutParams(lp);
//		holder.message.setTextColor(Color.WHITE))); //TODO there's no real reason why this shouldn't work...
		
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