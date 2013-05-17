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
				klass = findLoadedClass(name); // �������Ƿ��Ѿ���װ�ء�
				if (klass != null) {
					return klass;
				}

				byte[] bs = getClassBytes(name);// ��һ���ض�����ϢԴѰ�Ҳ���ȡ������ֽڡ�
				if (bs != null && bs.length > 0) {
					klass = defineClass(name, bs, 0, bs.length);
				}
				if (klass == null) { // �����ȡ�ֽ�ʧ�ܣ�����ͼ��JDK��ϵͳAPI��Ѱ�Ҹ��ࡣ
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
			return null; // �������ʧ�ܣ���������ҡ���׽����쳣��Ҫ��Ϊ�˹���JDK��ϵͳAPI��
		}
		byte[] bs = new byte[fis.available()];
		fis.read(bs);
		return bs;
	}
}
