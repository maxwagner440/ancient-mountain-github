<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="pageTitle" value="Home"/>
<%@include file="common/headerD.jspf" %>

				

				<c:url value="/moreSessions" var="more"/>
				<form method="POST" action="${more}">
					<input type="text" name="amountBought" placeholder="Session Amount"/>
					<input type="text" name="cost" placeholder="Cost"/>
					<input type="hidden" name="clientId" value="${clientSession.clientId }"/>
					<input type="submit" value="Give Sessions" class="sub"/>
				</form>


		

<%@include file="common/footer.jspf" %>