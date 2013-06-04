package reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Test {
	static Method getTime = null;
	
	static{
		try {
			getTime = OriginalMethod.class.getDeclaredMethod("getTime");
			getTime.setAccessible(true);
		} catch (NoSuchMethodException e) {
			e.printStackTrace();
		} catch (SecurityException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		OriginalMethod original = new OriginalMethod();
		try {
			String time = (String) getTime.invoke(original);
			System.out.println(time);
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			e.printStackTrace();
		}
	}

}
