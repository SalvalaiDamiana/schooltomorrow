<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page  import="schooltomorrow.MainServlet.*, java.util.*" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Arrange Appointments School of Tomorrow </title>
<link href="css/main.css" rel="stylesheet" type="text/css">
</head>

<body>

  <header>
  <!-- 
  	logo
  	
  	heading elements (<h1> - <h6>) ????
	LOGIN SIGNIN????  
    
    <fieldset> 
	    <button type="button" class="languagebtn">EN</button>
		<button type="button" class="languagebtn">RU</button>      
  	</fieldset>
	 -->
  
<!-- LOGIN e SIGNIN rimandano alla stessa pagina "registration" -->
	<fieldset id="modalbttn">
			<!-- LOGIN MODAL FORM -->
			<button onclick="document.getElementById('id01').style.display='block'">Login</button>
			
			<!-- The Modal -->
			<div id="id01" class="modal">
			<span onclick="document.getElementById('id01').style.display='none'" 
			class="close" title="Close Modal">&times;</span>
			 <!-- Modal Content -->
			 <form class="modal-content animate" action="<%= request.getSession().getAttribute("URL")%>" method="post">
			 <input name="servletAction" type="hidden" value="<%= Page.LOGIN.name() %>"> 
			 <div class="imgcontainer">
			     <img src="img/avatarAccount.png" alt="Avatar" class="avatar">
			   </div>
			
			   <div class="container">
			     <label><b>Username</b></label>
			     <input type="text" placeholder="Enter Username" name="uname" required>
						      <label><b>Password</b></label>
			     <input type="password" placeholder="Enter Password" name="psw" required>
						      <button type="submit">Login</button> <br>
			    
			     <input type="checkbox" checked="checked">Remember me<br>
			   </div>
			
				  <div class="container" style="background-color:#f1f1f1">
			     <button type="button" onclick="document.getElementById('id01').style.display='none'" class="cancelbtn">Cancel</button>
			     <span class="psw">Forgot <a href="#">password?</a></span>
			   </div>
			  </form>
			</div>
	
			<%/*alert 2 */
			if(request.getSession().getAttribute("loginFailed") != null)/*	SHOW THE FOLLOWING MODAL IF LOGIN FAIL */
			{ 
			%>    
	 		<!-- The Alert -->
				<div class="alert">
		  		<span class="closebtn" onclick="this.parentElement.style.display='none';">&times;</span> 
		  			Warning: user or password invalid
				</div>
			 <% 
				}
			%>
	
			<!-- SIGNUP MODAL FORM -->
			<!-- Button to open the modal -->
			<button onclick="document.getElementById('id02').style.display='block'">Sign Up</button>
			
			<!-- The Modal (contains the Sign Up form) -->
			<div id="id02" class="modal">
			 <span onclick="document.getElementById('id02').style.display='none'" class="close" title="Close Modal">&times;</span>
			 
			 <form class="modal-content animate" action="<%= request.getSession().getAttribute("URL")%>" method="post">
			 <input name="servletAction" type="hidden" value="<%= Page.LOGIN.name() %>">   
			    
			    <div class="container">
			      <label><b>Email</b></label>
			      <input type="text" placeholder="Enter Email" name="email" required>
			
			      <label><b>Password</b></label>
			      <input type="password" placeholder="Enter Password" name="psw" required>
			
			      <label><b>Repeat Password</b></label>
			      <input type="password" placeholder="Repeat Password" name="psw-repeat" required><br>
			      <input type="checkbox" checked="checked"> Remember me<br>
			      <p>By creating an account you agree to our <a href="#">Terms and Privacy</a>.</p>
			
			      <div class="clearfix">
			        <button type="button" onclick="document.getElementById('id02').style.display='none'" class="cancelbtn">Cancel</button>
			        <button type="submit" class="signupbtn">Sign Up</button>
			      </div>
			    </div>
			  </form>
			</div>		 
		 
	</fieldset> 
		 
		 		 
  </header>		 
		 
		 
		 	<!-- SLIDESHOW -->	
<div class="color-slide">		 						
	<div class="slideshow-container">
	  <div class="mySlides fade">
	    <div class="numbertext">1 / 3</div>
		    <p class="paragr-content">
			Integer rutrum purus at diam laoreet aliquam. Duis mi mi, mollis ac est quis, 
			convallis tincidunt elit. Phasellus enim nisi, ullamcorper sed dui in, facilisis 
			fermentum felis. Pellentesque lacus quam, ultrices in consectetur vitae, congue ut elit.
		    Integer vitae euismod erat. Pellentesque habitant morbi tristique senectus et netus et
			malesuada fames ac turpis egestas. Cras porta orci quis enim ultricies, eu blandit libero 
			viverra. Nunc molestie efficitur urna eu mollis. Aliquam feugiat sem non tellus mollis commodo. 
			Praesent non mattis magna.Suspendisse potenti. Aliquam at mauris at justo porta 
			consectetur ac ac sapien. Suspendisse tempor blandit malesuada. Mauris efficitur velit
			vehicula nisi semper, vitae pharetra felis sagittis. Vestibulum at semper risus, sed varius
			elit. Ut porttitor quis augue at commodo. Nunc eu imperdiet lorem. Fusce et gravida metus.
			</p>
	    <div class="text">Caption One</div>
	  </div>
	
	  <div class="mySlides fade">
	    <div class="numbertext">2 / 3</div>
	    <p class="paragr-content">
		    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus
		    elementum ut nisi ac aliquam. Donec tincidunt, eros eleifend vehicula
		    vehicula, est metus congue nisl, ut facilisis elit ex a nisl. Etiam volutpat 
		    feugiat mauris vitae tincidunt. Cras porta orci quis enim ultricies, eu blandit 
		    libero viverra. Nunc molestie efficitur urna eu mollis. Aliquam feugiat sem non 
		    tellus mollis commodo. Praesent non mattis magna.Nam posuere quis ante at pharetra. Integer
		    scelerisque ipsum et augue cursus, ac fermentum dolor rutrum. Quisque egestas
		    erat lacus, vitae finibus quam tristique id. Donec vitae ante a lacus faucibus auctor.
		    Integer ut quam varius, volutpat erat nec, blandit nunc. Curabitur ut convallis lacus. 
		    Morbi dapibus eu purus a ultricies. Aliquam consectetur enim eget vestibulum aliquet.
			</p>
	    <div class="text">Caption Two</div>
	  </div>
	
	  <div class="mySlides fade">
	    <div class="numbertext">3 / 3</div>
	    <p class="paragr-content">
	    Cras porta orci quis enim ultricies, eu blandit libero viverra. Nunc molestie efficitur urna 
	    eu mollis. Aliquam feugiat sem non tellus mollis commodo. Nunc molestie efficitur urna eu mollis. 
	    Aliquam feugiat sem non tellus mollis commodo. Praesent non mattis magna.Nam posuere quis ante at 
	    pharetra. Integer scelerisque ipsum et augue cursus, ac fermentum dolor rutrum. Quisque egestas
		erat lacus, vitae finibus quam tristique id.Praesent non mattis magna. Phasellus enim nisi, ullamcorper 
		sed dui in, facilisis fermentum felis. Pellentesque lacus quam, ultrices in consectetur vitae, congue ut
		elit.Integer vitae euismod erat.In vel venenatis nunc, nec mollis justo. Etiam posuere ipsum sodales 
		arcu ornare, eu fringilla nibh tempus.Duis scelerisque tortor quis tincidunt consequat. Nunc rhoncus
		posuere tristique. Praesent non mattis magna.Suspendisse potenti. 
	    </p>
	    <div class="text">Caption Three</div>
	  </div>
	
	  <a class="prev" onclick="plusSlides(-1)">&#10094;</a>
	  <a class="next" onclick="plusSlides(1)">&#10095;</a>
	</div>
	<br>
	
	<div style="text-align:center">
	  <span class="dot" onclick="currentSlide(1)"></span> 
	  <span class="dot" onclick="currentSlide(2)"></span> 
	  <span class="dot" onclick="currentSlide(3)"></span> 
	</div>
</div>	

<footer> <p class="footer">International School of Tomorrow © 2016 Privacy Policy</p> </footer>	
	
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="js/slideshow.js"></script>
</body>
</html>