package ssl;

import java.security.NoSuchAlgorithmException;

import javax.net.ssl.SSLContext;

public class SslContextTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("TLS : " + isSSL("TLS"));
		System.out.println("SSL : " + isSSL("SSL"));
		System.out.println("SSLv2 : " + isSSL("SSLv2"));
		System.out.println("SSLv3 : " + isSSL("SSLv3"));
		System.out.println("TLSv1 : " + isSSL("TLSv1"));
		System.out.println("TLSv1.1 : " + isSSL("TLSv1.1"));
		//System.out.println("TLS : " + isSSL("TLS"));
		//System.out.println("TLS : " + isSSL("TLS"));
	}

	
	public static boolean isSSL(String ssl){
		try {
			SSLContext.getInstance(ssl);
		} catch (NoSuchAlgorithmException e) {
			return false;
		}
		return true;
	}
}
