<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
</head>
<body>

<p>Hello World : 

<c:choose>
<c:when test="${securityLevel eq 'Public'}" >

Public Area. 
<p> <a href="/protected">Attempt to access</a> a protected resource</p>
<p> <a href="/protected/twitter">Attempt to access</a> a Twitter protected resource</p>
<p> <a href="/protected/facebook">Attempt to access</a> a Facebook protected resource</p>
<p> <a href="/protected/facebookTwitter">Attempt to access</a> a Facebook and Twitter protected resource</p>

</c:when>
<c:otherwise>
<c:out value="${securityLevel}" /></p><p> <a href="/" >Home</a>
</c:otherwise>
</c:choose>
</p>

                    <authz:authorize access="!hasRole('ROLE_USER')">
						<p>You are not logged in. &nbsp;<a href="/oauthlogin.jsp" />Login</a></p>
                    </authz:authorize>
                                  <authz:authorize access="hasRole('ROLE_USER')">
						You are logged in locally as <c:out value="${userName}" />. &nbsp;<a href="/logout">Logout</a></p>
						
                    </authz:authorize>
                      
                      <authz:authorize access="hasRole('ROLE_USER_TWITTER')">
					 <p>	You are connected with Twitter. </p>
                    </authz:authorize>
                      <authz:authorize access="hasRole('ROLE_USER_FACEBOOK')">
						<p>You are connected with Facebook. </p>
                    </authz:authorize>

           
                    <authz:authorize access="hasRole('ROLE_USER') and !hasRole('ROLE_USER_FACEBOOK')">
						<p><a href="/oauthconnect.jsp">Connect</a> your account with Facebook</p>
                    </authz:authorize>
                    <authz:authorize access="hasRole('ROLE_USER') and !hasRole('ROLE_USER_TWITTER')">
						<p><a href="/oauthconnect.jsp">Connect</a> your account with Twitter</p>
                    </authz:authorize>
                       
                 
</body>                    
</html>