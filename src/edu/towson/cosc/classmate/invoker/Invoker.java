package edu.towson.cosc.classmate.invoker;

import java.util.Stack;

import edu.towson.cosc.classmate.Message;
import edu.towson.cosc.classmate.aggregator.Aggregator;
import edu.towson.cosc.classmate.aggregator.Conversation;

public class Invoker {
	
	private static Aggregator aggr = new Aggregator();
	
	// Undo and Redo stack variables
	private static Stack<Command> redo = new Stack<Command>();
	private static Stack<Command> undo = new Stack<Command>();
	private static int STACK_SIZE = 100;
	
	// Invoker commands for accessing the Aggregator
	public synchronized static Message addMessage( Message msg ) {
		return (Message) new AddMessage( aggr, msg ).execute();
	}
	
	public synchronized static Message deleteMessage( int index ) throws IndexOutOfBoundsException {
		Command action = new DeleteMessage( aggr, index );
		return (Message) action.execute();
	}
	
	public synchronized static Conversation getAllMessages() {
		return (Conversation) new GetMessageList( aggr ).execute();
	}
	
	public synchronized static Message getMessage( int index ) throws IndexOutOfBoundsException {
		return (Message) new GetMessage( aggr, index ).execute();
	}
	
	// Undo and Redo methods
	public static Object redo() {
		Command action;
		
		if( Invoker.redo.size() > 0 ) {
			action = Invoker.redo.pop();
			
			while( Invoker.undo.size() >= STACK_SIZE ) {
				Invoker.undo.removeElementAt( STACK_SIZE - 1 );
			}
			
			Invoker.undo.push( action );
			
			return action.redo();
		}
		
		return null;
	}
	
	public static Object undo() {
		Command action;
		
		if( Invoker.undo.size() > 0 ) {
			action = Invoker.undo.pop();
			
			while( Invoker.redo.size() >= STACK_SIZE ) {
				Invoker.redo.removeElementAt( STACK_SIZE - 1 );
			}
			
			Invoker.redo.push( action );
			
			return action.undo();
		}
		
		return null;
	}
	
}
