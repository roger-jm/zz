package connector;

import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import org.junit.Test;

public class Timeout {

	@Test
	public void test() throws IOException {
		Socket socket = null;
		try {
			String ipAddress = "192.168.110.40";
			String portS = "8080";
			int port = Integer.valueOf(portS).intValue();
			String url = "GET http://" + ipAddress + ":" + port
					+ "/servlet-upload/index.jsp HTTP/1.1";

			socket = new Socket(ipAddress, port);
			System.out.println("done!");
			boolean autoflush = true;

			for (int j = 0; j < 100; j++) {
				PrintWriter out = new PrintWriter(socket.getOutputStream(),
						autoflush);
				BufferedReader in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));
				Thread.sleep(7000);
				// send an HTTP request to the web server
				out.println(url);
				out.println("Host: " + ipAddress);
				//out.println("Connection: Keep-Alive");
				out.println();
				// read the response
				boolean loop = true;
				StringBuffer sb = new StringBuffer(8096);
				while (loop) {
					// if (in.ready()) {
					int i = 0;
				/*	System.out.println("before while");
					while (i != -1) {
						i = in.read();
						sb.append((char) i);
						System.out.println("while: " + i);
					}
					System.out.println("after while");*/
					String s = "";
					sb.append(in.readLine());
					loop = false;
					// }
					// Thread.currentThread().sleep(50);
				}
				// display the response to the out console
				System.out.println(sb.toString());
				System.out.println("~~~~~~~~~~~~~~~~~~~~~");

				Thread.sleep(400);

			}

			socket.close();
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: Victest.");
			e.printStackTrace();
			if (socket != null)
				socket.close();
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for "
					+ "the connection to: Victest.");
			e.printStackTrace();
			if (socket != null)
				socket.close();
			System.exit(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Test
	public void testTimeout2(){
		Socket socket = null;
		try {
			String ipAddress = "192.168.110.40";
			String portS = "8080";
			int port = Integer.valueOf(portS).intValue();
			String url = "POST http://" + ipAddress + ":" + port
					+ "/servlet-upload/upload HTTP/1.1";

			socket = new Socket(ipAddress, port);
			socket.setKeepAlive(true);
			System.out.println("done!");
			System.out.println("is keep alive" + socket.getKeepAlive());
			Thread.sleep(7000);
			System.out.println("is closed? " + socket.isClosed());
			
			PrintWriter out = new PrintWriter(socket.getOutputStream(),
					true);
			out.println(url);
			out.println("Host: " + ipAddress);
			out.println("Connection: Keep-Alive");
			out.println("Content-Type	multipart/form-data");
			out.println();
			
			StringBuffer sb = new StringBuffer(8096);
			int i = 0;
			BufferedReader in = new BufferedReader(new InputStreamReader(
					socket.getInputStream()));
				String s = "";
				sb.append(in.readLine());
				System.out.println(sb.toString());
				System.out.println("~~~~~~~~~~~~~~~~~~~~~");
		}catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@Test
	public void testg() throws IOException{
		Socket socket = null;
		try {
			String ipAddress = "192.168.110.40";
			String portS = "8080";
			int port = Integer.valueOf(portS).intValue();
			String url = "GET http://" + ipAddress + ":" + port
					+ "/servlet-upload/index.jsp HTTP/1.1";

			socket = new Socket(ipAddress, port);
			System.out.println("done!");
			boolean autoflush = true;
			
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			for (int j = 0; j < 100; j++) {
				PrintWriter out = new PrintWriter(socket.getOutputStream(),
						autoflush);
				BufferedReader in = new BufferedReader(new InputStreamReader(
						socket.getInputStream()));

				// send an HTTP request to the web server
				out.println(url);
				out.println("Host: " + ipAddress);
				out.println("Connection: Keep-Alive");
				out.println();
				// read the response
				boolean loop = true;
				StringBuffer sb = new StringBuffer(8096);
				while (loop) {
					// if (in.ready()) {
					int i = 0;
				/*	System.out.println("before while");
					while (i != -1) {
						i = in.read();
						sb.append((char) i);
						System.out.println("while: " + i);
					}
					System.out.println("after while");*/
					String s = "";
					sb.append(in.readLine());
					loop = false;
					// }
					// Thread.currentThread().sleep(50);
				}
				// display the response to the out console
				System.out.println(sb.toString());
				System.out.println("~~~~~~~~~~~~~~~~~~~~~");

			}

			socket.close();
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: Victest.");
			e.printStackTrace();
			if (socket != null)
				socket.close();
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for "
					+ "the connection to: Victest.");
			e.printStackTrace();
			if (socket != null)
				socket.close();
			System.exit(1);
		} /*catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
