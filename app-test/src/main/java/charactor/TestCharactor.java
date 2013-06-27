package charactor;

import java.io.UnsupportedEncodingException;

import org.apache.tomcat.util.buf.B2CConverter;

public class TestCharactor {
	public static boolean isuse(String charactor){
		try {
			B2CConverter.getCharset(charactor);
		} catch (UnsupportedEncodingException e) {
			return false;
		}
		return true;
	}
	
	public static void main(String[] args) {
		boolean a = isuse("gbk2");
		System.out.println(a);
	}
}
