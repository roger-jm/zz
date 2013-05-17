package classloader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MyClassloader extends ClassLoader {

	public Class loadClass(String name) throws ClassNotFoundException {

		if (name.contains("ClassB")) {
			Class klass = null;
			try {
				klass = findLoadedClass(name); // 检查该类是否已经被装载。
				if (klass != null) {
					return klass;
				}

				byte[] bs = getClassBytes(name);// 从一个特定的信息源寻找并读取该类的字节。
				if (bs != null && bs.length > 0) {
					klass = defineClass(name, bs, 0, bs.length);
				}
				if (klass == null) { // 如果读取字节失败，则试图从JDK的系统API中寻找该类。
					klass = findSystemClass(name);
				}
				if ( klass != null) {
					resolveClass(klass);
				}
			} catch (IOException e) {
				throw new ClassNotFoundException(e.toString());
			}
			System.out.println("klass == " + klass);
			return klass;
		} else {
			return super.loadClass(name);
		}

	}

	private byte[] getClassBytes(String className) throws IOException {
		String path = System.getProperty("user.dir") + File.separator ;
		path += className.replace('.', File.separatorChar) + ".class";
		System.out.println(path);
		FileInputStream fis = null;
		try {
			fis = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			System.out.println(e);
			return null; // 如果查找失败，则放弃查找。捕捉这个异常主要是为了过滤JDK的系统API。
		}
		byte[] bs = new byte[fis.available()];
		fis.read(bs);
		return bs;
	}
}
