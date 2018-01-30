<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="pageTitle" value="Home"/>
<%@include file="common/headerD.jspf" %>





<h1><span class="blue"></span>Client Purchase Report<span class="blue"></span> <span class="yellow"></span></h1>
<h2><c:out value="${client.firstName} ${client.lastName }"/></h2>



<table class="container">
	<thead>
		<tr>
			<th><h1>Name</h1></th>
			<th><h1>Sessions Bought</h1></th>
			<th><h1>Date Bought</h1></th>
			<th><h1>Sessions Used</h1></th>
			<th><h1>Sessions Left</h1></th>
			
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${allSessions}" var="session">

		<tr class="${session.sessionId}">
			<td> <c:out value="${session.firstName} ${ session.lastName  }"/>
			<td id="amt-bt">
				<c:out value="${session.amountBought }"/>
			</td>
			
			<td id="date"><c:out value="${session.getTrueDate() }"/></td>
			<td id="amt-used"><c:out value="${session.amountUsed }"/></td>
			<td id="amt-left"><c:out value="${session.amountBought - session.amountUsed }"/></td>
			
			<td>
			<c:url value="/allSessions" var="allSessions"/>
				<form method="POST" action="${allSessions}">
					<input type="hidden" name="sessionId" value="${session.sessionId }"/>
					<input type="submit" value="Give More Sessions" class="sub"/>
				</form></td>
			<td>				
				<c:url value="/sTracker" var="individual"/>
				<form method="POST" action="${individual}">
					<input type="hidden" name="sessionId" value="${session.sessionId }"/>
					<input type="hidden" name="clientId" value="${session.clientId }"/>
					<input type="submit" value="Individual Sessions" class="sub"/>
				</form>
			</td>
			<td>
				<c:url value="/delete" var="delete"/>
				<form method="POST" action="${delete}">
					<input type="hidden" name="sessionId" value="${session.sessionId }"/>
					<input type="hidden" name="clientId" value="${session.clientId }"/>
					<input type="submit" value="Delete This Purchase" class="sub"/>
				</form>
			</td>
			
		</tr>
		
	</c:forEach>
	</tbody>
</table >

<table  class="container">
<thead>
		<tr>
			<th><h1>Name</h1></th>
			<th><h1>Give More Sessions</h1>
			
		</tr>
	</thead>
<tbody>

<c:forEach items="${allClients }" var="client">
	<tr>
		<td><strong><c:out value="${client.firstName } ${client.lastName }"/></strong>
		</td>
		<c:url value="/giveSessions" var="giveSessions"/>
		<td><form method="POST" action="${giveSessions}">
					<input type="text" name="amountBought" placeholder="Session Amount"/>
					<input type="text" name="cost" placeholder="Cost"/>
					<input type="hidden" name="clientId" value="${client.clientId }"/>
					<input type="submit" value="Give More Sessions" class="w3-xlarge w3-round-large w3-hover-white"/>
				</form>
		</td>
	
	</tr>
	</c:forEach>
</tbody>
</table>
<%@include file="common/footer.jspf" %>