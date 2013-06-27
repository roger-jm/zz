package connector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class HttpTest {
	public static void main(String[] args) throws IOException {

		Socket socket = null;
		try {
//			String ipAddress = "168.1.191.38";
			String ipAddress = "10.10.4.48";
			String portS = "8080";
			int port = Integer.valueOf(portS).intValue();
//			String url = "GET http://" + ipAddress + ":" + port + "/TestPro/jsp1.jsp HTTP/1.1";
			String url = "GET http://" + ipAddress + ":" + port + "/test/ HTTP/1.1";

			socket = new Socket(ipAddress, port);
			socket.setKeepAlive(true);
			System.out.println(socket.getKeepAlive());
			System.out.println("connect: " + socket.isConnected());
			System.out.println("done!");
			boolean autoflush = true;

			for (int j = 0; j < 100; j++) {
				PrintWriter out = new PrintWriter(socket.getOutputStream(), autoflush);
				
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				
				System.out.println(socket);
				
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
					/*
					 * System.out.println("before while"); while (i != -1) { i =
					 * in.read(); sb.append((char) i);
					 * System.out.println("while: " + i); }
					 * System.out.println("after while");
					 */
					String s = "";
					Thread.sleep(300);
					System.out.println("connect: " + socket.isConnected());
					System.out.println("receive: " + socket.getInputStream().available());
					
//					sb.append(in.readLine());
					String r = "";
					while((r = in.readLine()) != null){
						sb.append(r+"\n");;
					}
					loop = false;
					// }
					// Thread.currentThread().sleep(50);
				}
				// display the response to the out console
				System.out.println(sb.toString());
				System.out.println("~~~~~~~~~~~~~~~~~~~~~");

				Thread.sleep(5000);

			}

			socket.close();
		} catch (UnknownHostException e) {
			System.err.println("Don't know about host: Victest.");
			e.printStackTrace();
			if (socket != null)
				socket.close();
			System.exit(1);
		} catch (IOException e) {
			System.err.println("Couldn't get I/O for " + "the connection to: Victest.");
			e.printStackTrace();
			if (socket != null)
				socket.close();
			System.exit(1);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
