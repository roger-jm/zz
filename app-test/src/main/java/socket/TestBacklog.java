package socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Logger;

public class TestBacklog {
	private static Logger logger = Logger.getLogger(TestBacklog.class.toString());  
	  
    public static void main(String[] args) throws Exception {  
        BufferedReader in = null;  
        PrintWriter out = null;  
        int backlog = 2;  
  
        ServerSocket serversocket = new ServerSocket(10000, backlog);  
        while (true) {  
            logger.info("启动服务端......");  
            int i;  
            Socket socket = serversocket.accept();  
            logger.info("有客户端连上服务端, 客户端信息如下：" + socket.getInetAddress() + " : " + socket.getPort() + ".");  
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));  
            out = new PrintWriter(socket.getOutputStream(), true);  
            do {  
                char[] c = new char[1024];  
                i = in.read(c);  
                Thread.sleep(20000);
                logger.info("服务端收到信息: " + new String(c, 0, i));  
            } while (i == -1);  
            out.close();  
            in.close();  
            socket.close();  
            logger.info("关闭服务端......");  
        }  
    }  
}
