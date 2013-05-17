package foo;

public class WhoFirst {
	private Test test = new Test();
	

	public WhoFirst(){
		System.out.println("me");
	}
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		new WhoFirst();
	}

}
