package jndi;

import static org.junit.Assert.*;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.naming.spi.NamingManager;

import org.junit.Before;
import org.junit.Test;

public class TestTomcatJndi {
	InitialContext ctx = null;

	@Before
	public void setUp() throws Exception {
		Hashtable<String, String> pros = new Hashtable<String, String>();
		pros.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
		pros.put(Context.URL_PKG_PREFIXES, "org.apache.naming");
		
		ctx = new InitialContext(pros);
		Context init = NamingManager.getInitialContext(pros);
		String scheme = getURLScheme("java:comp/env");
		System.out.println(scheme);
		Context urlcontext = NamingManager.getURLContext(scheme, pros);
		
		System.out.println(urlcontext.toString());
		System.out.println(init);
	}

	private static String getURLScheme(String str) {
		int colon_posn = str.indexOf(':');
		int slash_posn = str.indexOf('/');

		if (colon_posn > 0 && (slash_posn == -1 || colon_posn < slash_posn))
		    return str.substring(0, colon_posn);
		return null;
	    }
	
	@Test
	public void test1() throws NamingException {
		
		Context comp = (Context)ctx.createSubcontext("comp");
		Context env = comp.createSubcontext("env");
		env.bind("chess", "queen");
		ctx.bind ("blah", "123");
		    assertEquals ("123", ctx.lookup("blah"));
		 assertEquals("queen", ctx.lookup("java:comp/env/chess"));
		    //ctx.lookup("java:");
		 //System.out.println(ctx.lookup("comp/env/chess"));
	}

}
