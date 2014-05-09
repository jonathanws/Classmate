package edu.towson.cosc.classmate.aggregator;

import static edu.towson.cosc.classmate.aggregator.DatabaseConstants.DATABASE_NAME;
import static edu.towson.cosc.classmate.aggregator.DatabaseConstants.DATABASE_VERSION;
import static edu.towson.cosc.classmate.aggregator.DatabaseConstants.TABLE_DRAFTS;
import static edu.towson.cosc.classmate.aggregator.DatabaseConstants.TABLE_DRAFTS_CREATE;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

class DatabaseHelper extends SQLiteOpenHelper {
	
	public DatabaseHelper( Context ctx ) {
		super( ctx, DATABASE_NAME, null, DATABASE_VERSION );
	}
	
	@Override
	public void onCreate( SQLiteDatabase db ) {
		db.execSQL( TABLE_DRAFTS_CREATE );
	}
	
	@Override
	public void onUpgrade( SQLiteDatabase db, int oldVersion, int newVersion ) {
		db.execSQL( "DROP TABLE IF EXISTS " + TABLE_DRAFTS );
		onCreate( db );
	}
	
}
