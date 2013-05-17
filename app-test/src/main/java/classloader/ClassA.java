package classloader;

public class ClassA {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws InterruptedException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, InterruptedException {
		// TODO Auto-generated method stub

		final ClassLoader cl=new MyClassloader();
		Thread.currentThread().setContextClassLoader(cl);
		//Class c=cl.loadClass("classloader.ClassB");
		//Class c=Class.forName("classloader.ClassB");
		//final Class c=null;
		Thread t=new Thread(new Runnable(){

			@Override
			public void run() {
				System.out.println(Thread.currentThread().getContextClassLoader());
				Thread.currentThread().setContextClassLoader(cl);
				//ClassB b=new ClassB();
				//"classloader.ClassB"
				 try {
					 Class c=cl.loadClass("classloader.ClassB");
					 System.out.println(c.getClassLoader());
				} catch (ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
		});
		t.setContextClassLoader(cl);
		t.start();
		Thread.sleep(10);
		//cl.loadClass("classloader.ClassB");
		//ClassB b=new ClassB();*/
		//Class c=Class.forName("classloader.ClassB");
		
		System.out.println(ClassA.class.getClassLoader());
	}

}
