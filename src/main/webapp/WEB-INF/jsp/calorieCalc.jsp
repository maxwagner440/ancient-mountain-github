<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="pageTitle" value="Calorie Calc" />
<%@include file="common/headerD.jspf"%>






	<c:url var="calorieCalc" value="/calorieCalc"/>
		<form method="POST" action="${calorieCalc}">

				<label for="activityLevel">Activity Level:</label>
				<select name="activityLevel">
					  <option id="select-one"value="1">One</option> 
					  <option id="select-two"value="2">Two</option> 
		 			  <option id="select-three" value="3">Three</option> 
		 			  <option id="select-four" value="4">Four</option> 
					  <option id="select-five" value="5">Five</option> 
				</select>

				<input type="submit"/>
		</form>

















<%@include file="common/footer.jspf"%>