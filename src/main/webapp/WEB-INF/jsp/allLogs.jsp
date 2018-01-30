<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="pageTitle" value="Home" />
<%@include file="common/headerD.jspf"%>

<!-- 	TITLE -->

<h1>More Coming Soon!</h1>

<h2>Past Logs</h2>

<div class="create-log">
<c:url value="/newLog" var="newLog" />
<a href="${newLog}"><button class="bttn-stretch "><div class="create-log-ft">Create New Log</div></button></a>
</div>
<!-- 	GRAPH IN CSS GRID -->
<div class="row">
<div class="col-md-6">

	<div class="log-item">
		<div class="graph-heading">
			<strong>Calories Consumed</strong>
		</div>
		<div id="chart_div" class="graph"></div>
	</div>
</div>

<div class="col-md-6 ">
	<div class="graph-heading">
			<strong>Day Breakdown</strong>
		</div>
<div id="piechart" class="pie-graph"></div>
</div>
</div>
<div class="row">
	<div class="date-item">
		 <br>
		  Choose Date:
		<br>
		<br>
		<div>
			<c:url var="allLogs" value="/allLogs" />
			<form method="POST" action="${allLogs}">
				<input type="date" name="date" /> 
				
				<input type="submit" value="This Date" />
			</form>
		</div>
		<br>

		<fmt:formatDate type="date" value="${date }" />

	</div>

<br>
<br>



</div>
<br>
<br>

<div class="row pad-top">
<c:forEach items="${allInput}" var="meal">
	<div class="col-md-2 rt-border">
<%-- 	<div class="meal-item${meal.mealNumber}"> --%>
	<div>
		<c:url value="/toLog" var="toLog" />
		<form method="POST" action="${toLog}" class="entypo-search">
			<input type="hidden" name="mealLogId" value="${meal.mealLogId }" />

			<button class="try bttn-stretch" type="submit">
				<strong>Meal <c:out value="${meal.mealNumber }" /></strong>
			</button>

		</form>
	</div>

	<div>
		<c:url var="deleteLog" value="/deleteLog" />
		<form method="POST" action="${deleteLog}">
			<input type="hidden" name="mealLogId" value="${meal.mealLogId }" />
			<button class="try" type="submit">
				<i class="w3-xlarge fa fa-trash"></i>
			</button>
		</form>
	</div>
	<div>
		Meal:
		<c:out value="${meal.meal }" />
	</div>
	<div>
		Calories:
		<c:out value="${meal.caloriesConsumed }" />
	</div>
	<div></div>
	<br>
	<br>
	
<!-- 	</div> -->
	</div>
</c:forEach>
</div>
<!--Load the AJAX API-->
    <script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
    <script type="text/javascript">

      // Load the Visualization API and the corechart package.
      google.charts.load('current', {'packages':['corechart']});

      // Set a callback to run when the Google Visualization API is loaded.
      google.charts.setOnLoadCallback(drawChart);

      // Callback that creates and populates a data table,
      // instantiates the pie chart, passes in the data and
      // draws it.
      function drawChart() {

        // Create the data table.
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'date');
        data.addColumn('number', 'Calories Consumed');
        
        data.addRows([
        	<c:forEach items="${allInput}" var="meal"> 
        		['-<fmt:formatDate type = "date" value="${meal.date }"/>-', ${meal.caloriesConsumed}],
        	</c:forEach>
        ]);

        // Set chart options
        var options = {'title':'Calories Per Meal',
                       'width': 600,
                       'height':300};

        // Instantiate and draw our chart, passing in some options.
        var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
        chart.draw(data, options);
      }
    </script>
    
<!--     Load Pie Chart -->

<script type="text/javascript">
// Load google charts
google.charts.load('current', {'packages':['corechart']});
google.charts.setOnLoadCallback(drawChart);

// Draw the chart and set the chart values
function drawChart() {
	

  var data = google.visualization.arrayToDataTable([
	 
  ['Task', 'Hours per Day'],
  ['Protein', ${total.protein}],
  ['Carbohydrates', ${total.carbs}],
  ['Fat', ${total.fat}],

]);

  // Optional; add a title and set the width and height of the chart
  var options = {'title':'Day Total',
		  'width':500, 
		  'height':350,
		  colors: ['#e0440e', '#e6693e', '#ec8f6e'],
		  slices: {  0: {offset: 0.1},
              1: {offset: 0.1},
              2: {offset: 0.1},
		  },
		  backgroundColor: '#8db4d5',
		  chartArea:{left:130, top:50 ,width:'60%',height:'80%'},
    	};
   

  // Display the chart inside the <div> element with id="piechart"
  var chart = new google.visualization.PieChart(document.getElementById('piechart'));
  chart.draw(data, options);
};
</script>

<%@include file="common/footer.jspf" %>