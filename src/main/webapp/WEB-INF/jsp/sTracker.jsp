<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="pageTitle" value="Home"/>
<%@include file="common/headerD.jspf" %>





<h1><span class="blue"></span>Purchase Report<span class="blue"></span> <span class="yellow"></span></h1>
<h2><c:out value="${client.firstName} ${client.lastName }"/></h2>
<h3 style="text-align: center;"><a href="https://venmo.com/account/sign-in">Request More Sessions</a></h3>
<table class="container">
	<thead>
		<tr>
			<th><h1>Sessions Bought</h1></th>
			<th><h1>Date Bought</h1></th>
			<th><h1>Sessions Used</h1></th>
			<th><h1>Sessions Left</h1></th>
		</tr>
	</thead>
	<tbody>
	<c:forEach items="${sessions}" var="session">

		<tr class="${session.sessionId}">
			
			<td id="amt-bt">
				<c:out value="${session.amountBought }"/>
			</td>
			
			<td id="date"><c:out value="${session.getTrueDate() }"/></td>
			<td id="amt-used"><c:out value="${session.amountUsed }"/></td>
			<td id="amt-left"><c:out value="${session.amountBought - session.amountUsed} "/></td>
			
			<td>				
				<c:url value="/sTracker" var="individual"/>
				<form method="POST" action="${individual}">
					<input type="hidden" name="sessionId" value="${session.sessionId }"/>
					<input type="hidden" name="clientId" value="${session.clientId }"/>
					<input type="submit" value="More Info" class="sub"/>
				</form>
			</td>
			
		</tr>
		
	</c:forEach>
	</tbody>
</table>
<%@include file="common/footer.jspf" %>