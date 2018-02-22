package com.natevaughan.exec;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;

import static org.junit.Assert.assertTrue;

public class NamedExecutorServiceSpec {

	private final Logger log = LoggerFactory.getLogger(getClass());

	/**
	 * This is not a test
	 *
	 * TODO: Use assertion not logigng to make sure threads are named appropriately
	 */
	@Test
	public void verifyThreadNameThroughLogging() throws InterruptedException, ExecutionException {
		ExecutorService exec = NamedExecutors.newSingleThreadExecutor("foo");
		List<Callable<Boolean>> tasks = new ArrayList<>();
		tasks.add(() -> {
			log.info("some log statement");
			return true;
		});
		List<Future<Boolean>> futures = exec.invokeAll(tasks);
		for (Future<Boolean> future : futures) {
			assertTrue(future.get());
		}
	}
}