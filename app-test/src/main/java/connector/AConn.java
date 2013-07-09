package connector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class AConn implements Runnable {
	
	public static void main(String[] args) {
		for(int i = 0;i<7;i++){
			Thread t = new Thread(new AConn());
			t.start();
			try {
				System.out.println(i);
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void run() {
		Socket socket = null;
		String ipAddress = "168.1.191.53";
		//String ipAddress = "10.10.4.48";
		String portS = "8080";
		int port = Integer.valueOf(portS).intValue();
		int maxConn = 10;
		String url = "GET http://" + ipAddress + ":" + port + "/test1/indextest HTTP/1.1";

		PrintWriter out = null;
		BufferedReader in = null;

		try {
			socket = new Socket(ipAddress, port);
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));

			out.println(url);
			out.println("Host: " + ipAddress);
			out.println("Connection: Keep-Alive");
			out.println();

			StringBuffer sb = new StringBuffer(8096);
			String r = "";
			while ((r = in.readLine()) != null) {
				sb.append(r + "\n");
				;
			}

			System.out.println(sb.toString());
			System.out.println("~~~~~~~~~~~~~~~~~~~~~");
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			//out.flush();
			//out.close();
			if(socket!=null){
				try {
					socket.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

}
