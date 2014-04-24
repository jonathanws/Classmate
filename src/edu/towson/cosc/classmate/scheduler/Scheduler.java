package edu.towson.cosc.classmate.scheduler;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Scheduler {
	
	private static ExecutorService executor = Executors.newSingleThreadExecutor();
	private static MultilevelQueue queue = new MultilevelQueue();
	private static boolean idle = false;
	
}
