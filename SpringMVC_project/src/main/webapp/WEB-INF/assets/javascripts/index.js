	$(document).ready(function() {
	    if (document.documentMode || /Edge/.test(navigator.userAgent)) {
	        alert('Hello Microsoft User!');
	        $('.cpic').removeClass("cpic");
	    	}
		
        $('.carousel').carousel({
            interval: 2800 
        })

        $("#main-nav").css("background-color","rgba(28,26,29,1)");
        
        $(window).scroll(function(){
          var scrollTop= $(this).scrollTop(); 
          if (scrollTop<=4) {
            $("#main-nav").css("background-color","rgba(28,26,29,1)");
          }else{
            $("#main-nav").css("background-color","rgba(28,26,29,0.8)");
          }
        })
        
	})