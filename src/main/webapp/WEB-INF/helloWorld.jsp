<%@ taglib prefix="authz" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
</head>
<body>

<p>Hello World ( <c:out value="${securityLevel}" /> )</p>

                    <authz:authorize access="!hasRole('ROLE_USER')">
						<p>You are not logged in. <a href="/protected"></p>
						<p>Attempt to access</a> a protected resource</p>
                    </authz:authorize>
                       
                       <p>
                      <authz:authorize access="hasRole('ROLE_USER_TWITTER')">
						You logged in with Twitter. 
                    </authz:authorize>
                      <authz:authorize access="hasRole('ROLE_USER_FACEBOOK')">
						You are connected with Facebook. 
                    </authz:authorize>
                    
                      <authz:authorize access="hasRole('ROLE_USER')">
						You are logged in locally as <c:out value="${userName}" /></p>
						<p><a href="/logout">Logout</a></p>
                    </authz:authorize>
                    <authz:authorize access="hasRole('ROLE_USER') and !hasRole('ROLE_USER_FACEBOOK')">
						<a href="/oauthconnect.jsp">Connect</a> your account with Facebook
                    </authz:authorize>
                    </p>
                 
</body>                    
</html>