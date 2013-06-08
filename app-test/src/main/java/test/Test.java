package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Test extends HttpServlet {
	private static final long serialVersionUID = -6716473836743722688L;

	protected void doGet(HttpServletRequest p0, HttpServletResponse p1) throws ServletException, IOException {
		doPost(p0, p1);
	}

	protected void doPost(HttpServletRequest p0, HttpServletResponse p1) throws ServletException, IOException {
		System.out.println("===================================");
		System.out.println("RequestURL       :" + p0.getRequestURL());
		System.out.println("QueryString      :" + p0.getQueryString());
		System.out.println("Method           :" + p0.getMethod());
		System.out.println("CharacterEncoding:" + p0.getCharacterEncoding());
		Enumeration aaa = p0.getParameterNames();
		while (aaa.hasMoreElements()) {
			String kkk = (String) aaa.nextElement();
			System.out.println("   - " + kkk + "=" + p0.getParameter(kkk));
		}

		String t = p0.getParameter("t");
		p1.setContentType("text/html");
		PrintWriter out;
		if (t == null) {
			out = p1.getWriter();
			out.print("Free");
			out.flush();
		} else if ("utf-8".equals(t)) {
			p1.setCharacterEncoding("UTF-8");
			out = p1.getWriter();
			out.print("Free");
			out.flush();
		} else if ("utf-16".equals(t)) {
			p1.setCharacterEncoding("UTF-16");
			out = p1.getWriter();
			
			//char[] buffer=new char[1024];
			/*String s ="ÖÐÎÄ";
			byte[] res=s.getBytes("UTF-16LE");*/
			out.println("Free");
			out.flush();
		} else if ("gbk".equals(t)) {
			p1.setCharacterEncoding("GBK");
			out = p1.getWriter();
			out.print("Free");
			out.flush();
		} else if ("gb2312".equals(t)) {
			p1.setCharacterEncoding("GB2312");
			out = p1.getWriter();
			out.print("Free");
			out.flush();
		}
	}
}
