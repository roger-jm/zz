
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class TestBacklog2 {
	private static Logger logger = Logger.getLogger(TestBacklog2.class.toString());  
	  
    public static void main(String[] args) throws Exception {  
        BufferedReader in = null;  
        PrintWriter out = null;  
        int backlog = 2;  
  
        ServerSocket serversocket = new ServerSocket(10000, backlog);  
        while (true) {  
            logger.info("���������......");  
            int i;  
            Socket socket = serversocket.accept();  
            logger.info("�пͻ������Ϸ����, �ͻ�����Ϣ���£�" + socket.getInetAddress() + " : " + socket.getPort() + ".");  
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));  
            out = new PrintWriter(socket.getOutputStream(), true);  
            do {  
                char[] c = new char[1024];  
                i = in.read(c);  
                Thread.sleep(20000);
                logger.info("������յ���Ϣ: " + new String(c, 0, i));  
            } while (i == -1);  
            out.close();  
            in.close();  
            socket.close();  
            logger.info("�رշ����......");  
        }  
    }  
}
