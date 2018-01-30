<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="pageTitle" value="Home"/>
<%@include file="common/headerD.jspf" %>


<h1><span class="blue"></span>Session Report<span class="blue"></span> <span class="yellow"></span></h1>
<h2><c:out value="${client.firstName} ${client.lastName }"/></h2>

<table class="container">
	<thead>
		<tr>
			<th><h1>Date Bought</h1></th>
			<th><h1>Date Redeemed</h1></th>
			<th><h1>Cost Per Session</h1></th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${individualSessions}" var="sessions">


		<tr class="${sessions.sessionId}">
			
			<td ><c:out value="${sessions.getBoughtDate() }"/></td>
			<td id="red-text"><c:out value="${sessions.getTrueDate() }"/></td>
			<td ><c:out value="${sessions.cost }"/></td>
			<c:if test="${sessions.getTrueDate() == null }">
			<td> 
				<c:url value="/individualSessions" var="indiv"/>
				<form method="POST" action="${indiv}">
					<input type="hidden" name="sessionId" value="${sessions.sessionId }"/>
					<input type="hidden" name="individualSessionId" value="${sessions.individualSessionId }"/>
					<input type="hidden" name="clientId" value="${clientId }"/>
					<input type="submit" value="Redeem" class="sub"/>
				</form>
			</td>
			</c:if>
			
		</tr>
		
	</c:forEach>
	</tbody>
</table>

















<%@include file="common/footer.jspf" %>