package net.jm.asm.serial;

import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.maven.artifact.DependencyResolutionRequiredException;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.project.MavenProject;
import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.SerialVersionUIDAdder;

/**
 * 
 * @author wangmj
 * 
 * @goal serialize
 * @phase process-classes
 * 
 */
public class SerializeMojo extends AbstractMojo {

	private String resourcesDirctory;

	 /**
     * The working directory where the generated Java source files are created.
     *
     * @parameter default-value=""
     * @required
     */
	private String outputDirectory;

	/**
	 * The default maven project object.
	 * 
	 * @parameter expression="${project}"
	 * @required
	 * @readonly
	 */
	private MavenProject project;

	@Override
	public void execute() throws MojoExecutionException, MojoFailureException {
		List<String> classpathFiles = null;
		Map<String, InputStream> allmap = null;
		try {
			classpathFiles = getClassPaths(project);
			for (String path : classpathFiles) {
				String realPath = path + outputDirectory;
				allmap = getClasses(realPath);
				
				for(Map.Entry<String,InputStream> entry: allmap.entrySet()){
					String name = entry.getKey();
					InputStream in = entry.getValue();
					serializeClass(name, in, path);
				}
			}
			
			/*Iterator it = allmap.entrySet().iterator();
			while(it.hasNext()){
				
			}*/
			/*for(Map.Entry<String,InputStream> entry: allmap.entrySet()){
				String name = entry.getKey();
				InputStream in = entry.getValue();
				serializeClass(name, in);
			}*/
			
			
		} catch (DependencyResolutionRequiredException e) {
			getLog().debug(e);
		} catch (FileNotFoundException e) {
			getLog().debug(e);
		} catch (IOException e) {
			getLog().debug(e);
		}
		
	}

	public void serializeClass(String name,InputStream in, String path) throws IOException {
		
		ClassReader classReader = new ClassReader(in);
		ClassWriter cw = new ClassWriter(ClassWriter.COMPUTE_MAXS);
		ClassVisitor uuid = new SerialVersionUIDAdder(cw);
		// ClassVisitor cv = new c

		// Wrap the ClassWriter with our custom ClassVisitor
		AddSerializeClassWriter mcw = new AddSerializeClassWriter(Opcodes.ASM4, uuid);
		classReader.accept(mcw, 0);

		// Write the output to a class file

		File outputDir = new File(path + outputDirectory);
		outputDir.mkdirs();
		DataOutputStream dout = new DataOutputStream(new FileOutputStream(new File(outputDir,
				name)));
		dout.write(cw.toByteArray());
	}

	public List<String> getClassPaths(MavenProject project) throws DependencyResolutionRequiredException {
		
		return project.getCompileClasspathElements();
	}

	/*public List<InputStream> getInputStreams() throws DependencyResolutionRequiredException {
		List<String> classpathFiles = getClassPaths(project);
		for (String path : classpathFiles) {
			String realPath = path + outputDirctory;
			
		}
		return null;
	}*/

	public Map<String, InputStream> getClasses(String strPath) throws FileNotFoundException {
		List<String> filelist = new ArrayList<String>();
		List<InputStream> fin = new ArrayList<InputStream>();
		Map<String, InputStream> fileMap = new HashMap<String, InputStream>();
		
		File dir = new File(strPath);
		File[] files = dir.listFiles();

		if (files == null)
			return null;
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				getClasses(files[i].getAbsolutePath());
			} else {
				String strFileName = files[i].getAbsolutePath().toLowerCase();
				//System.out.println("---" + strFileName);
				getLog().info("---" + strFileName);
				filelist.add(files[i].getAbsolutePath());
				InputStream temp =new FileInputStream(new File(files[i].getAbsolutePath()));
				fin.add(temp);
				String name = files[i].getName();
				fileMap.put(name, temp);
			}
		}
		return fileMap;
	}

}
