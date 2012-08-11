<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
 
 <html>
 <head>
 </head>
 <body>
   <p>


<p>Please connect your local account with Twitter</p>

<authz:authorize access="!hasRole('ROLE_USER_TWITTER')">

 <form class="login"action="http://localhost:8080/connect/twitter" method="POST">
	<p><input type="submit" value="Connect with Twitter" /></p>
</form> 
</authz:authorize>

</body>
</html>

