<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="pageTitle" value="clientLogin"/>
<%@include file="common/header.jspf" %>

<h2>Please Login First:</h2>

	<div class="login-wrapper">
	<div class="two">
<c:url var="tLoginUrl" value="/sessionTrackerLogin"/>
<form:form method="POST" action="${tLoginUrl}" modelAttribute="client" >
	

	
	<label for="username">Username:</label>
	<form:input path="username"  />
	<form:errors path="username"  cssClass="error"/>
	
	<label for="password">Password:</label>
	<input type="password" name="password" />
	<br><br>
	
<input type="submit" class="w3-xlarge w3-round-large w3-hover-white" value="Submit"/>


</form:form>

<c:out value="${message }"/>
</div>
</div>

<%@include file="common/footer.jspf" %>