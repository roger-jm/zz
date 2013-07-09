package test;

import java.io.File;

public class FileT {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		File f = new File("D:\\log3");
		 final String FILE_SEPARATOR = System.getProperties().getProperty("file.separator");
		 System.out.println(FILE_SEPARATOR);
		String os = System.getProperty("os.name");
		System.out.println(os);
		boolean b = f.canExecute();
		System.out.println(b);
	}

}
