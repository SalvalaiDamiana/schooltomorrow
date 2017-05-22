package schooltomorrow;

import schooltomorrow.MainServlet.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Properties;


public class Common {
	
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
	// ---------------------------------------------
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
	// ---------------------------------------------
	public static void createClassForPostgres() {
		try {
			String driver = "org.postgresql.Driver";
			Class.forName(driver);
		} catch (ClassNotFoundException e) {
			System.out.println("Error occured with PostgreSQL Driver. \n" + e.getMessage() + "\n" +
					"StackTrace: " + e.getStackTrace());
		}
	}
	// ---------------------------------------------
	private static Connection connectToDB(String dbname, String user, String password) {
		Connection dbConnection = null;
		try {
			Properties props = new Properties();
			props.setProperty("user", user);
			props.setProperty("password", password);
			// props.setProperty("ssl","true");
			dbConnection = DriverManager.getConnection(dbname, props);
			return dbConnection;
		} catch (SQLException e) {
			/* out.println("Error occured during connection to the DataBase. \n" + e.getMessage() + "\n");
			out.println("StackTrace: " + e.getStackTrace());*/
			return null;
		}
	}
	// ---------------------------------------------
	public static User loadUserAccount(String login) {
		createClassForPostgres();
		Connection dbConnection = connectToDB(db, pgLogin, pgWord);
		if (dbConnection == null) {
			System.out.println("Error occured during connection to the DataBase. \n");
		}
		try { // executing a query
			Statement st = dbConnection.createStatement();
			String query = "SELECT pk, " + cmPassword + ", " + cmRole + " FROM " + tbUsers + " WHERE " 
					+ cmLogin + "='" + login + "';";
			// System.out.println(query);
			ResultSet rs = st.executeQuery(query);
			String password = "";
			int personId = 0;
			Role role = null;
			while (rs.next()) {
				personId = rs.getInt(1);
				password = rs.getString(2);
				role = Role.valueOf(rs.getString(3));
			}
			rs.close();
			st.close();
			dbConnection.close();
			return new User(login, password, personId, role);
		} catch (SQLException e) {
			System.out.println("SQLException. " + e.getMessage());
			System.out.println("StackTrace: " + e.getStackTrace());
			return new User();
		}
	}
	// ---------------------------------------------
	public static User loadUserData(User user) {
		Connection dbConnection = connectToDB(db, pgLogin, pgWord);
		if (dbConnection == null) {
			System.out.println("Error occured during connection to the DataBase. \n");
		}
		try { // executing a query
			Statement st = dbConnection.createStatement();
			String query = "SELECT " + cmName + ", " + cmFamilyName + ", " + cmPhone + ", " + cmEmail + ", " 
				+ cmChildName + ", " + cmChildFamilyName + ", " + cmCityRegistration + ", " + cmAppntmntPK 
				+ " FROM " + tbUserData + " WHERE " + cmUserPK + "=" + user.getPersonId() + ";";
			System.out.println(query);
			ResultSet rs = st.executeQuery(query);
			String name = "", familyName = "", phone = "", email = "", childName = "", 
					childFamilyName = "", cityRegistration = "";
			int appntmntId = 0;
			while (rs.next()) {
				name = rs.getString(1);
				familyName = rs.getString(2);
				phone = rs.getString(3);
				email = rs.getString(4);
				childName = rs.getString(5);
				childFamilyName = rs.getString(6);
				cityRegistration = rs.getString(7);
				appntmntId = rs.getInt(8);
			}
			rs.close();
			st.close();
			dbConnection.close();
			return new User(user.getLogin(), user.getPassword(), user.getPersonId(), name, familyName, phone,
				email, childName, childFamilyName, cityRegistration, appntmntId);
		} catch (SQLException e) {
			System.out.println("SQLException. " + e.getMessage());
			System.out.println("StackTrace: " + e.getStackTrace());
			return user;
		}
	}
//---------------------------------------------
	public static void saveUserAccount(User user) {
		if (user == null) return;
		Connection dbConnection = connectToDB(db, pgLogin, pgWord);
		if (dbConnection == null) {
			System.out.println("Error occured during connection to the DataBase. \n");
		}
		try { // executing a query
			Statement st = dbConnection.createStatement();
			String query = "UPDATE " + tbUsers + " SET " + cmLogin + "='" + user.getLogin() + "', " 
				+ cmPassword +"='" + user.getPassword() + "', " + cmRole + "='" + user.getRole().name() 
				+ "' WHERE " + cmUserPK + "=" + user.getPersonId() + ";";
			System.out.println(query);
			st.executeUpdate(query);
			dbConnection.commit();
			st.close();
			dbConnection.close();
		} catch (SQLException e) {
			System.out.println("SQLException. " + e.getMessage());
			System.out.println("StackTrace: " + e.getStackTrace());
		}
	}// ---------------------------------------------
	public static void saveUserData(User user) {
		if (user == null) return;
		Connection dbConnection = connectToDB(db, pgLogin, pgWord);
		if (dbConnection == null) {
			System.out.println("Error occured during connection to the DataBase. \n");
		}
		try { // executing a query
			Statement st = dbConnection.createStatement();
			String query = "UPDATE " + tbUserData + " SET " + cmName + "='" + user.getName() + "', " 
				+ cmFamilyName +"='" + user.getFamilyName() + "', " + cmPhone + "='" + user.getPhone() + "', " 
				+ cmEmail + "='" + user.getEmail() + "', " + cmChildName + "='" + user.getChildName() + "', " 
				+ cmChildFamilyName + "='" + user.getChildFamilyName() + "', " + cmCityRegistration + "='" 
				+ user.getCityRegistration() + "', " + cmAppntmntPK + "=" + user.getAppntmntId() 
				+ " WHERE " + cmUserPK + "=" + user.getPersonId() + ";";
			System.out.println(query);
			st.executeUpdate(query);
			dbConnection.commit();
			st.close();
			dbConnection.close();
		} catch (SQLException e) {
			System.out.println("SQLException. " + e.getMessage());
			System.out.println("StackTrace: " + e.getStackTrace());
		}
	}
	// ---------------------------------------------
	// ---------------------------------------------
	// ---------------------------------------------
	// ---------------------------------------------
	// ---------------------------------------------
	// ---------------------------------------------
}
