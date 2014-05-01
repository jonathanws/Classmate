package edu.towson.cosc.classmate;

import static android.provider.BaseColumns._ID;
import static edu.towson.cosc.classmate.DatabaseConstants.TABLE_DRAFTS;
import static edu.towson.cosc.classmate.DatabaseConstants.DATABASE_NAME;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAdapter_Messages extends SQLiteOpenHelper {
	
	// This class is primarily used to instantiate the database, and handle updates

	private static final String DATABASE_NAME = "river.db";
	private static final int DATABASE_VERSION = 1;

	// Create a helper object
	public DatabaseAdapter_Messages(Context ctx) {
		super(ctx, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// TABLE_DRAFTS comes from DatabaseConstants.java
	@Override
	public void onCreate(SQLiteDatabase db) {
//		db.execSQL(DATABASE_DRAFTS);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_DRAFTS);
		onCreate(db);
	}

}