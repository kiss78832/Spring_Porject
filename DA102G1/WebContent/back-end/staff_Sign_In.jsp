<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
	<link rel="icon" type="image/x-icon"  href="https://i.imgur.com/J0aPP1N.png" />
	<title>Staff Sign In</title>
	<link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>


<!-- body -->


	<style type="text/css">
 /*本地字體引入*/
  @font-face{
    font-family: Russo;
    src:url('/DA102G1/assets/fonts/RussoOne-Regular.ttf');
     unicode-range: U+00-024F;
  }
  @font-face{
    font-family: Noto;
    src:url('/DA102G1/assets/fonts/NotoSansTC-Medium.otf');
    unicode-range: U+4E00-9FFF;
  }



  /* content start*/

.contentbox{
  margin-top: 80px;
  box-sizing: border-box;
  background-image: url(/DA102G1/assets/images/snowpeak/forest-1743206.jpg);
  background-size: cover;
  height: 770px;
  padding: 0px;
  

}
.contentbox .cb{/*contentbox 底下的div*/
  display: inline-block;
  position: relative;
}

/*centerbox start*/
#centerbox{
width: 100%;
background-color: rgba(52,53,55,0.7);
border: 1px solid rgba(150,150,150,0.5);
height: 770px;
}
/*centerbox end*/


  /* content end*/


/*signin start*/
.overlay{
	top:0;
	left:0;
	width:100%;
	height:auto;
}

h1{
  text-align:center;
  color:#FFF !important;
  margin-top:50%;
  font:60px;
  text-shadow: 0px 4px 3px rgba(0,0,0,0.4),
             0px 8px 13px rgba(0,0,0,0.1),
             0px 18px 23px rgba(0,0,0,0.1);
}

.wrap{
  width:400px;
  margin:0 auto;
}

input{
  width:100%;
  padding:10px;
  margin-bottom:20px;
  border-radius:5px;
  border:none;
  outline:none;
  font:13px;
}

input[type="submit"]{
  background:#e74c3c;
  color:#FFF;
  width:100%;
  padding:15px;
  font-weight:300;
  opacity:0.8;
}


input[type="submit"]:hover{
cursor:pointer;
  opacity:1;
}

input[type="text"]:focus{
  border-left:5px solid #e74c3c;
  -webkit-transition:0.4s ease;
}

input[type="email"]:focus{
  border-left:5px solid #e74c3c;
  -webkit-transition:0.4s ease;
}

/*signin end*/

.swarnning{
	margin:0 auto;
}

.swarnning p {
  color: red;
  font-size: 24px;
  display: none;
}

	</style>

</head>
<body>
	

	<div class="container-fluid">
		<div class="row">

			<jsp:include page="/common/adminNavbar.jsp" flush="true"/>
			
			

			<div class="col-lg-12 contentbox" >

			  
				<div id="centerbox" class="container-fluid m-auto">			
					
					<!-- sign in start-->
					<form action="<%= application.getContextPath() %>/StaffSigninHandler" method="POST">
						<div class="overlay">
						  <div class="wrap">
						   <h1>Staff Sign In</h1>
						<input type="text" name="sf_account" placeholder="Username">
						<input type="password"name="sf_password" placeholder="Password">
					    <input type="submit" value="Sign In">
						  </div>
						</div>
					</form>
					<!-- sign in end-->

				<div class="swarnning">
            <p>Wrong Account or Passwor</p>    
            <p>Account or Password can't be empty</p>
            <p>You shall not pass !!!<br>
            <img alt="you shall not pass" src="<%= application.getContextPath()%>/assets/images/GIF/you shall not pass.gif"></p>
        </div>

				</div>

			</div>






		  	<jsp:include page="/common/footer.jsp" flush="true"/>
		  
		</div>
	</div>



<script type="text/javascript">
  

    <%-- 登入錯誤表列 --%>
   var error ='<c:if test="${not empty errorMsgs}"><c:forEach var="message" items="${errorMsgs}">${message}</c:forEach></c:if>'
   if(error.match("invalid")){<%-- 帳密錯誤 --%>
     alert("帳密錯誤");

     $(".swarnning p:eq(0)").show();
   }else if(error.match("noValue")){<%-- 空值 --%>
   alert("空值");
     $(".swarnning p:eq(1)").show();
   }else if("<%= session.getAttribute("illegal")%>"=="you shall not pass!!!"){
   	$(".swarnning p:eq(2)").show();
   }
   <% session.removeAttribute("illegal");%>
   <% session.removeAttribute("errorMsgs");%>

</script>
<!-- JS -->
<script type="text/javascript" src="<%= application.getContextPath() %>/assets/javascripts/index.js"></script>

<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</body>
</html>