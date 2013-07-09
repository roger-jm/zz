package realm;

import org.apache.catalina.realm.RealmBase;

public class GenerateMD5 {
	public static void main(String[] args) {
		String ralph = RealmBase.Digest("felix:myfoorealm:1234", "MD5", "GBK");
		String ralph2 = RealmBase.Digest("hello:my realm:hello", "SHA", "GBK");
		String ralph3 = RealmBase.Digest("1234", "MD5", "GBK");
		String ralph5 = RealmBase.Digest("1234", "MD5", "ISO-8859-1");
		String ralph4 = RealmBase.Digest("hello:sqlrealm:1234", "MD5", "GBK");
		System.out.println("md5: " + ralph);
		System.out.println("sha: " + ralph2);
		System.out.println("no.3: " + ralph3);
		System.out.println("md5: " + ralph4);
		System.out.println("no.5: " + ralph5);
		String ralph6 = RealmBase.Digest("twns:twns-realm:twns", "SHA", "GBK");
		System.out.println("twns: " + ralph6);
	}
}
