package pattern;

import java.io.File;

public class ReplaceTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File f1 = new File("E:\\javaEE\\tomEE\\apache-tomee-webprofile-1.5.0\\webapps\\servlet-async\\WEB-INF\\classes\\async");
		File f2 = new File("E:\\javaEE\\tomEE\\apache-tomee-webprofile-1.5.0\\webapps\\servlet-async\\WEB-INF\\classes");
		String path = f1.getAbsolutePath().replace(f2.getAbsolutePath(), "");
		System.out.println(path);
		
		String s1 = "/root/hello/async";
		String s2 = "/root/hello";
		String result = s1.replaceFirst(s2, "");
		System.out.println(result);
	}

}
