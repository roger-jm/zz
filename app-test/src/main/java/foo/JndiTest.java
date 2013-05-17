package foo;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.LinkRef;
import javax.naming.NamingException;
import javax.naming.Reference;
import javax.naming.StringRefAddr;

public class JndiTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String name = "";
		if (args.length > 0)
			name = args[0];

		try {
			// Create a Properties object and set properties appropriately
			Properties props = new Properties();
			//props.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.jndi.fscontext.RefFSContextFactory");
			props.put(Context.PROVIDER_URL, "file:d:\\jnditemp");
			//props.put("hi", "wmj");

			// Create the initial context from the properties we just created
			Context initialContext = new InitialContext(props);
			initialContext.bind("hi", "hah");

			// Look up the object
			Object obj = initialContext.lookup("hi");
			if (name.equals(""))
				System.out.println("Looked up the initial context");
			else
				System.out.println(name + " is bound to: " + obj);
		} catch (NamingException nnfe) {
			System.out.println("Encountered a naming exception");
			nnfe.printStackTrace();
		}

		/*
		 * LinkRef link = new LinkRef("java:comp/env/rubbish"); try {
		 * initCtx.bind("java:comp/env/poubelle", link); } catch
		 * (NamingException e2) { // TODO Auto-generated catch block
		 * e2.printStackTrace(); } //assertEquals("abc", (String)
		 * initCtx.lookup("java:comp/env/poubelle")); String poube=null; try {
		 * poube = (String) initCtx.lookup("java:comp/env/poubelle"); } catch
		 * (NamingException e2) { // TODO Auto-generated catch block
		 * e2.printStackTrace(); } System.out.println(poube);
		 * 
		 * // check binding References StringRefAddr addr = new
		 * StringRefAddr("blah", "myReferenceable"); Reference ref = new
		 * Reference(java.lang.String.class.getName(), addr,
		 * JndiTest.class.getName(), (String) null);
		 * 
		 * try { initCtx.bind("java:comp/env/quatsch", ref); } catch
		 * (NamingException e) { e.printStackTrace(); }
		 * //assertEquals(JndiTest.myString, (String)
		 * initCtx.lookup("java:comp/env/quatsch"));
		 * 
		 * // test binding something at java: try { Context sub3 =
		 * initCtx.createSubcontext("java:zero"); } catch (NamingException e1) {
		 * e1.printStackTrace(); } try { initCtx.bind("java:zero/one", "ONE"); }
		 * catch (NamingException e) { e.printStackTrace(); }
		 * //assertEquals("ONE", initCtx.lookup("java:zero/one"));
		 */}

}
