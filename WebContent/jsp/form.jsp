<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page  import="schooltomorrow.MainServlet.*, java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
<link href="css/main.css" rel="stylesheet" type="text/css">
</head>
<body>
	
	<form class="modal-content animate" action="<%= request.getSession().getAttribute("URL")%>" method="post">
	 <input name="servletAction" type="hidden" value="<%= Page.LOGOUT.name() %>"> 
		<button onclick="location.href='homepage.html'" id="firstbutton" >Logout</button> <br> 
	</form>


<!-- DISPLAY when i am a WORKER -->
	<!-- MODIFY AVAILABLE TIME SLOTS -->
	<button onclick="???to calendar, where will be loaded all the slots insered buy the worker???????" >Modify time slots</button>
	
	<button onclick="http://????????to the page-list of my appointment    resumeappoint.html    ???????" >Resume my appointment</button>
		
<!-- DISPLAY when i am a PARENT  -->
	<!-- COMPLETING REGISTRATION -->
	<button onclick="document.getElementById('id04').style.display='block'" id="firstbutton" >Completing registration</button>
	<!-- The Modal -->
	<div id="id04" class="modal">
	<span onclick="document.getElementById('id04').style.display='none'" class="close" title="Close Modal">&times;</span>
		<form class="modal-content animate" 
		 			action="http://????????????????????" method="post">	<!-- dove rimandare?? Al calendarioS -->
			<div class="container">

			<label>Parent's name </label>
			<input type="text" name="firstname" placeholder="Firstname"><br>
					  
			<label>Parent's surname </label>
			<input type="text" name="lastname" placeholder="Lastname"><br>
			
			<label>Phone number</label>
			<input type="tel" name="usrtel" /> <br/>
	
			<label>Student's name </label>
			<input type="text" name="firstname" placeholder="Firstname"><br>
					  
			<label>Student's surname </label>
			<input type="text" name="lastname" placeholder="Lastname"><br>
		
		    <label>Birthday of the student</label>
		    <input type="date" name="date" /> <br/>
		
		    <label>City of registration of the student</label>
		    <input type="text" name="city" placeholder="City" list="cities"/> 
		
		    <div class="clearfix">
		    	<button type="button" onclick="document.getElementById('id04').style.display='none'" class="cancelbtn">Cancel</button>
		    	<button type="submit" class="signupbtn">Submit</button>
		    </div>

		   	</div>
	 	</form>
	</div>

	<!-- ACCOUNT SETTING  -->
	<button onclick="document.getElementById('id03').style.display='block'">Account setting</button>
	<!-- The Modal -->
	<div id="id03" class="modal">
	
	 <span onclick="document.getElementById('id03').style.display='none'" class="close" title="Close Modal">&times;</span>
	 <form class="modal-content animate" action="http://????????????????????" method="post" > 	<!-- dove rimandare?? Al calendarioS -->
	    <div class="container">
	      <label><b>Change Email</b></label>
	      <input type="text" placeholder="Enter Email" name="email" required>

	      <label><b>Change Password</b></label>
	      <input type="password" placeholder="Enter Password" name="psw" required>

	      <label><b>Confirm Password</b></label>
	      <input type="password" placeholder="Enter Password" name="psw" required>
	         
	      <div class="clearfix">
	        <button type="button" onclick="document.getElementById('id03').style.display='none'" class="cancelbtn">Cancel</button>
	        <button type="submit" class="signupbtn">Apply changes</button>
	      </div>
	      
	    </div>
	  </form>
	</div>

	<!-- NEW APPOINTEMENT -->
	<button onclick="document.getElementById('id05').style.display='none'" >New Appointment</button>
	<!-- The Modal -->
	<div id="id05" class="modal">
	<span onclick="document.getElementById('id05').style.display='none'" class="close" title="Close Modal">&times;</span>
	<form class="modal-content animate" 
	 			action="http://????????????????????" method="post">	<!-- dove rimandare?? Al calendarioS -->
	    <div class="container">
	    
	       <label>Topic of the appointment</label>
	       <input type="text" name="Topic" placeholder="Topic" list="topics"/> 
	            <datalist id = "topics">
	                <option value = "">
	                <option value = "Enrollment in the school">
	                <option value = "Receiving the enrolment certificate">
	            </datalist><br/>
	            
    	    <div class="clearfix">
		   		<button type="button" onclick="document.getElementById('id04').style.display='none'" class="cancelbtn">Cancel</button>
			   	<button type="submit" class="signupbtn">Submit</button>
		    </div>|
    	
    	</div>
   	</form>
	</div>
	
	
	<!-- CALCEL OR MODIFY APPOINTMENT -->
	<!-- The Modal -->
	<div id="id09" class="modal">
	<span onclick="document.getElementById('id09').style.display='none'" class="close" title="Close Modal">&times;</span>
	<form class="modal-content animate" 
	 			action="http:calendar.html" method="post">
	    <div class="container">
	       <div class="clearfix">
		  	<button type="submit" class="signupbtn">Cancel appointment</button><!-- display calendar (that show own appointment) -->
		  	<button type="submit" class="signupbtn">Move to another time slot</button><!-- display free time slots of the professor -->


		 	<button type="button" onclick="document.getElementById('id09').style.display='none'" class="cancelbtn">Cancel</button>
		   </div>
	    
	    
    	</div>
   	</form>
	</div>
	

</body>
</html>