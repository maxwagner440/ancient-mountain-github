
$(document).ready(function () { 

	$(".Cardio").on('click', function(){
		$(this).toggleClass('increaseFontSize');
		$("#cardio-img").show('slow');
		$("#lifting-img").hide('slow');
		$("#ct-img").hide('slow');
	});
	
	$(".Weight-Lifting").on('click', function(){
		$(this).toggleClass('increaseFontSize');
		$("#cardio-img").hide('slow');
		$("#lifting-img").show('slow');
		$("#ct-img").hide('slow');
	});
	
	$(".Cross-Training").on('click', function(){
		$(this).toggleClass('increaseFontSize');
		$("#cardio-img").hide('slow');
		$("#lifting-img").hide('slow');
		$("#ct-img").show('slow');
	});


	$('#sign').on('click', function(){
		$('#in').toggleClass('hidden');
	});
	
	$('section.awSlider .carousel').carousel({
		pause: "hover",
	  interval: 2000
	});

	var startImage = $('section.awSlider .item.active > img').attr('src');
	$('section.awSlider').append('<img src="' + startImage + '">');

	$('section.awSlider .carousel').on('slid.bs.carousel', function () {
	 var bscn = $(this).find('.item.active > img').attr('src');
		$('section.awSlider > img').attr('src',bscn);
	});


	if($('.meal-item1')[0] !== null){
		$('#select-one').addClass('hide');
	}
});