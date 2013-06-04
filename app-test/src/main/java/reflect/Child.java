package reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Child extends OriginalMethod {
	static Method getTime = null;

	static {
		try {
			getTime = OriginalMethod.class.getDeclaredMethod("getTime");
			getTime.setAccessible(true);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

	String getTJTime(){
		String Tianji = "";
				try {
					Tianji = (String) getTime.invoke(this);
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				}
		return "tian ji time is : " + Tianji;
	}
	
	public static void main(String[] args) {
		String ss = new Child().getTJTime();
		System.out.println(ss);
	}
}
