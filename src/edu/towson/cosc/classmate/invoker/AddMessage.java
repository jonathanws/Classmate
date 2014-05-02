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

class AddMessage implements Command {
	
	private final Message msg;
	private SQLiteDatabase mDb;
	
	AddMessage( Aggregator aggr, Message msg ) {
		this.msg = msg;
		this.mDb = aggr.getDatabase();
	}
	
	public synchronized Object execute() {
		// if( this.list.addMessage( this.msg ) ) {
		// return this.msg;
		// }
		
		// Do whatever to add to database
		ContentValues initialValues = new ContentValues();
		initialValues.put( KEY_MESSAGE, msg.getMessage() );
		initialValues.put( KEY_TIMESTAMP, msg.getTimestamp() );
		initialValues.put( KEY_IP_ADDR, msg.getIP() );
		initialValues.put( KEY_NAME, msg.getName() );
		initialValues.put( KEY_PRIORITY, msg.getPriority() );
		initialValues.put( KEY_ISMINE, msg.intIsMine() );
		
		long id = mDb.insert( TABLE_DRAFTS, null, initialValues );
		
		if( id != -1 ) {
			msg.setId( id );
			return msg;
		}
		
		return null;
	}
	
}
