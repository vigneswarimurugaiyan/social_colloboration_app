$( document ).ready(function() {
	$('.message a').click(function(){
	   $('.dsinz-form').animate({height: "toggle", opacity: "toggle"}, "slow");
	});
	var loginerr = $('.errors').length;
	if(loginerr > 0 ){
	   $('.login-form form .message a').click();
	}
	
});