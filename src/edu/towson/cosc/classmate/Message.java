package edu.towson.cosc.classmate;

import java.text.DateFormat;
import java.util.Date;

/**
 * Defines a new Message object. A message is anything that is sent, or
 * received.
 * 
 * @author jonathanws
 */
public class Message {
	
	private boolean mIsMine; // User = true, some shmoe = false
	private String mMessage;
	private String mTimestamp;
	private String mName;	
	private String mIP; // http://stackoverflow.com/questions/8677707/datatype-for-storing-ip-addresses-in-java
	private int mPriority;
	private long mId;
	
	public static final int HIGH_PRIORITY = 3;
	public static final int MEDIUM_PRIORITY = 2;
	public static final int LOW_PRIORITY = 1;
	
	// Constructor methods
	
	/**
	 * Constructor for a new message
	 * 
	 * @param isMine
	 *            Ownership of message. Either user(true) or some shmoe(false)
	 * @param message
	 *            Actual string of message
	 */
	public Message( boolean isMine, String message ) {
		this.mIsMine = isMine;
		this.mMessage = message;
		this.mPriority = LOW_PRIORITY;
	}
	
	/**
	 * Constructor for a new message
	 * 
	 * @param isMine
	 *            Ownership of message. Either user(true) or some shmoe(false)
	 * @param message
	 *            Actual string of message
	 * @param ip
	 *            IP of whomever
	 * @param priority
	 *            priority of message
	 */
	public Message( boolean isMine, String message, String ip, int priority ) {
		this.mIsMine = isMine;
		this.mMessage = message;
		this.mIP = ip;
		this.mPriority = priority;
	}
	
	/**
	 * Constructor for a new message
	 * 
	 * @param isMine
	 *            Ownership of message. Either user(true) or some shmoe(false)
	 * @param message
	 *            Actual string of message
	 * @param ip
	 *            IP of whomever
	 * @param timestamp
	 *            Date and time message was initialized.
	 */
	public Message( boolean isMine, String message, String ip, String timestamp ) {
		this.mIsMine = isMine;
		this.mMessage = message;
		this.mIP = ip;
		this.mTimestamp = timestamp;
	}
	
	/**
	 * Constructor for a new message
	 * 
	 * @param isMine
	 *            Ownership of message. Either user(true) or some shmoe(false)
	 * @param message
	 *            Actual string of message
	 * @param name
	 *            Name of the person sending the message
	 * @param ip
	 *            IP of whomever
	 * @param priority
	 *            int designating priority
	 */
	public Message (boolean isMine, String message, String name, String ip, int priority) {
		this.mIsMine = isMine;
		this.mMessage = message;
		this.mName = name;
		this.mIP = ip;
		this.mPriority = priority;
		this.mTimestamp = DateFormat.getTimeInstance(DateFormat.SHORT).format(new Date());
	}
	
	// Getters and Setters
	
	/** @return boolean for mIsMine */
	public boolean isMine() {
		return this.mIsMine;
	}
	
	public int intIsMine() {
		if (mIsMine)
			return 1;
		
		return 0;
	}
	
	/**
	 * @param status boolean for mIsMine
	 */
	public void setMine( boolean status ) {
		this.mIsMine = status;
	}
	
	/** @return String for message */
	public String getMessage() {
		return this.mMessage;
	}
	
	/**
	 * @param mes String for message
	 */
	public void setMessage( String mes ) {
		this.mMessage = mes;
	}
	
	/** @return String for timestamp */
	public String getTimestamp() {
		return this.mTimestamp;
	}
	
	/**
	 * @param timestamp String for timestamp
	 */
	public void setTimestamp( String timestamp ) {
		this.mTimestamp = timestamp;
	}
	
	/** @return int for IP address */
	public String getIP() {
		return this.mIP;
	}
	
	/**
	 * @param ip
	 *            int for IP address
	 */
	public void setIP( String ip ) {
		this.mIP = ip;
	}
	
	/** @return int for priority */
	public int getPriority() {
		return this.mPriority;
	}
	
	/**
	 * @param priority
	 *            must be one of three constant values
	 */
	public void setPriority( int priority ) {
		this.mPriority = priority;
	}
	
	/**
	 * @param name to set
	 */
	public void setName(String n) {
		this.mName = n;
	}
	
	/**
	 * @return name of sender of message
	 */
	public String getName() {
		return this.mName;
	}
	
	/**
	 * @param id to set to message
	 */
 	public void setId(long l) {
 		this.mId = l;
 	}
 	
 	/**
 	 * @return id of message
 	 */
 	public long getId() {
 		return this.mId;
 	}
	
}
