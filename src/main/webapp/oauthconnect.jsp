 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
 <html>
 <head>
 </head>
 <body>
   <p>
	You are logged in locally with Twitter account <c:out value="${userProfile.userName}" /></p>


<p>Please connect your local account with a another third party provider</p>

  <form class="login"action="http://localhost:8080/connect/facebook" method="POST">
	<p><input type="submit" value="Connect with Facebook" /></p>
</form> 
</body>
</html>

