package schooltomorrow;

import schooltomorrow.Common;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.EnumSet;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.Session;

// http://localhost:8080/appointment/main
public class MainServlet extends HttpServlet {
	
	private static final long serialVersionUID = 7L;
	
	private static String sLoginPage = "/jsp/login.jsp"; // "/jsp/login.jsp"; "/jsp/homepage.jsp";
	private static String sHomePage = "/jsp/main.jsp"; // "/jsp/main.jsp"; "/jsp/homepage.jsp"; 
	private static String sURL = "URL"; 
	private static String sLogin = "uname"; 
	private static String sPassword = "psw"; 
	private static String sName = "name"; 
	private static String sFamilyName = "familyName"; 
	private static String sPhone = "phone"; 
	private static String sEmail = "email"; 
	private static String sChildName = "childName"; 
	private static String sChildFamilyName = "childFamilyName"; 
	private static String sCityRegistration = "cityRegistration"; 
	private static String sCurrentUser = "currentUser"; 
	private static String sDate = "date"; 
	private static String sDateTime = "dateTime"; 
	private static String sCurrentPage = "currentPage";
	private static String sPageErrors = "pageErrors";
	private static String sServletAction = "servletAction";
	
	private static String sLoginFailed = "loginFailed";
	
	/*private static String tbUsers = "users";
	private static String tbUsers = "users";
	private static String tbUsers = "users";
	private static String tbUsers = "users";*/
	/** Roles of the users. */
  public enum Role {
    Parent, Secretary, Admin;
  }/** All possible JSP pages in this project. */
  public enum Page {
    HOME, LOGIN, LOGOUT, UPDATE_USER_INFO, UPDATE_USER_ACCOUNT, CREATE_TIMESLOTS, RESERVE_TIMESLOT, 
    GET_TABLE;
  }
  /** Errors in different pages. */
  public enum Errors {
    LOGIN_FAILED
  }
	// =============================================================
	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		if (request.getSession().getAttribute(sURL) == null) {
			/*String URL =  "http://" + request.getServerName() + ":" + request.getServerPort() 
				+ request.getContextPath() + request.getServletPath();*/
			request.getSession().setAttribute(sURL, "http://localhost:8080/schooltomorrow/main");
		}
		if (request.getSession().getAttribute(sCurrentUser) == null) {
      // in case if there were errors earlier
      Set<Errors> pageErrors = EnumSet.noneOf(Errors.class);
      request.getSession().setAttribute(sPageErrors, pageErrors);
      request.getRequestDispatcher(sLoginPage).forward(request, response);
      return;
    }
    request.getSession().setAttribute(sCurrentPage, Page.HOME.name());
    request.getRequestDispatcher(sHomePage).forward(request, response);
	}
	// =============================================================
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		if (request.getSession().getAttribute(sURL) == null) {
			/*String URL =  "http://" + request.getServerName() + ":" + request.getServerPort() 
				+ request.getContextPath() + request.getServletPath();*/
			request.getSession().setAttribute(sURL, "http://localhost:8080/schooltomorrow/main");
		}
    Enumeration<String> parameterNames = request.getParameterNames(); 
    while (parameterNames.hasMoreElements()) { 
    	// parameters could be read only one time
      String paramName = parameterNames.nextElement();
      System.out.print(paramName + ": "); 
      String[] paramValues = request.getParameterValues(paramName);
      String servletAction = "";
      for (int i = 0; i < paramValues.length; i++) {
      	String paramValue = paramValues[i];
        System.out.print(paramValue + "; "); 
      }
    }
    // Info for Errors on all pages.
    Set<Errors> pageErrors = EnumSet.noneOf(Errors.class);
    // This attribute is updated in order to delete info about previous errors
    request.getSession().setAttribute(sPageErrors, pageErrors);
    if (request.getSession().getAttribute(sCurrentUser) == null
        && !((String)request.getParameter(sServletAction)).equals(Page.LOGIN.name())) {
      request.getRequestDispatcher(sLoginPage).forward(request, response);
      return;
    }
    if (request.getParameter(sServletAction) == null) { 
      // have got here by mistake
      request.getSession().setAttribute(sCurrentPage, Page.HOME.name());
      request.getRequestDispatcher(sLoginPage).forward(request, response);
      return;
    }
    switch (Page.valueOf(request.getParameter(sServletAction))) {
    	// --------------
	    case LOGIN: {
	    	// remove previous error messages
	    	if (request.getSession().getAttribute(sLoginFailed) != null) 
	    		request.getSession().removeAttribute(sLoginFailed);
	    	// TODO: try to switch to https. At least for login page.
	      String login = request.getParameter(sLogin);
	      String password = request.getParameter(sPassword);
	      if ((login == null) || (login == "") || (password == null) || (password == "")) {
	        pageErrors.add(Errors.LOGIN_FAILED);
	        request.getSession().setAttribute(sPageErrors, pageErrors);
	        request.getSession().setAttribute(sLoginFailed, sLoginFailed);
	        request.getRequestDispatcher(sLoginPage).forward(request, response);
	        return;
	      }
	      // Retrieve user's information
	      User currentUser = Common.loadUserAccount(login);
	      if (currentUser.getPassword() == null || !password.equals(currentUser.getPassword())) {
	        pageErrors.add(Errors.LOGIN_FAILED);
	        request.getSession().setAttribute(sPageErrors, pageErrors);
	        request.getSession().setAttribute(sLoginFailed, sLoginFailed);
	        request.getRequestDispatcher(sLoginPage).forward(request, response);
	        return;
	      } else {
	        request.getSession(true).invalidate();
	        request.getSession(true); // makes new session
	        currentUser = Common.loadUserData(currentUser);
	        request.getSession().setAttribute(sCurrentUser, currentUser.getJSON());
	        request.getSession().setAttribute(sCurrentPage, Page.HOME.name());
	        request.getRequestDispatcher(sHomePage).forward(request, response);
	      }
	    } break; // case "NEW_LOGIN":
	    // --------------
	    case UPDATE_USER_INFO: {
	    	String name = request.getParameter(sName);
	    	String familyName = request.getParameter(sFamilyName);
	    	String phone = request.getParameter(sPhone);
	    	String email = request.getParameter(sEmail);
	    	String childName = request.getParameter(sChildName);
	    	String childFamilyName = request.getParameter(sChildFamilyName);
	    	String cityRegistration = request.getParameter(sCityRegistration);
	    	User currentUser = (User)request.getSession().getAttribute(sCurrentUser);
	    	System.out.println("Current user: " + currentUser.getName() + "  " + currentUser.getFamilyName());
	    	if (true) return;
	    	currentUser.setName(name);
	    	currentUser.setFamilyName(familyName);
	    	currentUser.setPhone(phone);
	    	currentUser.setEmail(email);
	    	currentUser.setChildName(childName);
	    	currentUser.setChildFamilyName(childFamilyName);
	    	currentUser.setCityRegistration(cityRegistration);
	    	Common.saveUserData(currentUser);
	    	request.getSession().setAttribute(sCurrentPage, Page.HOME.name());
	      request.getRequestDispatcher("/jsp/main.jsp").forward(request, response);
	    } break;
	 // --------------
	    case CREATE_TIMESLOTS: {
	    	String date = request.getParameter(sDate);
	    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDateTime ldt = LocalDateTime.parse(date, formatter);
        System.out.println("date: " + formatter.format(ldt));
        if (true) return;
	    	//reserve 
	    } break;
	    // --------------
	    case RESERVE_TIMESLOT: {
	    	String dateTime = request.getParameter(sDateTime);
	    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm dd.MM.yyyy");
        LocalDateTime ldt = LocalDateTime.parse(dateTime, formatter);
	    	//reserve 
	    } break;
	    // --------------
	    case GET_TABLE: {
	    	LocalDateTime ldt = LocalDateTime.now();
	    	if (request.getSession().getAttribute("prevweek") != null) {
	    		ldt = LocalDateTime.parse((String)request.getSession().getAttribute("prevweek"), 
	    				DateTimeFormatter.ofPattern("dd.MM.yyyy"));
	    	}
	    	if (request.getSession().getAttribute("nextweek") != null) {
	    		ldt = LocalDateTime.parse((String)request.getSession().getAttribute("nextweek"), 
	    				DateTimeFormatter.ofPattern("dd.MM.yyyy"));
	    	}
	    	ScheduleInfo[][] scheduleInfo = new ScheduleInfo[7][12];
	    	for (int i = 0; i < scheduleInfo.length; i++) {
	    		for (int j = 0; j < scheduleInfo[i].length; j++) {
	    			//scheduleInfo[1][3].state = 
	    		}
	    	}
	    	request.getSession().setAttribute("table", scheduleInfo);
	    } break;
	    // --------------
	    // --------------
	    // --------------
    } // switch (sServletAction
	}
	// =============================================================
	public static String formatHtml(String data) {
		String s = data.trim();
		StringBuffer sb = new StringBuffer();
		int n = s.length();
		for (int i = 0; i < n; i++) {
			char c = s.charAt(i);
			switch (c) {
				case '<': sb.append("&lt;"); break;
				case '>': sb.append("&gt;"); break;
				case '&': sb.append("&amp;"); break;
				case '"': sb.append("&quot;"); break;
				case '\'': sb.append("&apos;"); break;
				default:  sb.append(c); break;
			}
		}
		return sb.toString();
	}
	// ----------------------
	public final class ScheduleInfo {
		String time;
		String state; // taken, free, notav, booked
		int Id;
	}
	//----------------------
	
	//----------------------
	
	//----------------------
	private void reserve(User user, LocalDateTime ldt) {
		/*if (ldt.getDayOfYear() < LocalDateTime.now().getDayOfYear() + 1) return;
		Connection dbConnection = connectToDB(db, pgLogin, pgWord);
		if (dbConnection == null) {
			System.out.println("Error occured during connection to the DataBase. \n");
		}
		DateTimeFormatter dateOld = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		DateTimeFormatter timeOld = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		ldt = LocalDateTime.parse(ldt, formatter);
		// check and change, if necessary the old appointment
		if (user.getMeetingAt() != null & user.getMeetingAt().compareTo(LocalDateTime.now()) > 0) {
			try { // executing a query
				Statement st = dbConnection.createStatement();
				String query = "SELECT pk, " + cmDate + ", " + cmTimeStarts + ", " + cmStatus + ", " + cmUserPK +
						", " + " FROM " + tbAppntmnt + " WHERE " 
						+ cmLogin + "='" + login + "';";
				ResultSet rs = st.executeQuery(query);
				String password = null;
				int personId = 0;
				Role role = null;
				while (rs.next()) {
					password = rs.getString(1);
					personId = Integer.parseInt(rs.getString(2));
					role = Role.valueOf(rs.getString(3));
				}
				rs.close();
				st.close();
				try {
					dbConnection.close();
				} catch (SQLException e) {
					System.out.println("There was a problem with closing the connection to the DataBase. \n" + e.getMessage() + "\n");
					System.out.println("StackTrace: " + e.getStackTrace());
				}
			}
		try { // executing a query
			Statement st = dbConnection.createStatement();
			String query = "SELECT " + cmPassword + ", pk, " + cmRole + " FROM " + tbAppntmnt + " WHERE " 
					+ cmLogin + "='" + login + "';";
			ResultSet rs = st.executeQuery(query);
			String password = null;
			int personId = 0;
			Role role = null;
			while (rs.next()) {
				password = rs.getString(1);
				personId = Integer.parseInt(rs.getString(2));
				role = Role.valueOf(rs.getString(3));
			}
			rs.close();
			st.close();
			try {
				dbConnection.close();
			} catch (SQLException e) {
				System.out.println("There was a problem with closing the connection to the DataBase. \n" + e.getMessage() + "\n");
				System.out.println("StackTrace: " + e.getStackTrace());
			}
			return (new User(login, password, role));
		} catch (SQLException e) {
			System.out.println("Error occured during execution of DataBase query. \n" + e.getMessage() + "\n");
			System.out.println("StackTrace: " + e.getStackTrace());
			return new User();
		}*/
	}
	//----------------------
}
