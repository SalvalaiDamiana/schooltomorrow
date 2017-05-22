<%@ page language="java" contentType="text/html;charset=UTF-8" %>
<%@ page	import="schooltomorrow.MainServlet.*, java.util.*" %>
<%Set<Errors> pageErrors = (EnumSet<Errors>)request.getSession().getAttribute("pageErrors");
	if (pageErrors == null) pageErrors = EnumSet.noneOf(Errors.class);
%>
<!DOCTYPE html>
<html>
	<head>
    <link href="../css/main.css" rel="stylesheet" type="text/css" media = "all">
		<title>Login to the Appointment Entry Book></title>
	</head>
	<body>
		<div>
			<div class="h2">
				<br/><br/>
				<br/><br/><br/>
			</div>
			<form novalidate method="post" action="<%= request.getSession().getAttribute("URL") %>" id="id_loginform">
				<input name="continue" type="hidden" value="***">
				<input name="servletAction" type="hidden" value=<%=Page.LOGIN.name()%>>
				<input id="Login" name="uname" type="email" placeholder="Login" value="" spellcheck="false" class="" autofocus/>
				<br/><br/>
				<input id="Passwd" name="psw" type="password" placeholder="Password" value="" class=""/>
				<br /><br />
				<%if (pageErrors.contains(Errors.LOGIN_FAILED)) 
						out.println("The user name or password is incorrect.<br />Check the state of the Caps Lock key on your keyboard.");
				%>
				<br /><br />
				<input id="SignIn" name="signIn" type="submit" class="*rc-button" value="Login">
				<br /><br />
				<div class="bottom_center">
					<a href="/user/SendForgotPasswordLink">I forgot my password.</a><br/> <!-- ****  -->
					<a href="/user/CreateAccount">Create new Account.</a> <!-- ****  -->
				</div>
			</form>
		</div>
	</body>
</html>
<%request.getSession().removeAttribute("pageErrors");%>