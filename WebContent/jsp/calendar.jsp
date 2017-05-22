<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page  import="schooltomorrow.MainServlet.*, java.util.*" %>

<!DOCTYPE html>
<html>
<script src="https://www.w3schools.com/lib/w3.js"></script>	
<head>
	<meta charset="ISO-8859-1">
	<title>Arrange Appointments School of Tomorrow </title>
	<link href="css/main.css" rel="stylesheet" type="text/css">
</head>
<body>

	<%/*alert 4 */
		if( ((User)request.getSession().getAttribute("currentUser")).getCityRegistration() == null   )/*	SHOW THE FOLLOWING MODAL IF CHANGIES SUCCEDED  */
		{ 
	%>    
	 <!-- The Alert -->
		<div class="alert">
  		<span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
  			Please, complite the registration before arrange an appointment
		</div>
	 <% 
		}
	%>

	
<header><!--   	logo     -->
   		<form action="<%= request.getSession().getAttribute("URL")%>" method="post" method="post">	
		<input name="servletAction" type="hidden" value="<%= Page.GET_TABLE.name() %>"> 
			<div class="container">

			   	<label>Select the department or the teacher</label>
		     	<input type="text" name="Topic" placeholder="Department" list="professionist"/> 
	            <datalist id = "professionist">
	                <option value = "Distance Learning">
	                <option value = "Full-time Tuition">
	                <option value = "Teacher A">
	                <option value = "Teacher B">
	                <option value = "Teacher C">
	                <option value = "Teacher D">
	            </datalist>
	            <br/>
			<button>Submit</button> <br> 
			</div>
	 	</form>
    
</header>	
<br>

<div id="content">

	<%/*alert 1 */
		if(request.getSession().getAttribute("settingsErrorMsg") != null)/*	SHOW THE FOLLOWING MODAL IF CHANGIES SUCCEDED  */
		{ 
	%>    
	 <!-- The Alert -->
		<div class="alert">
  		<span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
  			Success: the changes have been saved
		</div>
	 <% 
		}
	%>
	
	<div class="menu" id="ex_users"> <!-- parent side -->
	    <fieldset > 
	    	<jsp:include page='form.jsp' />
	    </fieldset>
	 </div>
	
	<div class="calendar" >
		<div class="week"> 
		  <fieldset>
	  	  	<p class="weeknoun" style="font-weight: bold;font-size: x-large"> 12 / 19 MAY </p> 
			   <form action="<%= request.getSession().getAttribute("URL")%>" method="post" method="post">	
					<input name="servletAction" type="hidden" value="<%= Page.GET_TABLE.name() %>"> 
					<input name="startingfrom" type="hidden" value="<%= request.getSession().getAttribute("prevweek") %>"> 
			    	<p class="prev">&#10094;</p> 
			   </form>
   
			   <form action="<%= request.getSession().getAttribute("URL")%>" method="post" method="post">	
					<input name="servletAction" type="hidden" value="<%= Page.GET_TABLE.name() %>"> 
					<input name="startingfrom" type="hidden" value="<%= request.getSession().getAttribute("nextweek") %>"> 
			    	<p class="next">&#10095;</p> 
			   </form>
		    <p class="weeknoun" style="font-weight: bold;">2016</p>
		  </fieldset>
		</div>
	<div id="calendar" style="overflow-x:auto;">  
	</div>
	</div>
</div>

<footer> <p class="footer">International School of Tomorrow © 2016 Privacy Policy</p> </footer>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="js/main.js"></script>

</body>
</html>