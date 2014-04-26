package edu.towson.cosc.classmate.invoker;

interface Command {
	
	public Object execute();
	
	public Object redo();
	
	public Object undo();
	
}
