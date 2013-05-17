package pattern;

import java.util.regex.Pattern;

public class PatternTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Pattern agent = Pattern.compile(".*Firefox.*");
		
		boolean is = agent.matcher("Mozilla/5.0 (Windows NT 6.1; rv:20.0) Gecko/20100101 Firefox/20.0").matches();
		System.out.println(is);
	}

}
