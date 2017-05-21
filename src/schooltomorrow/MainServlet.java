package schooltomorrow;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
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
	private static String db = "jdbc:postgresql://localhost:5432/AIT";
	private static String pgLogin = "postgres";
	private static String pgWord = "86K624mt";
	private static String tbUsers = "users";
	private static String cmLogin = "login";
	private static String cmPassword = "password";
	private static String cmRole = "role";
	private static String tbUserData = "userData";
	private static String cmName = "name";
	private static String cmFamilyName = "familyName";
	private static String cmPhone = "phone";
	private static String cmEmail = "email";
	private static String cmChildName = "childName";
	private static String cmChildFamilyName = "childFamilyName";
	private static String cmCityRegistration = "cityRegistration";
	private static String cmAppntmntPK = "appntmnt_pk";
	private static String cmUserPK = "user_pk";
	private static String tbAppntmnt = "appntmnt";
	private static String cmDate = "date";
	private static String cmTimeStarts = "timeStarts";
	private static String cmTimeEnds = "timeEnds";
	private static String cmStatus = "status";
	private static String cmDepartmentPK = "department_pk";
	private static String tbSchedule = "schedule";

	private static String sURL = "URL"; 
	private static String sLogin = "uname"; 
	private static String sPasswrd = "psw"; 
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
      request.getRequestDispatcher("/jsp/homepage.jsp").forward(request, response);
      return;
    }
    request.getSession().setAttribute(sCurrentPage, Page.HOME.name());
    request.getRequestDispatcher("/jsp/homepage.jsp").forward(request, response);
    
    if (true) return;
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		out.println("<!DOCTYPE html>\r\n" + "<html>\r\n" + " <head>\r\n" +
			" </head>\r\n" + " <body>\r\n");
				
		out.println("URL: " + request.getSession().getAttribute(sURL));
		if (true) return;
    out.println("</body></html>");
    out.close();
	}
	// =============================================================
	protected void doPost(HttpServletRequest request, HttpServletResponse response) 
			throws ServletException, IOException {
		
		if (request.getSession().getAttribute(sURL) == null) {
			request.getSession().setAttribute(sURL, "http://" + request.getServerName() 
			+ ":" + request.getServerPort() + request.getContextPath() + request.getServletPath());
		}
		response.setContentType("text/html");
    response.setCharacterEncoding("UTF-8"); 
    PrintWriter out = response.getWriter(); 
    out.println("<!DOCTYPE html>\r\n" + "<html>\r\n" + " <head>\r\n" +
  			" </head>\r\n" + " <body>\r\n");
    Enumeration<String> parameterNames = request.getParameterNames(); 
    while (parameterNames.hasMoreElements()) { 
    	// parameters could be read only one time
      String paramName = parameterNames.nextElement();
      out.print(paramName + ": "); 
      String[] paramValues = request.getParameterValues(paramName);
      String servletAction = "";
      for (int i = 0; i < paramValues.length; i++) {
      	
        String paramValue = paramValues[i];
        out.print(paramValue + "; "); 
      }
      out.println("<br><br>"); 
    }
    // Info for Errors on all pages.
    Set<Errors> pageErrors = EnumSet.noneOf(Errors.class);
    // This attribute is updated in order to delete info about previous errors
    request.getSession().setAttribute(sPageErrors, pageErrors);
    if (request.getSession().getAttribute(sCurrentUser) == null
        && !((String)request.getParameter(sServletAction)).equals(Page.LOGIN.name())) {
      request.getRequestDispatcher("/jsp/homepage.jsp").forward(request, response);
      return;
    }
    if (request.getParameter(sServletAction) == null) { 
      // have got here by mistake
      request.getSession().setAttribute(sCurrentPage, Page.HOME.name());
      request.getRequestDispatcher("/jsp/homepage.jsp").forward(request, response);
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
	      String password = request.getParameter(sPasswrd);
	      if ((login == null) || (login == "") || (password == null) || (password == "")) {
	        pageErrors.add(Errors.LOGIN_FAILED);
	        request.getSession().setAttribute(sPageErrors, pageErrors);
	        request.getSession().setAttribute(sLoginFailed, sLoginFailed);
	        request.getRequestDispatcher("/jsp/homepage.jsp").forward(request, response);
	        return;
	      }
	      // Retrieve user's information
	      User currentUser = loadUser(login);
	      if (currentUser.getPassword() == null || !password.equals(currentUser.getPassword())) {
	        pageErrors.add(Errors.LOGIN_FAILED);
	        request.getSession().setAttribute(sPageErrors, pageErrors);
	        request.getSession().setAttribute(sLoginFailed, sLoginFailed);
	        request.getRequestDispatcher("/jsp/homepage.jsp").forward(request, response);
	        return;
	      } else {
	        request.getSession(true).invalidate();
	        request.getSession(true); // makes new session

	        currentUser = loadUserData(currentUser);
	        request.getSession().setAttribute(sCurrentUser, currentUser);
	        request.getSession().setAttribute(sCurrentPage, Page.HOME.name());
	        request.getRequestDispatcher("/jsp/homepage.jsp").forward(request, response);
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
	    	out.println("Current user: " + currentUser.getName() + "  " + currentUser.getFamilyName());
	    	if (true) return;
	    	currentUser.setName(name);
	    	currentUser.setFamilyName(familyName);
	    	currentUser.setPhone(phone);
	    	currentUser.setEmail(email);
	    	currentUser.setChildName(childName);
	    	currentUser.setChildFamilyName(childFamilyName);
	    	currentUser.setCityRegistration(cityRegistration);
	    	saveUserData(currentUser);
	    	request.getSession().setAttribute(sCurrentPage, Page.HOME.name());
	      request.getRequestDispatcher("/jsp/main.jsp").forward(request, response);
	    } break;
	 // --------------
	    case CREATE_TIMESLOTS: {
	    	String date = request.getParameter(sDate);
	    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDateTime ldt = LocalDateTime.parse(date, formatter);
        out.println("date: " + formatter.format(ldt));
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
	    			
	    		}
	    	}
	    } break;
	    // --------------
	    // --------------
	    // --------------
    } // switch (sServletAction
    out.println("</body></html>");
    out.close();
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
	public static String createClassForPostgres() {
		try {
			String driver = "org.postgresql.Driver";
			Class.forName(driver);
			return "";
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			return "Error occured with PostgreSQL Driver. " + e.getClass().getName() + ": " + e.getMessage();
		}
	}
	// ----------------------
	public static Connection connectToDB(String dbname, String user, String password) {
		Connection dbConnection = null;
		try {
			Properties properties = new Properties();
			properties.setProperty("user", user);
			properties.setProperty("password", password);
			// props.setProperty("ssl","true");
			dbConnection = DriverManager.getConnection(dbname, properties);
			dbConnection.setAutoCommit(false);
			return dbConnection;
		} catch (SQLException e) {
			/* out.println("Error occured during connection to the DataBase. \n" + e.getMessage() + "\n");
			out.println("StackTrace: " + e.getStackTrace());*/
			return null;
		}
	}
	// ----------------------
	public final class ScheduleInfo {
		String time;
		String state;
	}
	// ----------------------
	public final class User {
	  private String login;
	  private String password;
	  private int personId;
	  private Role role;
	  private String name, familyName, phone, email, childName, childFamilyName, cityRegistration;
	  private LocalDateTime meetingAt = LocalDateTime.now();


	  public User() {}
	  
	  public User(String login, String password, Role role) {
	    this.login = login;
	    this.password = password;
	    this.role = role;
	    this.name = null;
	    this.familyName = null;
	    this.phone = null;
	    this.email = null;
	    this.childName = null;
	    this.childFamilyName = null;
	    this.cityRegistration = null;
	    this.meetingAt = null;
	  }
	  /**
	   * This constructor sets login, password, schoolKey and accountID
	   **/
	  public User(String login, String password, int personId, String name, String familyName, 
	  		String phone, String email, String childName, String childFamilyName, String cityRegistration,
	  		LocalDateTime meetingAt) {
	  	this.login = login;
	  	this.password = password;
	   	this.personId = personId;
	   	this.name = name;
	   	this.familyName = familyName;
	   	this.phone = phone;
	   	this.email = email;
	   	this.childName = childName;
	   	this.childFamilyName = childFamilyName;
	   	this.cityRegistration = cityRegistration;
	   	this.meetingAt = meetingAt;
	  }
	  public String getLogin() {return this.login;}
	  public void setLogin(String login) {
	    this.login = login;
	  }
	  public String getPassword() {return this.password;}
	  public void setPassword(String password) {
		  this.password = password;
	  }
	  public int getPersonId() {return this.personId;}
	  public void setPersonId(int personId) {
	    this.personId = personId;
	  }
	  public Role getRole() {return this.role;}
	  public void setRole(Role role) {
	  	this.role = role;
	  }
	  public String getName() {return this.name;}
	  public void setName(String name) {
		  this.name = name;
	  }
	  public String getFamilyName() {return this.familyName;}
	  public void setFamilyName(String familyName) {
		  this.familyName = familyName;
	  }
	  public String getPhone() {return this.phone;}
	  public void setPhone(String phone) {
		  this.phone = phone;
	  }
	  public String getEmail() {return this.email;}
	  public void setEmail(String email) {
		  this.email = email;
	  }
	  public String getChildName() {return this.childName;}
	  public void setChildName(String childName) {
		  this.childName = childName;
	  }
	  public String getChildFamilyName() {return this.childFamilyName;}
	  public void setChildFamilyName(String childFamilyName) {
		  this.childFamilyName = childFamilyName;
	  }
	  public String getCityRegistration() {return this.cityRegistration;}
	  public void setCityRegistration(String cityRegistration) {
		  this.cityRegistration = cityRegistration;
	  }
	  public LocalDateTime getMeetingAt() {return this.meetingAt;}
	  public void setMeetingAt(LocalDateTime meetingAt) {
	    this.meetingAt = meetingAt;
	  }
	}
	//----------------------
	private User loadUser(String login) {
		String result = createClassForPostgres();
		if (!result.equals("")) { // 
			System.out.println(result);
		}
		Connection dbConnection = connectToDB(db, pgLogin, pgWord);
		if (dbConnection == null) {
			System.out.println("Error occured during connection to the DataBase. \n");
		}
		try { // executing a query
			Statement st = dbConnection.createStatement();
			String query = "SELECT " + cmPassword + ", pk, " + cmRole + " FROM " + tbUsers + " WHERE " 
					+ cmLogin + "='" + login + "';";
			ResultSet rs = st.executeQuery(query);
			String password = null;
			int personId = 0;
			Role role = null;
			while (rs.next()) {
				password = rs.getString(1);
				personId = rs.getInt(2);
				role = Role.valueOf(rs.getString(3));
			}
			rs.close();
			st.close();
			dbConnection.close();
			return new User(login, password, role);
		} catch (SQLException e) {
			System.out.println("SQLException. " + e.getMessage());
			System.out.println("StackTrace: " + e.getStackTrace());
			return new User();
		}
	}
	//----------------------
	private User loadUserData(User user) {
		Connection dbConnection = connectToDB(db, pgLogin, pgWord);
		if (dbConnection == null) {
			System.out.println("Error occured during connection to the DataBase. \n");
		}
		try { // executing a query
			Statement st = dbConnection.createStatement();
			String query = "SELECT " + cmName + ", " + cmFamilyName + ", " + cmPhone + ", " + cmEmail + ", " 
				+ cmChildName + ", " + cmChildFamilyName + ", " + cmCityRegistration + ", " + cmAppntmntPK 
				+ " FROM " + tbUserData + " WHERE " + cmUserPK + "=" + user.getPersonId() + ";";
			//System.out.println(query);
			//if (true) return null;
			ResultSet rs = st.executeQuery(query);
			String name = null, familyName = null, phone = null, email = null, childName = null, 
					childFamilyName = null, cityRegistration = null;
			int appntmntPK = 0;
			while (rs.next()) {
				name = rs.getString(1);
				familyName = rs.getString(2);
				phone = rs.getString(3);
				email = rs.getString(4);
				childName = rs.getString(5);
				childFamilyName = rs.getString(6);
				cityRegistration = rs.getString(7);
				if (rs.getString(8) != null) appntmntPK = rs.getInt(8);
			}
			LocalDateTime ldt = null;
			if (appntmntPK > 0) {
				query = "SELECT " + cmDate + ", " + cmTimeStarts + ", " + cmStatus + ", " + cmUserPK + ", " 
					+ " FROM " + tbAppntmnt + " WHERE pk=" + appntmntPK + ";";
				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				while (rs.next()) {
					ldt = LocalDateTime.parse(rs.getString(1) + " " + rs.getString(2), formatter);
				}
			}
			rs.close();
			st.close();
			dbConnection.close();
			return new User(user.getLogin(), user.getPassword(), user.getPersonId(), name, familyName, phone,
				email, childName, childFamilyName, cityRegistration, ldt);
		} catch (SQLException e) {
			System.out.println("SQLException. " + e.getMessage());
			System.out.println("StackTrace: " + e.getStackTrace());
			return user;
		}
	} // private User loadUserData(User user)
	//----------------------
	private void saveUserData(User user) {
		if (user == null) return;
		Connection dbConnection = connectToDB(db, pgLogin, pgWord);
		if (dbConnection == null) {
			System.out.println("Error occured during connection to the DataBase. \n");
		}
		try { // executing a query
			Statement st = dbConnection.createStatement();
			String query = "UPDATE " + tbUserData + " SET " + cmName + ", " + cmFamilyName + ", " + cmPhone 
				+ ", " + cmEmail + ", " + cmChildName + ", " + cmChildFamilyName + ", " + cmCityRegistration 
				+ ", " + cmAppntmntPK + " WHERE " + cmUserPK + "=" + user.getPersonId() + ";";
			st.executeUpdate(query);
			dbConnection.commit();
			st.close();
			dbConnection.close();
		} catch (SQLException e) {
			System.out.println("SQLException. " + e.getMessage());
			System.out.println("StackTrace: " + e.getStackTrace());
		}
	}
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
