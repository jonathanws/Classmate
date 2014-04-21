package edu.towson.cosc.classmate;

/**
 * Defines a new Message object.  A message is anything that is sent, or received.
 * @author jonathanws
 */
public class Message {
	
	private boolean mIsMine; // User = true, some shmoe = false
	private String mMessage;
	private String mTimestamp;
	
	// Might not include this
	// Something about how to store IPs in java
	// http://stackoverflow.com/questions/8677707/datatype-for-storing-ip-addresses-in-java
	private String mIP;
	
	
	// Constructor methods
	
	/**
	 * Constructor for a new message
	 * @param isMine Ownership of message.  Either user(true) or some shmoe(false)
	 * @param message Actual string of message
	 */
	public Message(boolean isMine, String message) {
		this.mIsMine = isMine;
		this.mMessage = message;
	}
	
	/**
	 * Constructor for a new message
	 * @param isMine Ownership of message.  Either user(true) or some shmoe(false)
	 * @param message Actual string of message
	 * @param ip IP of whomever
	 */
	public Message(boolean isMine, String message, String ip) {
		this.mIsMine = isMine;
		this.mMessage = message;
		this.mIP = ip;
	}
	
	/**
	 * Constructor for a new message
	 * Constructor for a new message
	 * @param isMine Ownership of message.  Either user(true) or some shmoe(false)
	 * @param message Actual string of message
	 * @param ip IP of whomever
	 * @param timestamp Date and time message was initialized.
	 */
	public Message(boolean isMine, String message, String ip, String timestamp) {
		this.mIsMine = isMine;
		this.mMessage = message;
		this.mIP = ip;
		this.mTimestamp = timestamp;
	}
	
	// Getters and Setters
	
	/** @return boolean for mIsMine */
	public boolean isMine() {
		return this.mIsMine;
	}
	
	/** @param status boolean for mIsMine */
	public void setMine(boolean status) {
		this.mIsMine = status;
	}
	
	/** @return String for message */
	public String getMessage() {
		return this.mMessage;
	}
	
	/** @param mes String for message */
	public void setMessage(String mes) {
		this.mMessage = mes;
	}
	
	/** @return String for timestamp */
	public String getTimestamp() {
		return this.mTimestamp;
	}
	
	/** @param timestamp String for timestamp */
	public void setTimestamp(String timestamp) {
		this.mTimestamp = timestamp;
	}
	
	/** @return int for IP address */
	public String getIP() {
		return this.mIP;
	}
	
	/** @param ip int for IP address */
	public void setIP(String ip) {
		this.mIP = ip;
	}

}
