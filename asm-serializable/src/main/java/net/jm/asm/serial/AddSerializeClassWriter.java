package net.jm.asm.serial;

import org.objectweb.asm.ClassVisitor;

public class AddSerializeClassWriter extends ClassVisitor{

	public AddSerializeClassWriter(int api, ClassVisitor cv) {
		super(api, cv);
	}
	
	@Override
	public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
		super.visit(version, access, name, signature, superName, new String[]{"java/io/Serializable"});
	}

}
