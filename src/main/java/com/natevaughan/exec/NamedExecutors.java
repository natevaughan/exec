package com.natevaughan.exec;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Static methods to make getting named executors simple
 */
public class NamedExecutors {

	public static ExecutorService newFixedThreadPool(int nThreads, String poolName) {
		return Executors.newFixedThreadPool(nThreads, new NamedThreadFactory(poolName));
	}

	public static ExecutorService newSingleThreadExecutor(String poolName) {
		return Executors.newSingleThreadExecutor(new NamedThreadFactory(poolName));
	}


	public static ExecutorService newCachedThreadPool(String poolName) {
		return new ThreadPoolExecutor(0, Integer.MAX_VALUE,
				60L, TimeUnit.SECONDS,
				new SynchronousQueue<Runnable>(),
				new NamedThreadFactory(poolName));
	}

	private NamedExecutors() {}
}
