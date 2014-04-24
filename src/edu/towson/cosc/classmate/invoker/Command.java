package edu.towson.cosc.classmate.invoker;

public interface Command {
	
	public Object execute();
	
	public Object redo();
	
	public Object undo();
	
}
