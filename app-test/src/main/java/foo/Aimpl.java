package foo;

public class Aimpl implements AClass {

	@Override
	public void test() {
		System.out.println("haha!");
	}
	
	public static void main(String[] args) {
		Aimpl a = new Aimpl();
		AClass aa = (AClass)a;
		aa.test();
	}

}
