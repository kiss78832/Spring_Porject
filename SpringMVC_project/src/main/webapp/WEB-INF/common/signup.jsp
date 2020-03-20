<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
  <html>
  <head>
    <title></title>
    <meta charset="utf-8">
	<script type="text/javascript" src="<%= application.getContextPath() %>/assets/javascripts/signin.js"></script>
    <link rel="stylesheet" type="text/css" href="<%= application.getContextPath() %>/assets/stylesheets/login.css">


</head>
<body>

	<div id="lightBox">
    
  <div class="login-wrap">
  <div class="login-html">
    <input id="tab-1" type="radio" name="tab" class="sign-in" checked><label for="tab-1" class="tab">Sign In</label>
    <input id="tab-2" type="radio" name="tab" class="sign-up"><label for="tab-2" class="tab">Sign Up</label>
    <div class="login-form">
    
     <div class="sign-in-htm">

      <div id="swarnning">
      <p>Pleace Sign In or Sing Up</p>
      <p>Account & PassWord cannot be empty</p>
      <p>wrong Account or PassWord</p>
      </div>
       <form action="<%= application.getContextPath() %>/signinhandler" method="POST">
        <div class="group">
           <label for="user" class="label">Username</label>
           <input id="user" type="text" class="input"
            name="member_id">
         </div>
         <div class="group">
           <label for="pass" class="label">Password</label>
           <input id="pass" type="password" class="input" data-type="password"
           name="password">
         </div>
         <div class="group">
           <input id="check" type="checkbox" class="check" checked>
           <label for="check"><span class="icon"></span> Keep me Signed in</label>
         </div>
         <div class="group">
           <input type="submit" class="button" value="Sign In">
           <input type="hidden" class="button" name="action" value="SINGIN">
         </div>
         <div class="hr"></div>
         <div class="foot-lnk">
           <a class="Forgot">Forgot Password?</a>
         </div>
       </form>
     </div>
      
      
     <div class="sign-up-htm">
      <form id="signUpForm" action="<%= application.getContextPath() %>/member/member.do" method="POST">
        <div class="group">
            <label for="userName" class="label">Username</label>
            <input id="userName" type="text" class="input"
            name="m_name" placeholder="Your Name">
          </div>
          <div class="group">
            <label for="newAccount" class="label">Account</label>
            <input id="newAccount" type="text" class="input"
            name="member_id" placeholder="Your Account">
            <div id="AChecked"><img src="<%= application.getContextPath() %>/assets/images/Icon/checked.png"></div>
            <div id="UnChecked"><img src="<%= application.getContextPath() %>/assets/images/Icon/cancel.png"></div>
          </div>
      <div class="group">
        <label for="newPass" class="label">Password</label>
        <input id="newPass" type="password" class="input" data-type="password"
        name="password" placeholder="輸入8-9位密碼">
      </div>
      <div class="group">
        <label for="rePass" class="label">Repeat Password</label>
        <input id="rePass" type="password" class="input" data-type="password"
        placeholder="再輸入一次您的密碼">
      </div>
        <div class="group">
          <label for="newEmail" class="label">Email Address</label>
          <input id="newEmail" type="email" class="input"
          name="m_email" placeholder="輸入您的Email" required>
        </div>
        <div class="hr"></div>
        <div class="group">
          <input type="button" class="button" value="Sign Up">
          <input type="hidden" class="button" name="action" value="SINGUP">
        </div>
        <div class="hr"></div>
      </form>
       
     </div>
      
      
    </div>
  </div>
</div>

  </div>
  
  <script type="text/javascript">
  <%
	String str = (String)session.getAttribute("signin");
	String signUp = (String)session.getAttribute("signupSuccess");
  %>
  $(document).ready(function(){
	<%-- 非法進入需登入頁面 --%>
    if ("<%= str%>"=="reject") {
		$('#lightBox').show();
		$("#swarnning p:eq(0)").show();
    	 <%--$('.sign-up').attr("checked","ture");--%>
  }else if(<%= str%>==null){
		$('#lightBox').hide();
  }
    
    
    
    <%-- 登入成功 --%>
    if(<%= signUp%>){
		swal({
			title: 'Sign in Success!',
			type:'success',
			showConfirmButton: false,
			timer: 2000,
		}).catch(swal.noop);
    }
    
    <%-- 錯誤跳窗後移除屬性 --%>
    <% session.removeAttribute("signin");
    session.removeAttribute("signupSuccess");
    %>
    
    <%-- 更換in out --%>
    if("<%= session.getAttribute("memberVO")%>" != "null"){
    	$('#signin').hide();
    	$('#signout').show();
    	
    }else if("<%= session.getAttribute("memberVO")%>"==="null") {
    	$('#signin').show();
    	$('#signout').hide();
    }
    <%-- 登入錯誤表列 --%>
   var error ='<c:if test="${not empty errorMsgs}"><c:forEach var="message" items="${errorMsgs}">${message}</c:forEach></c:if>'
   if(error.match("invalid")){<%-- 帳密錯誤 --%>
	   console.log("帳密錯誤");
	   $('#user').addClass("wrong");
	   $('#pass').addClass("wrong");
	   $('#lightBox').show();
	   $("#swarnning p:eq(2)").show();
   }else if(error.match("noValue")){<%-- 空值 --%>
	   $('#user').addClass("wrong");
	   $('#pass').addClass("wrong");
	   $('#lightBox').show();
	   $("#swarnning p:eq(1)").show();
   }
   <% session.removeAttribute("errorMsgs");%>
   
   <%-- 註冊錯誤及時資訊驗證 --%>

   
   $('#newAccount').keyup(function(){<!--帳號尚未驗證文字形式 -->
    if ($(this).val().length<=7) {
		$(this).addClass("wrong");
		$(".label").eq(3).html('Account'+'<font style="color:red;">*不能低於7個字</font>');
		$('#AChecked').hide();
		$('#UnChecked').hide();
    }else if($(this).val().length>7){
		var str = {
          "action":"COMPARE",
          "member_id":$(this).val(),
          "m_email":$('#newEmail').val(),
          };
          
		var str1 = JSON.stringify(str).toString();
		console.log(str1);
		
    	$.ajax({
   		 type: "POST",
   		 url: "<%= application.getContextPath() %>/member/member.do",
   		 data:str ,
   		 dataType: "json",
   		 success: function (data){
   			 
   			$.each(data, function(key, value){
   				if(value==true){
					$('#newAccount').addClass("wrong");
					$(".label").eq(3).html("Account"+'<font id="reject">此帳號已被註冊</font>');
					$('#AChecked').hide();
					$('#UnChecked').show();
   				}else if(value==false){
					$('#newAccount').removeClass("wrong");
					$(".label").eq(3).html("Account"+'<font id="allow">可以使用此帳號</font>');
					$('#AChecked').show();
					$('#UnChecked').hide();
   				}
   				console.log(value);
   				
   			})
   			
   	     },
              error: function(){alert("AJAX-class發生錯誤囉!")}
          })


		
    }
   })
   

   
 <%-- 錯誤阻擋送出 --%>
$('#signUpForm').click(function(e){
  var k;
    for(k=2; k<=6; k++ ){
      
      if($(".input").eq(k).val()==''|| $(".input").eq(k).val().toUpperCase()=="NULL"){
		$(".input").eq(k).addClass("wrong");
		var text = $(".label").eq(k).text();
		$(".label").eq(2).html("username");
		$(".label").eq(k).html("username"+'<font style="color:red;">*姓名不得為空</font>');
    	  
        e.preventDefault();
    	return;
      }
    };
    
    if($('#newPass').val().length<=7||$('#rePass').val() !=$('#newPass').val()||$('#newAccount').val().length<=7){
		e.preventDefault();
    	return;
    };
    
    	var str = {
            "action":"COMPARE",
            "member_id": $('#newAccount').val(),
            };
            
		var str1 = JSON.stringify(str).toString();
		
            console.log(str1);
      	$.ajax({
			type: "POST",
			url: "<%= application.getContextPath() %>/member/member.do",
			data:str ,
			dataType: "json",
			async: false, /*同步*/
			success: function (data){
     			 
     			$.each(data, function(key, value){
     				if(value==true){
						$('#newAccount').addClass("wrong");
						$(".label").eq(3).html("Account"+'<font id="reject">此帳號已被註冊</font>');
						$('#AChecked').hide();
						$('#UnChecked').show();
						e.preventDefault();
				    	return;
     				}else if(value==false){
						$('#newAccount').removeClass("wrong");
						$(".label").eq(3).html("Account"+'<font id="allow">可以使用此帳號</font>');
						$('#AChecked').show();
						$('#UnChecked').hide();
     				}
     				console.log(value);
     				
     			})
     			
     	     },
                error: function(){alert("AJAX-class發生錯誤囉!")}
            })
            
            
            /*如果驗證前台正確，呼叫發法送出驗證信*/
            confirmMail();
})


/**/
	function confirmMail(){
	console.log("送出驗證信");
	console.log("新註冊帳號"+$('#newAccount').val());
	
   	var str = {
          "action":"signup",
          "member_id": $('#newAccount').val(),
          "m_email":$('#newEmail').val(),
          };
	
// 	var str1 = JSON.stringify(str).toString();
	
  	$.ajax({
		type: "POST",
		url: "<%= application.getContextPath() %>/member/confirmMail.send",
		data:str ,
		dataType: "json",
		async: true, /*非同步(預設true)*/
		success: function (data){
 			console.log("data = "+data.pass);
			
			swal({
				  title: '註冊驗證碼已寄至您的信箱',
				  html:'請輸入驗證碼 ：',
				  input: 'text',
				  showCancelButton: true,
				  confirmButtonText: 'Submit',
				  showLoaderOnConfirm: true,
				  preConfirm: function(text) {
					  
				    return new Promise(function(resolve, reject) {
				    	/*點選送出驗證碼時同步驗證*/
				    	var getconfirm = {
				    	          "action":"getconfirm",
				    	          "member_id": $('#newAccount').val(),
				    	          };
// 				    	var str1 = JSON.stringify(getconfirm).toString();
				    	var successCode;
				    	//改為同步 比對驗證碼
				    	$.ajax({
				    		type: "POST",
				    		url: "<%= application.getContextPath() %>/member/confirmMail.send",
				    		data:getconfirm ,
				    		dataType: "json",
				    		async: false, /*同步*/
				    		success: function (confirmCode){
				    			
				    			console.log("servlet回傳 = "+confirmCode.confirm);
				    			successCode = confirmCode.confirm;
				    			
				    		}
				    	})
				    	
				    	
				    	    	
				    //比對驗證碼是否一致
				      setTimeout(function() {
				         if(text===""||text.toUpperCase()=="NULL"){
			        	 	reject('不得為空值');
				        }else if(text != successCode){
				        	reject('輸入錯誤');
				        }else if(text ==successCode){//與驗證碼一致
				        	$('#signUpForm').submit();
				        	resolve();
				        }
				        
				      }, 2000);
				    });
				    
				  },
				  allowOutsideClick: false
				})
		}
        })
        
        }
        
        
        <%-- 忘記密碼 --%>
  $('.Forgot').click(function(){
              swal({
            title: '請輸入Email與帳號 ',
            html:
              '<form>' +
                '<div class="form-group">' +
                  '<label for="m_email" class="pull-left">Email：</label>' +
                  '<input type="text" class="form-control" id="m_email" placeholder="Email">' +
                '</div>' +
                '<div class="form-group">' +
                  '<label for="member_id" class="pull-left">帳號：</label>' +
                  '<input type="text" class="form-control" id="member_id" placeholder="Account">' +
                '</div>' +
              '</form>',
              showLoaderOnConfirm: true,
            type: "warning",
            preConfirm: function () {
                    return new Promise(function (resolve, reject) {
                        var data = {};
                        data.action ="FORGETPASSWORD";
                        data.m_email = $('#m_email').val().trim();
                        data.member_id = $('#member_id').val().trim();

                        if (!data.m_email) reject('請輸入Email！');
                        else if (!data.member_id) reject('請輸入帳號！');
                        else {
                          $.ajax({
                           type: "POST",
                           url: "<%= application.getContextPath() %>/member/member.do",
                           data: data,
                           dataType: "json",
                           success: function (result){
                            if(false === result.result){
                              reject('輸入資料錯誤！');
                            } else {
                              resolve();
                            }
                             },
                                 error: function(){
                                    reject('AJAX發生錯誤囉!');
                                   }
                            });
                        }
                    })
                },
                onOpen: function () {
                    $('#account').focus();
                },
          }).then(function () {
        	  setTimeout(function(){
              swal({
                    type: 'success',
                    title: '帳號密已經寄至您的信箱 !',
                  html: '<b> 感謝你對本站的長期支持 ! </b>',
                })
        	  }, 2000);
          }).catch(swal.noop);
  })  
		

   
  })
  
  
  
  
		
  </script>
  
  </body>
  </html>  
  