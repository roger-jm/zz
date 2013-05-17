package thread.pool;

import static org.junit.Assert.*;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.apache.tomcat.util.threads.TaskQueue;
import org.junit.Assert;
import org.junit.Test;

public class TestThreadPool {

	@Test
	public void test() throws InterruptedException {
		final int min = 2;

		final int max = 4;

		final CountDownLatch active = new CountDownLatch(max + 1);

		/*ThreadPoolExecutor pool = (ThreadPoolExecutor) Executors.newCachedThreadPool();

		pool.setCorePoolSize(min);

		pool.setMaximumPoolSize(max);

		pool.setKeepAliveTime(1, TimeUnit.MILLISECONDS);*/
		
		ThreadPoolExecutor pool = new ThreadPoolExecutor(min, max, 1, TimeUnit.MILLISECONDS, new LinkedBlockingQueue(3444));

		for (int i = 0; i < max; ++i) {

			pool.execute(new Runnable() {

				public void run()

				{

					try {

						active.countDown();

						active.await();

					} catch (InterruptedException e) {

						e.printStackTrace(); // ignore

					}

				}

			});

			// wait time to execute task by pool

			Thread.sleep(100);

		}
		
		Assert.assertEquals("Expected all threads to block. ", 3, active.getCount());

		Assert.assertEquals("Expected active. ", max-2, pool.getActiveCount());

		Assert.assertEquals("Expected max pool size. ", max-2, pool.getPoolSize());

		// release the latch

		active.countDown();

		active.await();

		// wait around for one second for state to settle

		Thread.sleep(1000);

		Assert.assertEquals("Expected completion of all tasks. ", max, pool.getCompletedTaskCount());

		Assert.assertEquals("Expected completion of all threads. ", 0, pool.getActiveCount());

		Assert.assertEquals("Expected minimum pool size. ", min, pool.getPoolSize());
	}
	
	@Test
	public void test2() throws InterruptedException{
		LinkedBlockingQueue<Runnable> linked = new LinkedBlockingQueue<Runnable>(100);
		TaskQueue task = new TaskQueue(6000);
		
		ThreadPoolExecutor tpe = new ThreadPoolExecutor(10, 50, 0, TimeUnit.SECONDS, linked,new ThreadPoolExecutor.CallerRunsPolicy());

        //dump 5000 tasks on the queue
        for (int i = 0; i < 54; i++) {
            tpe.submit(new Runnable() {
                @Override
                public void run() {
                    //just to eat some time and give a little feedback
                    for (int j = 0; j < 5; j++) {
                       System.out.println("First-batch Task, looping:" + j + "[" + Thread.currentThread().getId() + "]");
                    }
                }
            }, null);
            System.out.println("ddddddddddd" + i);
        }
        System.out.println("MAIN THREAD:!!Done queueing!!");

        //check tpe statistics forever
        while (true) {
        	 System.out.println("Active count: " + tpe.getActiveCount() + " Pool size: " + tpe.getPoolSize() + " Largest Pool: " + tpe.getLargestPoolSize());
            Thread.sleep(1000);
        }
	}

}
