package realm;

import java.security.AccessControlContext;
import java.security.AccessController;
import java.security.Permission;
import java.security.Principal;
import java.security.PrivilegedAction;

import javax.security.auth.Subject;

public class AccessTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Subject subject = new Subject();
		subject.getPrincipals().add(new Principal() {
			
			@Override
			public String getName() {
				
				return "roger";
			}
		});
		
		//SecurityContext sc = new SecurityContext(subject);
		Permission perm = new Permission("") {
			
			@Override
			public boolean implies(Permission permission) {
				// TODO Auto-generated method stub
				return false;
			}
			
			@Override
			public int hashCode() {
				// TODO Auto-generated method stub
				return 0;
			}
			
			@Override
			public String getActions() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public boolean equals(Object obj) {
				// TODO Auto-generated method stub
				return false;
			}
		};
		//sc.acc.checkPermission(perm);
	}

	class SecurityContext {

	    private final Subject subject;
	    private final AccessControlContext acc;

	    public SecurityContext(final Subject subject) {
	        this.subject = subject;
	        this.acc = (AccessControlContext) Subject.doAsPrivileged(subject, new PrivilegedAction() {
	            @Override
	            public Object run() {
	                return AccessController.getContext();
	            }
	        }, null);
	    }
	}
}

 
