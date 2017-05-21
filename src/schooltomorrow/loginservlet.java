/* io non ho creato il file web.xml 
 * NON HO APPAIOATO pagina/servlet
 * ad ogni modo, sarebbe:
 * 
 *  <servlet>
    	<servlet-name>login</servlet-name>
    	<servlet-class>schooltomorrow.loginservlet</servlet-class>
  	</servlet>

  <servlet-mapping>
  		<servlet-name>login</servlet-name>
    	<url-pattern>/registration</url-pattern>
  </servlet-mapping> 
 * 
 * */

package schooltomorrow;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class loginservlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public void doPost (HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		Enumeration<String> enumNames = req.getParameterNames();
		if (!enumNames.hasMoreElements()) {
			 req.getRequestDispatcher("/registration").forward(req, res);
			 return;
		} else {
		 PrintWriter out = res.getWriter();
		 out.println("<!DOCTYPE html>");
		 out.println("<html>");
		 out.println(" <head>");
		 out.println(" <title>result of login form</title>");
		 out.println(" </head>");
		 out.println(" <body>");
		 out.println(" <h1>Registration Success</h1><br>");
		 out.println(" <article>");
		 out.println(" <header><h2>User's personal information:</h2></header>");
		 out.println(" <section>");		 
		 while (enumNames.hasMoreElements()) {
			 String name = (String) enumNames.nextElement();
			 String values[] = req.getParameterValues(name);
			 
			 if (values != null) {
				 for (int i = 0; i < values.length; i++) {
				 //out.println("<h4>" + name + ": " + common.formatHtml(values[i]) + "</h4>");
				 }
			 }
		 }
		 out.println(" <br><a href=\"http://" + req.getServerName() + ":" + req.getServerPort() + req.getContextPath() +
		 req.getServletPath() + "\">Back to the form page</a><br><br>");
		 out.println("</section>");
		 out.println("</article>");
		 out.println("</body>");
		 out.println("</html>");
		}
	} // doPosts
}