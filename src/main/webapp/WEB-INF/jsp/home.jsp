<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<div class="backgroundimg">
<c:set var="pageTitle" value="Home"/>
<%@include file="common/header.jspf" %>




<div class="login-wrapper">
<div class="two">
<c:url value="img/coollogo_com-159421344.png" var="logo"/>
<img class="logo" src="${logo }"/>
</div>

<div class="heading head1 fontwhite middle-lower">Welcome to Max's Gym Simulation</div>



</div>

<div class="row">
	<div class="col-md-6">
		<c:url value="img/fitPeople.jpg" var="fit"/>
		<div style="padding: 10%; padding-top:16%;">
<img class="pic-ppl" src="${fit }"/>
</div>
	</div>

<div class="col-md-6">
<div style="padding: 10%;  color: white; text-shadow: 2px 2px 4px #000000;">
		
		<div class="list-font">
			<div>
			<div class="larger"><i class="fa fa-pie-chart fa-2x" aria-hidden="true"></i> &nbsp<strong>Calorie Tracker:</strong></div>
					<div class="indent">-Track and monitor your caloric intake</div>
			</div>
			<br>
		
			<div>
			<div class="larger"><i class="fa fa-th-list fa-2x" aria-hidden="true"></i> &nbsp<strong>Session Tracker:</strong></div> 
			<div class="indent">-This feature allows current Personal Training client's can keep track of their sessions.</div>
			</div>
			<br>
			
			<div>
			<div class="larger"><i class="fa fa-bar-chart fa-2x" aria-hidden="true"></i> &nbsp<strong>Exercise Log (coming soon):</strong></div>
			 <div class="indent">-Allows you to keep track of your workouts.</div>
			</div>
			<br>
			
			<div>
			<div class="larger"><i class="fa fa-file-text-o fa-2x" aria-hidden="true"></i> &nbsp<strong>Forum (coming soon):</strong></div>
			<div class="indent"> -Stimulate discussions about current or new fitness topics.</div>
			</div>
			<br>
			
			<div>
			<div class="larger"><i class="fa fa-plus-square-o fa-2x" aria-hidden="true"></i> &nbsp<strong>And More to Come!</strong></div>
			</div>
			</div>
	</div>
	</div>

<br>
</div>

<div class="row font-back">
	<div class="col-md-6 head-about">
		
	</div>
	
</div>

<!-- carousel -->
<section class="awSlider">
  <div  class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
      <li data-target=".carousel" data-slide-to="0" class="active"></li>
      <li data-target=".carousel" data-slide-to="1"></li>
      <li data-target=".carousel" data-slide-to="2"></li>
      <li data-target=".carousel" data-slide-to="3"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
      <div class="item active">
         <c:url value="img/fitness-594143_640.jpg" var="pic1"/>
        <img class="item" src="${pic1}">
        <div class="carousel-caption"></div>
      </div>
      <div class="item">
       <c:url value="img/jogging-2343558_640.jpg" var="pic2"/>
        <img class="item" src="${pic2}">
        <div class="carousel-caption"></div>
      </div>
      <div class="item">
        <c:url value="img/sport-2262083_640.jpg" var="pic3"/>
        <img class="item" src="${pic3}">
        <div class="carousel-caption"></div>
      </div>
      <div class="item">
      <c:url value="img/running-573762_640.jpg" var="pic4"/>
        <img class="item" src="${pic4}">
        <div class="carousel-caption"></div>
      </div>
    </div>

    <!-- Controls -->
    <a class="left carousel-control" href=".carousel" role="button" data-slide="prev">
      <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
      <span class="sr-only">Geri</span>
    </a>
    <a class="right carousel-control" href=".carousel" role="button" data-slide="next">
      <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
      <span class="sr-only">İleri</span>
    </a>
  </div>
</section>


<%@include file="common/footer.jspf" %>
</div>