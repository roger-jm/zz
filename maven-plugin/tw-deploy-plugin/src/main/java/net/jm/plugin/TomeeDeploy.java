package net.jm.plugin;

import java.io.File;
import java.io.FilenameFilter;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.tools.ant.Project;
import org.apache.tools.ant.taskdefs.Copy;
import org.apache.tools.ant.taskdefs.Delete;

@Mojo(name = "deploy", defaultPhase = LifecyclePhase.VERIFY)
public class TomeeDeploy extends AbstractMojo {

	@Parameter(defaultValue = "webapps")
	private String webapps;
	
	@Parameter(defaultValue="false")
	private boolean isDelApp;
	
	@Parameter(defaultValue="2000")
	private long wait;

	@Parameter(defaultValue = "autodeploy")
	private String autodeploy;

	@Parameter(defaultValue = ".autodeploy")
	private String dotAutodeploy;

	@Parameter(defaultValue = "${project.build.directory}/${project.artifactId}.war", required = true)
	private File targetApp;

	@Parameter(defaultValue = "temp")
	private String temp;

	@Parameter(required = true)
	private File tongweb_base;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		File tongweb = tongweb_base;
		if (!tongweb.exists()) {
			tongweb.mkdirs();
		}

		File tomcat = new File(tongweb, "/bin");
		String[] list = tomcat.list();
		boolean istomcat = false;
		for (String a : list) {
			if (a.contains("catalina")) {
				istomcat = true;
			}
		}
		if (!istomcat) {
			getLog().error("it is not tomcat!");
			return;
		}
		File app = targetApp;
		String appName = app.getName();
		String appName_noWar = appName.split("\\.")[0];

		// delete app from autodeploy
		File auto = new File(tongweb_base, "/" + autodeploy);
		File autoWar = new File(auto, "/" + appName);
		if (autoWar.exists()) {
			autoWar.delete();
		}

		// delete app from webapps
		if(isDelApp){
			File webApp = new File(tongweb, "/" + webapps);
			File oldapp = new File(webApp, "/" + appName_noWar);
			if (oldapp.exists()) {
				try {
					Thread.sleep(wait);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				Project app_proj = new Project();
				Delete app_del = new Delete();
				app_del.setProject(app_proj);
				app_del.setDir(oldapp);
				app_del.execute();
			}
		}

		// delete app from autodeploy/.autodeploy
		File dot = new File(auto, "/.autodeploy");
		if (dot.exists()) {
		}

		// delete temp

		Project pro = new Project();
		Copy copy = new Copy();
		copy.setProject(pro);
		copy.setFile(app);
		copy.setTodir(new File(tongweb, "/" + autodeploy));
		copy.execute();
	}

}
