package net.jm.plugin;

import java.io.File;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.project.MavenProject;

public abstract class AbstractTomeeMojo extends AbstractMojo {
	@Parameter(defaultValue = "webapps")
	private String webapps;
	
	@Parameter(required = true)
	private File tongweb_base;

	@Component
	protected MavenProject project;
}
