package realm;

import javax.security.auth.login.LoginContext;

import javax.security.auth.login.LoginException;
import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.realm.JAASCallbackHandler;

public class LoginContextTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		/*new JAASCallbackHandler(this, username, clientDigest, nonce,
                nc, cnonce, qop, realmName, md5a2,
                HttpServletRequest.DIGEST_AUTH);*/
		
		try {
			LoginContext loginContext = new LoginContext("PropertiesLoginModule", new JAASCallbackHandler(null, "felix", "123"));
		//	loginContext.
		} catch (LoginException e) {
			e.printStackTrace();
		}
		
	}

}
