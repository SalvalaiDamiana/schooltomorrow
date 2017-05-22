package schooltomorrow;

import schooltomorrow.MainServlet.Role;

public final class User {
  private String login;
  private String password;
  private int personId;
  private Role role;
  private String name, familyName, phone, email, childName, childFamilyName, cityRegistration;
  private int appntmntId;


  public User() {}
  
  public User(String login, String password, int personId, Role role) {
    this.login = login;
    this.password = password;
    this.personId = personId;
    this.role = role;
    this.name = null;
    this.familyName = null;
    this.phone = null;
    this.email = null;
    this.childName = null;
    this.childFamilyName = null;
    this.cityRegistration = null;
    this.appntmntId = 0;
  }

  public User(String login, String password, int personId, String name, String familyName, 
  		String phone, String email, String childName, String childFamilyName, String cityRegistration,
  		int appntmntId) {
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
   	this.appntmntId = appntmntId;
  }
  public String getJSON() {
  	String s;
  	s = "{" 
  		+ "\"login\":\"" + this.getLogin() + "\", " 
  		+ "\"personId\":" + this.getPersonId() + ", "
  		+ "\"name\":\"" + this.getName() + "\", "
  		+ "\"familyName\":\"" + this.getFamilyName() + "\", "
  		+ "\"phone\":\"" + this.getPhone() + "\", "
  		+ "\"email\":\"" + this.getEmail() + "\", "
  		+ "\"childName\":\"" + this.getChildName() + "\", "
  		+ "\"childFamilyName\":\"" + this.getChildFamilyName() + "\", "
	    + "\"cityRegistration\":\"" + this.getCityRegistration() + "\", "
	    + "\"appntmntId\":" + this.getAppntmntId() + " "
	    +	"}";
	  System.out.println(s);
	  return s;
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
  public int getAppntmntId() {return this.appntmntId;}
  public void setAppntmntId(int appntmntId) {
    this.appntmntId = appntmntId;
  }
}
