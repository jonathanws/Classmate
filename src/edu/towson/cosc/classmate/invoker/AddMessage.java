package edu.towson.cosc.classmate.invoker;

import static edu.towson.cosc.classmate.aggregator.DatabaseConstants.KEY_IP_ADDR;
import static edu.towson.cosc.classmate.aggregator.DatabaseConstants.KEY_ISMINE;
import static edu.towson.cosc.classmate.aggregator.DatabaseConstants.KEY_MESSAGE;
import static edu.towson.cosc.classmate.aggregator.DatabaseConstants.KEY_NAME;
import static edu.towson.cosc.classmate.aggregator.DatabaseConstants.KEY_PRIORITY;
import static edu.towson.cosc.classmate.aggregator.DatabaseConstants.KEY_TIMESTAMP;
import static edu.towson.cosc.classmate.aggregator.DatabaseConstants.TABLE_DRAFTS;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import edu.towson.cosc.classmate.Message;
import edu.towson.cosc.classmate.aggregator.Aggregator;
import edu.towson.cosc.classmate.scheduler.SystemCall;

class AddMessage implements Command {
	
	private SQLiteDatabase mDb;
	private final Message msg;
	private SystemCall thread;
	
	AddMessage( Aggregator aggr, SystemCall thread, Message msg ) {
		this.mDb = aggr.getDatabase();
		this.msg = msg;
		this.thread = thread;
	}
	
	public Object execute() {
		ContentValues initialValues = new ContentValues();
		
		initialValues.put( KEY_MESSAGE, msg.getMessage() );
		initialValues.put( KEY_TIMESTAMP, msg.getTimestamp() );
		initialValues.put( KEY_IP_ADDR, msg.getIP() );
		initialValues.put( KEY_NAME, msg.getName() );
		initialValues.put( KEY_PRIORITY, msg.getPriority() );
		initialValues.put( KEY_ISMINE, msg.intIsMine() );
		
		Aggregator.request( thread );
		long id = this.mDb.insert( TABLE_DRAFTS, null, initialValues );
		Aggregator.signal();
		
		if( id != -1 ) {
			this.msg.setId( id );
			return this.msg;
		}
		
		return null;
	}
	
}
