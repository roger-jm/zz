package client;

import java.io.IOException;

import org.apache.http.client.HttpClient;

public class Test {
	
	static
	   {
	      System.setProperty("javax.net.ssl.trustStore", "E:/javaEE/tomcat/apache-tomcat-7.0.30/conf/client1.jks");
	      System.setProperty("javax.net.ssl.trustStorePassword", "password");
	     System.setProperty("javax.net.ssl.keyStore", "E:/javaEE/tomcat/apache-tomcat-7.0.30/conf/client1.jks");
	      System.setProperty("javax.net.ssl.keyStorePassword", "password");
	   }
	
	public static void main(String[] args) {
		
	}
}
