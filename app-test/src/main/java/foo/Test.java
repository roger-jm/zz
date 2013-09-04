package foo;

public class Test {
	
	public Test(){
		System.out.println("test first");
	}
	
	public String getName(){
		return "chess";
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		String s = "localhost.war";
		String[] ss = s.split("\\.");
		System.out.println(ss[0]);
		System.out.println(ss[1]);
		String app = "app_server";
		app = app.substring(4);
		System.out.println(app);
	}

}
