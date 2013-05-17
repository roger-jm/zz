package security;

import static org.junit.Assert.*;

import java.nio.charset.Charset;

import org.junit.Test;

public class TestNtName {

	@Test
	public void test() {
		String user = System.getProperty("user.name");
		System.out.println(user);
		
		Charset set = Charset.defaultCharset();
		System.out.println(set);
	}

}
