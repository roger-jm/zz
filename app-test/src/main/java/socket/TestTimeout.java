package socket;

import java.net.Socket;
import java.net.SocketException;

public class TestTimeout {
	public static void main(String[] args) {
		Socket socket = new Socket();
		try {
			socket.setSoTimeout(-1);
		} catch (SocketException e) {
			e.printStackTrace();
		}
	}
}
