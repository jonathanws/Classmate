//package edu.towson.cosc.classmate;
//
//import static android.provider.BaseColumns._ID;
//import static edu.towson.cosc.classmate.DatabaseConstants.TABLE_DRAFTS;
//import static edu.towson.cosc.classmate.DatabaseConstants.KEY_MESSAGE;
//import static edu.towson.cosc.classmate.DatabaseConstants.KEY_TIMESTAMP;
//import static edu.towson.cosc.classmate.DatabaseConstants.KEY_IP_ADDR;
//import static edu.towson.cosc.classmate.DatabaseConstants.KEY_NAME;
//import static edu.towson.cosc.classmate.DatabaseConstants.KEY_PRIORITY;
//import static edu.towson.cosc.classmate.DatabaseConstants.KEY_ISMINE;
//import static edu.towson.cosc.classmate.DatabaseConstants.TABLE_DRAFTS;
//import static edu.towson.cosc.classmate.DatabaseConstants.TABLE_DRAFTS_CREATE;
//import static edu.towson.cosc.classmate.DatabaseConstants.DATABASE_VERSION;
//import static edu.towson.cosc.classmate.DatabaseConstants.DATABASE_NAME;
//
//import android.content.ContentValues;
//import android.content.Context;
//import android.database.Cursor;
//import android.database.SQLException;
//import android.database.sqlite.SQLiteDatabase;
//import android.database.sqlite.SQLiteOpenHelper;
//
//public class DatabaseDrafts {
//	
//	// This class is primarily used to instantiate the database, and handle updates
//	
//	private DatabaseAdapter_Helper mDbHelper;
//	private SQLiteDatabase mDb;
//	private final Context mCtx;
//	
//	// Nested inner class
//		private static class DatabaseAdapter_Helper extends SQLiteOpenHelper {
//			
//			public DatabaseAdapter_Helper(Context ctx) {
//				super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
//			}
//			
//			@Override
//			public void onCreate(SQLiteDatabase db) {
//				db.execSQL(TABLE_DRAFTS_CREATE);
//			}
//			
//			@Override
//			public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//				db.execSQL("DROP TABLE IF EXISTS " + TABLE_DRAFTS);
//				onCreate(db);
//			}
//			
//		}
//
//	
//	public DatabaseDrafts(Context ctx) {
//		this.mCtx = ctx;
//	}
//	
//	public DatabaseDrafts open() throws SQLException {
//		mDbHelper = new DatabaseAdapter_Helper(mCtx);
//		mDb = mDbHelper.getWritableDatabase();
//		return this;
//	}
//	
//	public void close() {
//		if (mDbHelper != null)
//			mDbHelper.close();
//	}
//	
//	public long createMessage(String message, String ts, String ip, int priority, int isMine, String name) {
//		ContentValues initialValues = new ContentValues();
//		initialValues.put(KEY_MESSAGE, message);
//		initialValues.put(KEY_TIMESTAMP, ts);
//		initialValues.put(KEY_IP_ADDR, ip);
//		initialValues.put(KEY_NAME, name);
//		initialValues.put(KEY_PRIORITY, priority);
//		initialValues.put(KEY_ISMINE, isMine);
//		
//		return mDb.insert(TABLE_DRAFTS, null, initialValues);
//	}
//	
//	public void insertSomeEntries() {
//		
//		// message, timestamp, ip, priority, ismine, name
//		createMessage("lol",                        "1:20 pm", "192.168.0.1", 1, 1, "Dog");
//		createMessage("what",                       "1:20 pm", "192.168.0.2", 1, 0, "Cat");
//		createMessage("nothing",                    "1:21 pm", "192.168.0.1", 1, 1, "Dog");
//		createMessage("no what",                    "1:21 pm", "192.168.0.2", 2, 0, "Cat");
//		createMessage("don't worry about it",       "1:22 pm", "192.168.0.1", 1, 1, "Dog");
//		createMessage("Dog.",                       "1:22 pm", "192.168.0.2", 3, 0, "Cat");
//		createMessage("ok.",                        "1:22 pm", "192.168.0.1", 1, 1, "Dog");
//		createMessage("I pooped in your litterbox", "1:23 pm", "192.168.0.1", 2, 1, "Dog");
//		
//		createMessage("lol2",                        "1:20 pm", "192.168.0.1", 1, 1, "Dog");
//		createMessage("what2",                       "1:20 pm", "192.168.0.2", 1, 0, "Cat");
//		createMessage("nothing2",                    "1:21 pm", "192.168.0.1", 1, 1, "Dog");
//		createMessage("no what2",                    "1:21 pm", "192.168.0.2", 2, 0, "Cat");
//		createMessage("don't worry about it2",       "1:22 pm", "192.168.0.1", 1, 1, "Dog");
//		createMessage("Dog.2",                       "1:22 pm", "192.168.0.2", 3, 0, "Cat");
//		createMessage("ok.2",                        "1:22 pm", "192.168.0.1", 1, 1, "Dog");
//		createMessage("I pooped in your litterbox2", "1:23 pm", "192.168.0.1", 2, 1, "Dog");
//	}
//	
//	public void deleteAllEntries() {
//		mDb.delete(TABLE_DRAFTS, null, null);
//	}
//	
//	public Cursor selectStar() {
//		String s = "SELECT * FROM " + TABLE_DRAFTS;
//		return mDb.rawQuery(s, null);
//	}
//
//}