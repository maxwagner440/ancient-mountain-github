<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="pageTitle" value="Home"/>
<%@include file="common/headerD.jspf" %>
<h1>More Coming Soon!</h1>

<div>Meal <c:out value="${log.mealNumber }"/></div>
<div><c:out value="${log.getTrueDate() }"/></div>
<div><c:out value="${log.meal }"/></div>


<div>Protein: <c:out value="${log.protein }"/>g</div>
<div>Carbohydrates: <c:out value="${log.carbs }"/>g</div>
<div>Fat: <c:out value="${log.fat }"/>g</div>

<div>Total: <c:out value="${log.caloriesConsumed }"/> Calories</div>














<div id="piechart"></div>
<!--     Load Pie Chart -->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
<script type="text/javascript">
// Load google charts
google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);

// Draw the chart and set the chart values
function drawChart() {
	

  var data = google.visualization.arrayToDataTable([
	 
  ['Task', 'Hours per Day'],
  ['Protein', ${log.protein}],
  ['Carbohydrates', ${log.carbs}],
  ['Fat', ${log.fat}],

]);

  // Optional; add a title and set the width and height of the chart
  var options = {'title':'Meal Breakdown', 'width':400, 'height':300, is3D: true};

  // Display the chart inside the <div> element with id="piechart"
  var chart = new google.visualization.PieChart(document.getElementById('piechart'));
  chart.draw(data, options);
}
</script>


<%@include file="common/footer.jspf" %>