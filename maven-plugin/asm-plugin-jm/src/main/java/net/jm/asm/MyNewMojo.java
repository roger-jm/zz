package net.jm.asm;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Execute;
import org.apache.maven.plugins.annotations.LifecyclePhase;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;

@Mojo(name="itouch")
@Execute(goal="itouch",phase=LifecyclePhase.PROCESS_RESOURCES)
public class MyNewMojo extends AbstractMojo {
	
	@Parameter(defaultValue="${project.build.directory}",required=true)
	private File outputDirectory;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		File f = outputDirectory;

        if ( !f.exists() )
        {
            f.mkdirs();
        }

        File readme = new File( f, "readme.txt" );

        FileWriter w = null;
        try
        {
            w = new FileWriter( readme );

            w.write( "welcome to Roger's house!" );
        }
        catch ( IOException e )
        {
            throw new MojoExecutionException( "Error creating file " + readme, e );
        }
        finally
        {
            if ( w != null )
            {
                try
                {
                    w.close();
                }
                catch ( IOException e )
                {
                    // ignore
                }
            }
        }
	}

}
