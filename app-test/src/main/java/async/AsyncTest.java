package async;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.text.MessageFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

public class AsyncTest {
	public static final AtomicInteger counter = new AtomicInteger(0);
	public static final int maxThreadCount = 100;

	public static void main(String[] args) throws InterruptedException {

		new AsyncTest();

	}

	public AsyncTest() throws InterruptedException {

		// call simple servlet

//		ExecutorService exec1 = Executors.newCachedThreadPool();
//
//		for (int i = 0; i < maxThreadCount; i++) {
//
//			exec1.submit(new UrlReaderTask("http://localhost:8080/async/simple"));
//
//		}
//
//		exec1.shutdown();
//
//		Thread.currentThread().sleep(5000);

		System.out.println("....NEXT....");

		// call complex servlet

		counter.set(0);

		ExecutorService exec2 = Executors.newCachedThreadPool();

		for (int i = 0; i < maxThreadCount; i++) {

			exec2.submit(new UrlReaderTask("http://localhost:8080/async/complex"));

		}

		exec2.awaitTermination(1, TimeUnit.MINUTES);

	}

	public class UrlReaderTask implements Runnable {

		private String endpoint;

		public UrlReaderTask(String s) {
			endpoint = s;
		}

		public void run() {

			try {
				actuallyrun();
			} catch (Exception e) {
				System.err.println(e.toString());
			}

		}

		public void actuallyrun() throws Exception {

			int count = counter.addAndGet(1);

			BufferedReader in = new BufferedReader(new InputStreamReader(
					new URL(endpoint).openStream()));

			String inputLine;

			while ((inputLine = in.readLine()) != null) {
				System.out.println(MessageFormat.format(
						"thread[{0}] : {1} : {2}", count, inputLine, endpoint));
			}

			in.close();

		}

	}

}// end class ComplexLoadTester
