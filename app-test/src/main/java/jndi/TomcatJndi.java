package jndi;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import foo.Test;

public class TomcatJndi {
	public static void main(String[] args) throws NamingException {
		Hashtable<String, String> pros = new Hashtable<String, String>();
		pros.put(Context.INITIAL_CONTEXT_FACTORY, "org.apache.naming.java.javaURLContextFactory");
		pros.put(Context.URL_PKG_PREFIXES, "org.apache.naming");
		
		InitialContext init = new InitialContext(pros);
		
		init.bind("rook", new Test());
		
		Test t = (Test) init.lookup("rook");
		System.out.println(t.getName());
	}
}
