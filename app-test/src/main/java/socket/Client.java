package socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class Client {

	/**
	 * @param args
	 * @throws IOException 
	 * @throws UnknownHostException 
	 */
	public static void main(String[] args) throws UnknownHostException, IOException {
		//Socket socket = new Socket("168.1.100.113", 10000);
		Socket socket = new Socket("127.0.0.1", 10000);
		
		BufferedReader in = null;
		PrintWriter out = null;
		in = new BufferedReader(new InputStreamReader(socket.getInputStream()));  
        out = new PrintWriter(socket.getOutputStream(), true);  
        out.println("hello");
        out.close();
        in.close();
        socket.close();
	}

}
