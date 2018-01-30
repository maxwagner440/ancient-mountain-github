<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<c:set var="pageTitle" value="Home" />
<%@include file="common/headerD.jspf"%>

<div class="dashboard-wrapper">
	<div class="two">

		<h1>Client Dashboard</h1>
		<%-- <c:url value="img/coollogo_com-159421344.png" var="logo"/> --%>
		<%-- <img class="logo" src="${logo }"/> --%>
	</div>



	<div class="name">
		<div class="name-font">
			<c:out value="${client.firstName } ${client.lastName }" />
		</div>
	</div>
	<div class="bio">
		<h3 class="bio-heading">Personal Bio:</h3>
		<hr class="small-border">
		<div>
			Weight:
			<c:out value="${client.weightInLbs }" />
			lbs
			
			
		</div>
		<div>
			Goal Weight:
			<c:out value="${client.goalWeightInLbs }" />
			lbs.
			
		</div>
		<div>
			Age:
			<c:out value="${client.age }" />
			years
			
		</div>
		<div>
			Daily Caloric Needs:
			<c:url value="/calorieCalc" var="calc"/>
			<a href="${calc }">			
				<button class="bttn-stretch" style="font-size: 1em;">
				<c:out value="${cals}" />
				</button>
				</a>
				calories
			
		</div>
		<div style="padding-top:10%;">
			<button id="myBtn">Change Info</button>
		</div>
	</div>
	
	<div class="forum">
		<div class="forum-font">Forum For Everyone:</div>
		<h3 class="fm-desc">Coming Soon!</h3>
		<p></p>
	</div>

</div>







<!-- The Modal -->
<div id="myModal" class="modal">

  <!-- Modal content -->
  <div class="modal-content">
    <span class="close">&times;</span>
    <c:url value="/infoChange" var="info"/>
    <form method="POST" action="${info}" style="padding-left: 8%;">
    
	    <label for="weightInLbs">Weight In Lbs.</label>
		<input type="text" name="weightInLbs" placeholder="Weight in Lbs"/>
		
	    
	    <label for="goalWeightInLbs">Goal Weight In Lbs.</label>
		<input type="text" name="goalWeightInLbs" placeholder="Goal Weight in lbs"/>
		
	   
	    <label for="age">Age</label>
		<input type="text" name="age" placeholder="Age"/>
	   <br>
	   <br>
	   <div>
    <input type="submit"/>
    </div>
    </form>
  </div>

</div>


<!-- Modal style -->
<style>

.modal {
	color: #4c4d7d;
    font-size: 1.2em;
    display: none; /* Hidden by default */
    position: fixed; /* Stay in place */
    z-index: 1; /* Sit on top */
    left: 0;
    top: 0;
    width: 100%; /* Full width */
    height: 100%; /* Full height */
    overflow: auto; /* Enable scroll if needed */
    background-color: rgb(0,0,0); /* Fallback color */
/*     background-color: rgba(0,0,0,0.4); /* Black w/ opacity */ */
}

/* Modal Content/Box */
.modal-content {
    background-color: #fefefe;
    margin: 15% auto; /* 15% from the top and centered */
    padding: 20px;
    border: 1px solid #888;
    width: 80%; /* Could be more or less, depending on screen size */
}

/* The Close Button */
.close {
    color: #aaa;
    float: right;
    font-size: 28px;
    font-weight: bold;
}

.modal > input{
	max-width: 80% !important;
}

.close:hover,
.close:focus {
    color: black;
    text-decoration: none;
    cursor: pointer;
}

</style>


<script>
$(document).ready(function () {
	// Get the modal
	var modal = document.getElementById('myModal');

	// Get the button that opens the modal
	var btn = document.getElementById("myBtn");
	
	// Get the <span> element that closes the modal
	var span = document.getElementsByClassName("close")[0];

	// When the user clicks on the button, open the modal 
	btn.onclick = function() {
	    modal.style.display = "block";
	}

	// When the user clicks on <span> (x), close the modal
	span.onclick = function() {
	    modal.style.display = "none";
	}

	// When the user clicks anywhere outside of the modal, close it
	window.onclick = function(event) {
	    if (event.target == modal) {
	        modal.style.display = "none";
	    }
	}
});



</script>




<%@include file="common/footer.jspf"%>