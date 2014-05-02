package edu.towson.cosc.classmate.invoker;

import static edu.towson.cosc.classmate.DatabaseConstants.DATABASE_NAME;
import static edu.towson.cosc.classmate.DatabaseConstants.DATABASE_VERSION;
import static edu.towson.cosc.classmate.DatabaseConstants.KEY_IP_ADDR;
import static edu.towson.cosc.classmate.DatabaseConstants.KEY_ISMINE;
import static edu.towson.cosc.classmate.DatabaseConstants.KEY_MESSAGE;
import static edu.towson.cosc.classmate.DatabaseConstants.KEY_NAME;
import static edu.towson.cosc.classmate.DatabaseConstants.KEY_PRIORITY;
import static edu.towson.cosc.classmate.DatabaseConstants.KEY_TIMESTAMP;
import static edu.towson.cosc.classmate.DatabaseConstants.TABLE_DRAFTS;
import static edu.towson.cosc.classmate.DatabaseConstants.TABLE_DRAFTS_CREATE;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import edu.towson.cosc.classmate.DatabaseConstants;

public class DatabaseAdapter_Drafts {
	
	private static DatabaseDrafts_Helper mDbHelper;
	private static SQLiteDatabase mDb;
	private static Context mCtx;
	
	
	// Nested inner class
	private static class DatabaseDrafts_Helper extends SQLiteOpenHelper {
		
		public DatabaseDrafts_Helper(Context ctx) {
			super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
		}
		
		@Override
		public void onCreate(SQLiteDatabase db) {
			db.execSQL(TABLE_DRAFTS_CREATE);
		}
		
		@Override
		public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
			db.execSQL("DROP TABLE IF EXISTS " + TABLE_DRAFTS);
			onCreate(db);
		}
		
	}
	
	
	
	public DatabaseAdapter_Drafts(Context ctx) {
		this.mCtx = ctx;
	}
	
	public DatabaseAdapter_Drafts open() throws SQLException {
		mDbHelper = new DatabaseDrafts_Helper(mCtx);
		mDb = mDbHelper.getWritableDatabase();
		return this;
	}
	
	public static void close() {
		if (mDbHelper != null)
			mDbHelper.close();
	}
	
	/**
	 * @param message
	 * @param ts
	 * @param ip
	 * @param priority
	 * @param isMine
	 * @param name
	 * @return
	 */
	public long createMessage(String message, String ts, String ip, int priority, int isMine, String name) {
		// There is no boolean datatype in SQLite.  Treat isMine as an integer boolean: 1:true, 0:false
		ContentValues initialValues = new ContentValues();		
		initialValues.put(KEY_MESSAGE, message);
		initialValues.put(KEY_TIMESTAMP, ts);
		initialValues.put(KEY_IP_ADDR, ip);
		initialValues.put(KEY_NAME, name);
		initialValues.put(KEY_PRIORITY, priority);
		initialValues.put(KEY_ISMINE, isMine);
		
		return mDb.insert(TABLE_DRAFTS, null, initialValues);
	}
	
	public void deleteAllMessages() {
		mDb.delete(TABLE_DRAFTS, null, null);
	}
	
	public void insertSomeMessages() {
		
		//message, timestamp, ip, priority, ismine, name
		
//		createMessage("lol",                        "1:20 pm", "192.168.0.1", 1, 1, "Dog");
//		createMessage("what",                       "1:20 pm", "192.168.0.2", 1, 0, "Cat");
//		createMessage("nothing",                    "1:21 pm", "192.168.0.1", 1, 1, "Dog");
//		createMessage("no what",                    "1:21 pm", "192.168.0.2", 2, 0, "Cat");
//		createMessage("don't worry about it",       "1:22 pm", "192.168.0.1", 1, 1, "Dog");
//		createMessage("DOG.",                       "1:22 pm", "192.168.0.2", 3, 0, "Cat");
//		createMessage("ok",                         "1:22 pm", "192.168.0.1", 1, 1, "Dog");
//		createMessage("I pooped in your litterbox", "1:23 pm", "192.168.0.1", 2, 1, "Dog");
////		
//		createMessage("lol2",                        "1:20 pm", "192.168.0.1", 1, 1, "Dog");
//		createMessage("what2",                       "1:20 pm", "192.168.0.2", 1, 0, "Cat");
//		createMessage("nothing2",                    "1:21 pm", "192.168.0.1", 1, 1, "Dog");
//		createMessage("no what2",                    "1:21 pm", "192.168.0.2", 2, 0, "Cat");
//		createMessage("don't worry about it2",       "1:22 pm", "192.168.0.1", 1, 1, "Dog");
//		createMessage("DOG.2",                       "1:22 pm", "192.168.0.2", 3, 0, "Cat");
//		createMessage("ok2",                         "1:22 pm", "192.168.0.1", 1, 1, "Dog");
//		createMessage("I pooped in your litterbox2", "1:23 pm", "192.168.0.1", 2, 1, "Dog");
	}
	
	// For debugging purposes. Toast this from HomeActivity.java
	public Cursor showAllTables() {
        String allTables = " SELECT name FROM sqlite_master " + " WHERE type='table'";
        return mDb.rawQuery(allTables, null);
    }
	
	// For debugging purposes. Toast this from HomeActivity.java
	public Cursor selectStar() {
		String selectStar = " SELECT * FROM " + DatabaseConstants.TABLE_DRAFTS;
		return mDb.rawQuery(selectStar, null);
	}

}