<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="pageTitle" value="Home"/>
<%@include file="common/headerD.jspf" %>

<h1>Personal Info</h1>


	<c:url var="nfoChange" value="/infoChange"/>
		<form method="POST" action="${infoChange}">

		<label for="protein">Grams of Protein:</label>
		<input type="text" name="protein" placeholder="Grams of Protein"/>
		<label for="carbs">Grams of Carbs:</label>
		<input type="text" name="carbs" placeholder="Grams of Carbs"/>
		<label for="fat">Grams of Fat:</label>
		<input type="text" name="fat" placeholder="Grams of Fat"/>
		<label for="meal">Meal:</label>
		<input type="text" name="meal" placeholder="What did you eat?"/>
		<label for="mealNumber">Meal Number:</label>
		<select name="mealNumber">
		      <c:forEach items="${mealsLeft}" var="meal">
			 		<option id="select-<c:out value="${meal}"/>" value="<c:out value="${meal}"/>"><c:out value="${meal}"/></option>
			 		<c:out value="${meal}"/>
			  </c:forEach>
<!-- 			  <option id="select-two"value="2">Two</option> -->
<!-- 			  <option id="select-three" value="3">Three</option> -->
<!-- 			  <option id="select-four" value="4">Four</option> -->
<!-- 			  <option id="select-five" value="5">Five</option> -->
<!-- 			  <option id="select-six" value="6">Six</option> -->
			</select>
	
		<input type="hidden" name="clientId" value="${clientSession.clientId }"/>
		<input type="submit" value="Submit"/>
		</form>


<%@include file="common/footer.jspf" %>