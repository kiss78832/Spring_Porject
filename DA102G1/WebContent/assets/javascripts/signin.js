	$(document).ready(function() {
		
           $('#signin').click(function(){
            $('#lightBox').toggle(500);
            $("#swarnning p").hide();
             $('input').removeClass("wrong");
           })

           $('.login-wrap').click(function(){
          event.stopPropagation();
           })

           $('#lightBox').click(function(){
            $('#lightBox').hide();
           })
           
           
              <!-- 姓名不得為空 -->
      $('#userName').blur(function(){
      if($(this).val().length==0){
		$(this).addClass("wrong");
		$(".label").eq(2).html("username"+'<font style="color:red;">*姓名不得為空</font>');
     }else{
		$(this).removeClass("wrong");
		$(".label").eq(2).html("username");
     }
   })
   
   
   
   
      $('#newPass').keyup(function(){
    if ($(this).val().length<=7) {
		$(this).addClass("wrong");
		$(".label").eq(4).html('Password'+'<font style="color:red;">*不能低於7個字</font>');
    }else{
		$(this).removeClass("wrong");
		$(".label").eq(4).html('Password');
    }   
   })
   
   $('#rePass').keyup(function(){
    if($(this).val() !=$('#newPass').val()){
		$(this).addClass("wrong");
		$(".label").eq(5).html('REPEAT PASSWORD'+'<font style="color:red;">*密碼不相符</font>');
    }else{
		$(this).removeClass("wrong");
		$(".label").eq(5).html('REPEAT PASSWORD');
    }
   })

   var emailRule = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z]+$/;

   $('#newEmail').keyup(function(){
      if( emailRule.test($(this).val())){
        $(this).removeClass("wrong");
        $(".label").eq(6).html('Email Address');
     }else{
        $(this).addClass("wrong");
        $(".label").eq(6).html('Email Address'+'<font style="color:red;">*格式錯誤</font>');
     }
   })
   
   
   
           
           

         });