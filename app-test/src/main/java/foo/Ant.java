package foo;

import java.io.File;

import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Copy;

public class Ant {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Project pro = new Project();
		Copy copy = new Copy();
		copy.setProject(pro);
		copy.setFile(new File("/Users/wangmingjie/myjob/tomcat/tomcat-maven-plugin/trunk/tomcat7-maven-plugin/src/main/java/org/apache/tomcat/maven/plugin/tomcat7/deploy/DeployMojo.java"));
		copy.setTodir(new File("/Users/wangmingjie/myjob/tomcat/apache-tomcat-7.0.35/autodeploy"));
		copy.execute();
	}

}
