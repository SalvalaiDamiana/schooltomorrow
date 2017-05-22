<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ page	import="schooltomorrow.MainServlet.*, schooltomorrow.Common.*, java.util.*" %>
<%User user = (User)request.getSession().getAttribute("currentUser");%>
<!DOCTYPE html>
<html>
	<head>
		<meta name="robots" content="noindex, nofollow" />
    <meta name="keywords" content="***" />
    <script> 
    	var currentUser = JSON.parse
    </script>
    <link href = "../css/main.css" rel = "stylesheet" type = "text/css" media = "all">
		<title>Appointment Entry Book</title>
	</head>
	<body>
		<form method="post" action="<%= request.getSession().getAttribute("URL") %>">
			<input name="servletAction" type="hidden" value="UPDATE_USER_INFO">
			Name: <input name="name" type="text" placeholder="Name of the Parent" value="<%=user.getName()%>" spellcheck="true"/><br><br>
			Family Name: <input name="familyName" type="text" placeholder="Family Name of the Parent" value="<%=user.getFamilyName()%>" spellcheck="true"/><br><br>
			Phone: <input name="phone" type="text" placeholder="Phone number" value="<%=user.getPhone()%>" spellcheck="true"/><br><br>
			<!-- E-mail: <input name="email" type="email" placeholder="E-mail address" value="<%=user.getEmail()%>" spellcheck="false"/><br><br>-->
			Child's Name: <input name="childName" type="text" placeholder="Name of the Child" value="<%=user.getName()%>" spellcheck="true"/><br><br>
			Child's Family Name: <input name="childFamilyName" type="text" placeholder="Family Name of the Child" value="<%=user.getName()%>" spellcheck="true"/><br><br>
			City of Registration: <input name="cityRegistration" type="text" placeholder="City of Registration" value="<%=user.getName()%>" spellcheck="true"/><br><br>
			<input name="Submit" type="submit" value="Save Changes">
		</form>
		=======================================================================
		<br/><br/>
		<form method="post" action="<%= request.getSession().getAttribute("URL") %>">
			<input name="servletAction" type="hidden" value="CREATE_TIMESLOTS">
			Open timeslots for the following week, <br>starting with the date<br>
			<input name="date" type="text" placeholder="dd.MM.yyyy" value="" spellcheck="false" class="" autofocus/><br><br>
			<input name="Submit" type="submit" value="Create Timeslots">
		</form>
		<br />
		=======================================================================
		<br/><br/>
		<form method="post" action="<%= request.getSession().getAttribute("URL") %>">
			<input name="servletAction" type="hidden" value="RESERVE">
			<input name="dateTime" type="text" placeholder="Date and Time (H:mm dd.MM.yyyy)" value="" spellcheck="false" class="" autofocus/>
			<input name="Submit" type="submit" value="Reserve Timeslot">
		</form>
		<br />
		=======================================================================
		<br/><br/>
		<form method="post" action="/file" enctype="multipart/form-data">
			<input name="action" type="hidden" value="upload">
			<input name="destination" type="hidden" value="<%=request.getSession().getAttribute("schoolId")%>">
  	<input name="fileField" type="file" size="10">
  	<input name="Submit" type="submit" value="Отправить файл">
		</form>
		<br/>
		=======================================================================
		<br/><br/>
		<br/>
		<img src="/file?action=view&id=5664683906301952">
		<br/>
		<br/>
		<br/>
		=======================================================================
		<br/><br/>
		<form method="post" action="/<%= request.getSession().getAttribute("schoolId") %>">
			<input name="servletAction" type="hidden" value="create_test">
			<input name="Submit" type="submit" value="Создать контрольную работу">
		</form>
		<br/>
		=======================================================================
		<br/><br/>
		<form method="post" action="/<%= request.getSession().getAttribute("schoolId") %>">
			<input name="servletAction" type="hidden" value="just_testing">
			<input name="Submit" type="submit" value="Just testing">
		</form>
		<br/>
		=======================================================================
		<br/><br/>
		<a href="/file?action=download&id=6614661952700416">Download</a>
		<br/>
		=======================================================================
		<br/><br/>
	</body>
</html>