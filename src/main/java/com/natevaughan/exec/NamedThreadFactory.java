package com.natevaughan.exec;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Adapted from
 * https://stackoverflow.com/questions/5740478/how-to-name-the-threads-of-a-thread-pool-in-java#5740531
 */
public class NamedThreadFactory implements ThreadFactory {

	private final String poolName;
	private static final String THREAD_NAME_PATTERN = "%s-%d";
	private final AtomicInteger counter = new AtomicInteger();

	public NamedThreadFactory(String poolName) {
		this.poolName = poolName;
	}

	@Override
	public Thread newThread(Runnable r) {
		final String threadName = String.format(THREAD_NAME_PATTERN, poolName, counter.incrementAndGet());
		return new Thread(r, threadName);
	}
}