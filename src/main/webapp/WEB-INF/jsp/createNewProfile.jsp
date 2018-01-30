<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="pageTitle" value="Home"/>
<%@include file="common/header.jspf" %>

<div class="login-wrapper">
	<div class="two">
	<h3>Lets Get Some Information First:</h3>
	<br>
<c:url var="newUserUrl" value="/createNewProfile"/>
<form:form method="POST" action="${newUserUrl}" modelAttribute="client">
	
	<label for="username"></label>
	<form:input path="username" placeholder="Username"/>
	<form:errors path="username" cssClass="error"/>
	<br>
	<br>
	<label for="password"></label>
	<input type="password" name="password" placeholder="Password"/>
	<br>
	<br>
	<label for="firstName"></label>
	<form:input path="firstName" placeholder="First Name"/>
	<form:errors path="firstName" cssClass="error"/>
	<br>
	<br>
	<label for="lastName"></label>
	<form:input path="lastName" placeholder="Last Name"/>
	<form:errors path="lastName" cssClass="error"/>
	<br>
	<br>
	<label for="age"></label>
	<form:input path="age" placeholder="Age"/>
	<form:errors path="age" cssClass="error"/>
	<br>
	<br>
	<label for="weightInLbs"></label>
	<form:input path="weightInLbs" placeholder="Weight in Lbs"/>
	<form:errors path="weightInLbs" cssClass="error"/>
	<br>
	<br>
	<label for="height"></label>
	<form:input path="height" placeholder="Height in Inches"/>
	<form:errors path="height" cssClass="error"/>
	<br>
	<br>
	<label for=gender>Gender:</label>
	<form:radiobutton path="gender" value="female" /> Female<br>
	<form:radiobutton path="gender" value="male" /> Male<br>
	<form:errors path="gender" cssClass="error"/> 
	<br>
		
	<label for="goalWeightInLbs"></label>
	<form:input path="goalWeightInLbs" placeholder="Goal Weight in lbs"/>
	<form:errors path="goalWeightInLbs" cssClass="error"/>
	<div>
	<br>
	<input type="submit" class="w3-xlarge w3-round-large w3-hover-white" value="Submit"/>
	</div>
</form:form>

</div>
</div>

<%@include file="common/footer.jspf" %>
