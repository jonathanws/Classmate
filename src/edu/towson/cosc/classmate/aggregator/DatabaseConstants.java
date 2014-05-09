package edu.towson.cosc.classmate.aggregator;

import android.provider.BaseColumns;

public interface DatabaseConstants extends BaseColumns {
	
	public static final String DATABASE_NAME = "river.db";
	public static final int DATABASE_VERSION = 1;
	
	public static final String TABLE_MESSAGES = "messages_table";
	public static final String TABLE_DRAFTS = "drafts_table";
	
	public static final String KEY_ROWID = "_id";
	public static final String KEY_NAME = "name";
	public static final String KEY_MESSAGE = "message";
	public static final String KEY_TIMESTAMP = "timestamp";
	public static final String KEY_IP_ADDR = "ip_addr";
	public static final String KEY_PRIORITY = "priority";
	public static final String KEY_ISMINE = "ismine";
	
	public static final int PRIORITY_HIGH = 3;
	public static final int PRIORITY_MEDIUM = 2;
	public static final int PRIORITY_LOW = 1;
	
	public static final String TABLE_DRAFTS_CREATE = "CREATE TABLE if not exists " + TABLE_DRAFTS + " (" + KEY_ROWID + " integer PRIMARY KEY autoincrement, " + KEY_MESSAGE + ", " + KEY_TIMESTAMP + ", " + KEY_IP_ADDR + ", " + KEY_PRIORITY + ", " + KEY_ISMINE + ", " + KEY_NAME + ", " + " UNIQUE (" + KEY_ROWID + "));";
	// last line was
	// + " UNIQUE (" + KEY_MESSAGE + ", " + KEY_TIMESTAMP + "));";
	
}
